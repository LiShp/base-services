package com.gw.domain.hr.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.ByteArrayBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class HttpUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");


	private static final int DEFAULT_PORT = 80;


	public static HttpReturn postJSON(String host, String uri,  String param, String userName, String paasword) {
		return postJSON(host, DEFAULT_PORT, uri, param, DEFAULT_CHARSET, 10000, 10000, userName, paasword);
	}
	public static HttpReturn postJSON(String host, int port,String uri,  String param, Charset charset,int connectTimeout,int socketTimeout, String userName, String paasword) {
		if (charset == null) {
			charset = DEFAULT_CHARSET;
		}
		int status = -1;
		String text = "";
		StopWatch watch = new StopWatch();
		watch.start();
		CloseableHttpClient client = HttpClients.createDefault();
		try {

			HttpHost targetHost = new HttpHost(host, port, "http");
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(new AuthScope(targetHost.getHostName(),
					targetHost.getPort()), new UsernamePasswordCredentials(
					userName, paasword));

			// Create AuthCache instance
			AuthCache authCache = new BasicAuthCache();
			// Generate BASIC scheme object and add it to the local auth cache
			BasicScheme basicAuth = new BasicScheme();
			authCache.put(targetHost, basicAuth);

			// Add AuthCache to the execution context
			HttpClientContext context = HttpClientContext.create();
			context.setCredentialsProvider(credsProvider);
			context.setAuthCache(authCache);

			RequestConfig config = RequestConfig.copy(RequestConfig.DEFAULT).setExpectContinueEnabled(true)
					.setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
			HttpPost post = new HttpPost(uri);
			post.setConfig(config);
			post.setHeader("Content-Type", "application/json;charset=utf-8");
			HttpEntity entity = new StringEntity(param, charset);
			post.setEntity(entity);

			HttpResponse response = client.execute(targetHost, post, context);
			status = response.getStatusLine().getStatusCode();
			text = readContent(charset, response);

			HttpReturn httpReturn = new HttpReturn(status, text);
			return httpReturn;
		} catch (Exception e) {
			logger.error(uri + " :: " + param, e);
		}finally {
			try{
				watch.stop();
				logger.info(uri +  " " + param + " " + status + " " + text + " " + watch.getLastTaskTimeMillis() + "ms");
				client.close();
			}catch (Exception e){
				logger.error("关闭http请求异常",e);
			}
		}
		return null;
	}


	private static String readContent(Charset charset, HttpResponse response) throws IOException {
		InputStream is = response.getEntity().getContent();
		BufferedInputStream bis = new BufferedInputStream(is);
		ByteArrayBuffer baf = new ByteArrayBuffer(20);
		int current = 0;
		while ((current = bis.read()) != -1) {
			baf.append((byte) current);
		}
		String text = new String(baf.toByteArray(), charset);
		bis.close();
		is.close();
		return text;
	}

}