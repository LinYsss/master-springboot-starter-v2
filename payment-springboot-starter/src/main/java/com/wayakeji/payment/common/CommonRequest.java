package com.wayakeji.payment.common;

import com.alibaba.fastjson.JSONObject;
import com.wayakeji.payment.exception.PaymentRequestException;
import com.wayakeji.payment.exception.PaymentResponseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class CommonRequest {
	
	private static final String CONTENT_TYPE = "application/json";
	
	private HttpURLConnection conn;
	private OutputStream out;
	private BufferedReader br;
	private int respCode;
	
	private void buildConnect(String uri, String method, Map<String, String> headers) throws PaymentRequestException {
		method = method.toUpperCase();
		try {
			URL url = new URL(uri);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.addRequestProperty("Content-Type", CONTENT_TYPE);
			if(!method.equals("GET")) {
				conn.setDoOutput(true);
				conn.setRequestMethod(method);
			}
		}catch (Exception e) {
			throw new PaymentRequestException("Domain or model error", e);
		}
		if(headers != null) {
			Set<Entry<String, String>> set = headers.entrySet();
			for(Entry<String, String> entry : set) {
				conn.addRequestProperty(entry.getKey(), entry.getValue());
			}
		}
		connect();
	}
	
	private void connect() throws PaymentRequestException {
		try {
			conn.connect();
		}catch (IOException e) {
			throw new PaymentRequestException("connection error", e);
		}
	}
	
	private void write(String data) throws PaymentRequestException {
		try {
			out = conn.getOutputStream();
			out.write(data.getBytes());
			out.flush();
		}catch (IOException e) {
			throw new PaymentRequestException("Data transfer error", e);
		}
	}
	
	private String read() throws PaymentResponseException {
		try {
			respCode = conn.getResponseCode();
			if(respCode != 200) {
				return "";
			}
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String str;
			StringBuilder strb = new StringBuilder();
			while((str = br.readLine()) != null) {
				if(str.length() == 0) {
					continue;
				}
				strb.append(str);
			}
			return strb.toString();
		}catch (IOException e) {
			throw new PaymentResponseException("Data transfer error", e);
		}
	}
	
	public int getRespCode() {
		return respCode;
	}
	
	public String get(String url) throws PaymentRequestException, PaymentResponseException {
		return get(url, null);
	}
	
	public String post(String url, Map<String, Object> param) throws PaymentRequestException, PaymentResponseException {
		return post(url, param, null);
	}
	
	public String get(String url, Map<String, String> headers) throws PaymentRequestException, PaymentResponseException {
		buildConnect(url, "get", headers);
		return read();
	}
	
	public String get(String url, HashMap<String, Object> param, Map<String, String> headers) throws PaymentRequestException, PaymentResponseException {
		return get(url, ParameterParse.mapToSortQueryString(param), headers);
	}
	
	public String get(String url, String param, Map<String, String> headers) throws PaymentRequestException, PaymentResponseException {
		if(url.indexOf('?') > -1) {
			return get(url + "&" + param, headers);
		}else {
			return get(url + "?" + param, headers);
		}
	}
	
	public String post(String url, Map<String, Object> param, Map<String, String> headers) throws PaymentRequestException, PaymentResponseException {
		return post(url, new JSONObject(param).toString(), headers, "post");
	}
	
	public String post(String url, String param, Map<String, String> headers, String method) throws PaymentRequestException, PaymentResponseException {
		buildConnect(url, method, headers);
		write(param);
		return read();
	}
	
}
