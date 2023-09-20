package com.wayakeji.qiniuyun.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

/**
 * @version 1.0
 * @auther bojan
 * @date 2021/4/28 15:21
 */
@Configuration
public class Qiniuyun {
	public static final String FORM_MIME = "application/x-www-form-urlencoded";
	private static final Charset CHARSET = Charset.forName("UTF-8");
	
	 /**
     * 七牛云图片瘦身
     */
    static String imageslim = "?imageslim";
    
	/**
	 * 七牛云上传空间
	 */
	static String bucket = "";

	/**
	 * 图片上传至七牛云的路径前缀
	 */
	static String key;

	/**
	 * 七牛云accessKey
	 */
	static String accessKey;

	/**
	 * 七牛云secretKey
	 */
	static String secretKey;

	static String rsHttps = "https://rs.qbox.me";
	/**
	 * 七牛云空间资源访问域名
	 */
	static String domain;


	@Value("${qinniu.bucket:test-server}")
	public void setBucket(String bucket) {
		Qiniuyun.bucket = bucket;
	}

	@Value("${qinniu.key}")
	public void setKey(String key) {
		Qiniuyun.key = key;
	}

	@Value("${qinniu.accessKey:ZKu4l6QoEiPKjC0Ql8oRMbMmgCzVJcw6znFRfWp2}")
	public void setAccessKey(String accessKey) {
		Qiniuyun.accessKey = accessKey;
	}

	@Value("${qinniu.secretKey:wEKqBb4BVR2Sa47y2jROVMbZgc6Ycq7GPROjBnbv}")
	public void setSecretKey(String secretKey) {
		Qiniuyun.secretKey = secretKey;
	}

	@Value("${qinniu.domain}")
	public void setDomain(String domain) {
		if(domain.charAt(domain.length() - 1) == '/') {
			Qiniuyun.domain = domain;
		}else {
			Qiniuyun.domain = domain + '/';
		}
	}
	
	/**
	 * 获取七牛云图片瘦身后缀
	 * @return
	 */
	public static String getImageslim() {
		return imageslim;
	}

	public static String getBucket() {
		return bucket;
	}

	public static String getKey() {
		return key;
	}

	public static String getAccessKey() {
		return accessKey;
	}

	public static String getSecretKey() {
		return secretKey;
	}

	public static String getRsHttps() {
		return rsHttps;
	}

	public static String getDomain() {
		return domain;
	}

	private static long deadline() {
		// token有效期3600秒
		return System.currentTimeMillis() / 1000 + 3600;
	}

	/**
	 * 获取七牛云上传token
	 * @return 七牛云上传token
	 */
	public static String uploadToken() {
		String str = "{\"scope\":\"" + bucket + "\",\"deadline\":" + deadline() + "}";
		String last = encodeToString(str.getBytes(CHARSET));
		return sign(last.getBytes(CHARSET)) + ":" + last;
	}

	public static JSONArray batchDelete(String... keys) throws IOException {
		byte[] body = body(keys, "op=delete/").getBytes(CHARSET);
		String url = rsHttps + "/batch";
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		authorization(post, url, body, FORM_MIME);
		post.addHeader("Content-Type", FORM_MIME);
		post.addHeader("User-Agent", "QiniuJava/7.2.18 (Windows 10 amd64 10.0) Java/1.8.0_201");
		post.setEntity(new ByteArrayEntity(body));
		try {
			CloseableHttpResponse response = client.execute(post);
			String rsp = EntityUtils.toString(response.getEntity());
			return JSON.parseArray(rsp);
		}catch (Exception e) {
			throw e;
		}finally {
			client.close();
		}
	}

	public static void authorization(HttpRequest request, String url, byte[] body, String contentType) {
		String authorization = "QBox " + signRequest(url, body, contentType);
		request.addHeader("Authorization", authorization);
	}

	public static String signRequest(String url, byte[] body, String contentType) {
		URI uri = URI.create(url);
		String path = uri.getRawPath();
		String query = uri.getRawQuery();
		Mac mac = createMac();
		mac.update(path.getBytes(CHARSET));
		if(query != null && query.length() != 0) {
			mac.update((byte) ('?'));
			mac.update(query.getBytes(CHARSET));
		}
		mac.update((byte) '\n');
		if(body != null && FORM_MIME.equalsIgnoreCase(contentType)) {
			mac.update(body);
		}
		String digest = encodeToString(mac.doFinal());
		return accessKey + ":" + digest;
	}

	private static String body(String[] arr, String action) {
		StringBuilder strb = new StringBuilder();
		for(int i = 0; i < arr.length; i++) {
			if(i != 0) {
				strb.append("&");
			}
			strb.append(action);
			strb.append(Qiniuyun.encodeToString("line:" + arr[i]));
		}
		return strb.toString();
	}

	public static Mac createMac() {
		SecretKeySpec sks = new SecretKeySpec(secretKey.getBytes(CHARSET), "HmacSHA1");
		Mac mac;
		try {
			mac = Mac.getInstance("HmacSHA1");
			mac.init(sks);
			return mac;
		}catch (GeneralSecurityException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static String sign(byte[] abyte) {
		return accessKey + ":" + encodeToString(createMac().doFinal(abyte));
	}

	public static String encodeToString(String input) {
		return encodeToString(input.getBytes(CHARSET));
	}

	public static String encodeToString(byte[] input) {
		try {
			return new String(encode(input, 0, input.length, Encoder.URL_SAFE | Encoder.NO_WRAP), "US-ASCII");
		}catch (UnsupportedEncodingException e) {
			// US-ASCII is guaranteed to be available.
			throw new AssertionError(e);
		}
	}

	public static byte[] encode(byte[] input, int offset, int len, int flags) {
		Encoder encoder = new Encoder(flags, null);
		// Compute the exact length of the array we will produce.
		int output_len = len / 3 * 4;
		// Account for the tail of the data and the padding bytes, if any.
		if(encoder.do_padding) {
			if (len % 3 > 0) {
				output_len += 4;
			}
		}else {
			switch (len % 3) {
				case 0:
					break;
				case 1:
					output_len += 2;
					break;
				case 2:
					output_len += 3;
					break;
			}
		}
		// Account for the newlines, if any.
		if(encoder.do_newline && len > 0) {
			output_len += (((len - 1) / (3 * Encoder.LINE_GROUPS)) + 1) * (encoder.do_cr ? 2 : 1);
		}
		encoder.output = new byte[output_len];
		encoder.process(input, offset, len, true);
		assert encoder.op == output_len;
		return encoder.output;
	}

	static class Encoder {
		public byte[] output;
		public int op;

		/**
		 * Encoder flag bit to omit the padding '=' characters at the end
		 * of the output (if any).
		 */
		private static final int NO_PADDING = 1;

		/**
		 * Encoder flag bit to omit all line terminators (i.e., the output
		 * will be on one long line).
		 */
		private static final int NO_WRAP = 2;

		/**
		 * Encoder flag bit to indicate lines should be terminated with a
		 * CRLF pair instead of just an LF.  Has no effect if {@code
		 * NO_WRAP} is specified as well.
		 */
		private static final int CRLF = 4;

		/**
		 * Encoder/decoder flag bit to indicate using the "URL and
		 * filename safe" variant of Base64 (see RFC 3548 section 4) where
		 * {@code -} and {@code _} are used in place of {@code +} and
		 * {@code /}.
		 */
		private static final int URL_SAFE = 8;
		/**
		 * Emit a new line every this many output tuples.  Corresponds to
		 * a 76-character line length (the maximum allowable according to
		 * <a href="http://www.ietf.org/rfc/rfc2045.txt">RFC 2045</a>).
		 */
		public static final int LINE_GROUPS = 19;

		/**
		 * Lookup table for turning Base64 alphabet positions (6 bits)
		 * into output bytes.
		 */
		private static final byte ENCODE[] = {
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
				'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
				'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
				'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/',
		};

		/**
		 * Lookup table for turning Base64 alphabet positions (6 bits)
		 * into output bytes.
		 */
		private static final byte ENCODE_WEBSAFE[] = {
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
				'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
				'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
				'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_',
		};

		final public boolean do_padding;
		final public boolean do_newline;
		final public boolean do_cr;
		final private byte[] tail;
		final private byte[] alphabet;
		/* package */ int tailLen;
		private int count;

		public Encoder(int flags, byte[] output) {
			this.output = output;

			do_padding = (flags & NO_PADDING) == 0;
			do_newline = (flags & NO_WRAP) == 0;
			do_cr = (flags & CRLF) != 0;
			alphabet = ((flags & URL_SAFE) == 0) ? ENCODE : ENCODE_WEBSAFE;

			tail = new byte[2];
			tailLen = 0;

			count = do_newline ? LINE_GROUPS : -1;
		}

		/**
		 * @return an overestimate for the number of bytes {@code
		 * len} bytes could encode to.
		 */
		public int maxOutputSize(int len) {
			return len * 8 / 5 + 10;
		}

		public boolean process(byte[] input, int offset, int len, boolean finish) {
			// Using local variables makes the encoder about 9% faster.
			final byte[] alphabet = this.alphabet;
			final byte[] output = this.output;
			int op = 0;
			int count = this.count;

			int p = offset;
			len += offset;
			int v = -1;

			// First we need to concatenate the tail of the previous call
			// with any input bytes available now and see if we can empty
			// the tail.

			switch (tailLen) {
				case 0:
					// There was no tail.
					break;

				case 1:
					if (p + 2 <= len) {
						// A 1-byte tail with at least 2 bytes of
						// input available now.
						v = ((tail[0] & 0xff) << 16) |
								((input[p++] & 0xff) << 8) |
								(input[p++] & 0xff);
						tailLen = 0;
					}
					;
					break;

				case 2:
					if (p + 1 <= len) {
						// A 2-byte tail with at least 1 byte of input.
						v = ((tail[0] & 0xff) << 16) |
								((tail[1] & 0xff) << 8) |
								(input[p++] & 0xff);
						tailLen = 0;
					}
					break;
			}

			if (v != -1) {
				output[op++] = alphabet[(v >> 18) & 0x3f];
				output[op++] = alphabet[(v >> 12) & 0x3f];
				output[op++] = alphabet[(v >> 6) & 0x3f];
				output[op++] = alphabet[v & 0x3f];
				if (--count == 0) {
					if (do_cr) output[op++] = '\r';
					output[op++] = '\n';
					count = LINE_GROUPS;
				}
			}

			// At this point either there is no tail, or there are fewer
			// than 3 bytes of input available.

			// The main loop, turning 3 input bytes into 4 output bytes on
			// each iteration.
			while (p + 3 <= len) {
				v = ((input[p] & 0xff) << 16) |
						((input[p + 1] & 0xff) << 8) |
						(input[p + 2] & 0xff);
				output[op] = alphabet[(v >> 18) & 0x3f];
				output[op + 1] = alphabet[(v >> 12) & 0x3f];
				output[op + 2] = alphabet[(v >> 6) & 0x3f];
				output[op + 3] = alphabet[v & 0x3f];
				p += 3;
				op += 4;
				if (--count == 0) {
					if (do_cr) output[op++] = '\r';
					output[op++] = '\n';
					count = LINE_GROUPS;
				}
			}

			if (finish) {
				// Finish up the tail of the input.  Note that we need to
				// consume any bytes in tail before any bytes
				// remaining in input; there should be at most two bytes
				// total.

				if (p - tailLen == len - 1) {
					int t = 0;
					v = ((tailLen > 0 ? tail[t++] : input[p++]) & 0xff) << 4;
					tailLen -= t;
					output[op++] = alphabet[(v >> 6) & 0x3f];
					output[op++] = alphabet[v & 0x3f];
					if (do_padding) {
						output[op++] = '=';
						output[op++] = '=';
					}
					if (do_newline) {
						if (do_cr) output[op++] = '\r';
						output[op++] = '\n';
					}
				} else if (p - tailLen == len - 2) {
					int t = 0;
					v = (((tailLen > 1 ? tail[t++] : input[p++]) & 0xff) << 10) |
							(((tailLen > 0 ? tail[t++] : input[p++]) & 0xff) << 2);
					tailLen -= t;
					output[op++] = alphabet[(v >> 12) & 0x3f];
					output[op++] = alphabet[(v >> 6) & 0x3f];
					output[op++] = alphabet[v & 0x3f];
					if (do_padding) {
						output[op++] = '=';
					}
					if (do_newline) {
						if (do_cr) output[op++] = '\r';
						output[op++] = '\n';
					}
				} else if (do_newline && op > 0 && count != LINE_GROUPS) {
					if (do_cr) output[op++] = '\r';
					output[op++] = '\n';
				}

				assert tailLen == 0;
				assert p == len;
			} else {
				// Save the leftovers in tail to be consumed on the next
				// call to encodeInternal.

				if (p == len - 1) {
					tail[tailLen++] = input[p];
				} else if (p == len - 2) {
					tail[tailLen++] = input[p];
					tail[tailLen++] = input[p + 1];
				}
			}

			this.op = op;
			this.count = count;

			return true;
		}

	}



}
