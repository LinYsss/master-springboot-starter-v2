package com.wayakeji.payment.wxpay;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class CertRequest {
	
	private int respCode;
	
	/**
	 * <p>创建HttpClient
	 * @param config
	 * @return
	 * @throws Exception
	 */
	public HttpClient createClient(WxpayConfig config) throws Exception {
		SSLConnectionSocketFactory sslFactory = createSSLFactory(config);
		BasicHttpClientConnectionManager connManager = createConnectionManager(sslFactory);
		return HttpClientBuilder.create().setConnectionManager(connManager).build();
	}
	
	/**
	 * 
	 * <p>概要描述，通常用一句或者一段话简要描述该方法的作用，以英文句号作为结束
	 * <p>详细描述，通常用一段或者多段话来详细描述该方法的作用，一般每段话都以英文句号作为结束
	 * @param config
	 * @return
	 * @throws Exception
	 */
	public BasicHttpClientConnectionManager createConnectionManager(SSLConnectionSocketFactory sslFactory) throws Exception {
		return new BasicHttpClientConnectionManager(
				RegistryBuilder.<ConnectionSocketFactory>create()
					.register("http", PlainConnectionSocketFactory.getSocketFactory())
					.register("https", sslFactory)
					.build(),
				null,
				null,
				null);
	}
	
	/**
	 * <p>创建SSL嵌套字连接工厂
	 * @param config
	 * @return
	 * @throws Exception
	 */
	public SSLConnectionSocketFactory createSSLFactory(WxpayConfig config) throws Exception {
		// 获得证书
		char[] password = config.mchId().toCharArray();
		InputStream certStream = config.certStream();
		KeyStore ks = KeyStore.getInstance("PKCS12");
		ks.load(certStream, password);
		// 实例化密钥库 & 初始化密钥工厂
		KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		kmf.init(ks, password);
		
		SSLContext ssl = SSLContext.getInstance("TLS");
		ssl.init(kmf.getKeyManagers(), null, new SecureRandom());
		return new SSLConnectionSocketFactory(
				ssl,
				new String[]{"TLSv1"},
				null,
				new DefaultHostnameVerifier());
	}
	
	public String execute(WxpayConfig config, String url, String param, HashMap<String, String> headers) throws Exception {
		HttpClient client = createClient(config);
		HttpPost post = new HttpPost(url);
		addHeaders(headers, post);
		RequestConfig rc = RequestConfig.custom()
				.setConnectTimeout(config.connectTimeout())
				.setSocketTimeout(config.readTimeout()).build();
		post.setConfig(rc);
		StringEntity reqEntity = new StringEntity(param, "UTF-8");
		post.setEntity(reqEntity);
		HttpResponse response = client.execute(post);
		respCode = response.getStatusLine().getStatusCode();
		if(respCode != 200) {
			return "";
		}
		HttpEntity respEntity = response.getEntity();
		return EntityUtils.toString(respEntity, "UTF-8");
	}
	
	private void addHeaders(HashMap<String, String> headers, HttpPost post) {
		Set<Entry<String, String>> set = headers.entrySet();
		for(Entry<String, String> entry : set) {
			post.addHeader(entry.getKey(), entry.getValue());
		}
	}
	
	public int getRespCode() {
		return respCode;
	}
	

}
