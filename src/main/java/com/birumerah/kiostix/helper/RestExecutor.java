package com.birumerah.kiostix.helper;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.birumerah.kiostix.encoder.HMAC_SHA256Encoder;
import com.google.gson.Gson;

@Service
public class RestExecutor {

	private static final Logger logger = LoggerFactory.getLogger(RestExecutor.class);

	public String executeShopee(String endPoint, Object params, String secretKey){
	
		Gson gson = new Gson();
		String jsonParams = gson.toJson(params);
		logger.debug(">>> jsonParams : "+jsonParams);
		
		HMAC_SHA256Encoder hmacEncoder = new HMAC_SHA256Encoder();
		String signatureBaseStr = endPoint+"|"+jsonParams;
		logger.debug(">>> signatureBaseStr : "+signatureBaseStr);
		String hmacEncoded = hmacEncoder.encode(signatureBaseStr, secretKey);
		
		CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
	
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Content-Type", "application/json");
		requestHeaders.set("Content-Length", ""+jsonParams.length());
		requestHeaders.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		requestHeaders.set("Authorization", hmacEncoded);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(jsonParams, requestHeaders);
		
		Object result = restTemplate.exchange(endPoint, HttpMethod.POST, httpEntity, Object.class);
		String jsonReturn = gson.toJson(result);
		logger.debug(">> jsonReturn : "+jsonReturn);
		return jsonReturn;
	}
	
	public String executeTokopedia(String endPoint, Object params, String httpMethod, String token){
		
		String jsonParams = null;
		
		CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
	
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		requestHeaders.set("Authorization", "Bearer "+token);
		requestHeaders.set("Cache-Control", "no-cache");
		
		logger.debug(">>> token : "+token);
		HttpEntity<?> httpEntity = null;
		Gson gson = new Gson();
		String jsonReturn = null;
		
		if("POST".equals(httpMethod)){
			jsonParams = gson.toJson(params);
			logger.debug(">>> jsonParams : "+jsonParams);

			requestHeaders.set("Content-Type", "application/json");
			requestHeaders.set("Content-Length", ""+jsonParams.length());
			
			httpEntity = new HttpEntity<Object>(jsonParams, requestHeaders);

			Object result = restTemplate.exchange(endPoint, HttpMethod.POST, httpEntity, Object.class);
			jsonReturn = gson.toJson(result);
			logger.debug(">> jsonReturn : "+jsonReturn);
		}else{
			httpEntity = new HttpEntity<Object>(requestHeaders);

			Object result = restTemplate.exchange(endPoint, HttpMethod.GET, httpEntity, Object.class);
			jsonReturn = gson.toJson(result);
			logger.debug(">> jsonReturn : "+jsonReturn);
		}

		return jsonReturn;
	}

	public String loginTokopedia(String endPoint, String clientId, String clientSecret){
		
		logger.debug(">> loginTokopedia...");
		logger.debug(">> endPoint : "+endPoint);
		logger.debug(">> clientId : "+clientId);
		logger.debug(">> clientSecret : "+clientSecret);
		String signatureBaseStr = clientId+":"+clientSecret;
		byte[] encodedBytes = Base64.encodeBase64(signatureBaseStr.getBytes());
		String token = new String(encodedBytes);
		
		CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
	
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		requestHeaders.set("Authorization", "Basic "+token);
		requestHeaders.set("Cache-Control", "no-cache");
		requestHeaders.set("Content-Type", "application/x-www-form-urlencoded");
		
		HttpEntity<?> httpEntity = null;
		Gson gson = new Gson();
		String jsonReturn = null;
		
		httpEntity = new HttpEntity<Object>("grant_type=client_credentials", requestHeaders);

		Object result = restTemplate.exchange(endPoint, HttpMethod.POST, httpEntity, Object.class);
		jsonReturn = gson.toJson(result);
		logger.debug(">> jsonReturn : "+jsonReturn);

		return jsonReturn;
	}
	
}
