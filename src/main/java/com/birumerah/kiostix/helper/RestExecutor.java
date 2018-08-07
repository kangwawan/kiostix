package com.birumerah.kiostix.helper;

import java.util.Map;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@Service
public class RestExecutor {

	private static final Logger logger = LoggerFactory.getLogger(RestExecutor.class);

//	public String executeShopee(String endPoint, Object params, String secretKey){
//	
//		Gson gson = new Gson();
//		String jsonParams = gson.toJson(params);
//		logger.debug(">>> jsonParams : "+jsonParams);
//		
//		HMAC_SHA256Encoder hmacEncoder = new HMAC_SHA256Encoder();
//		String signatureBaseStr = endPoint+"|"+jsonParams;
//		logger.debug(">>> signatureBaseStr : "+signatureBaseStr);
//		String hmacEncoded = hmacEncoder.encode(signatureBaseStr, secretKey);
//		
//		CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
//		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//		requestFactory.setHttpClient(httpClient);
//		RestTemplate restTemplate = new RestTemplate(requestFactory);
//	
//		HttpHeaders requestHeaders = new HttpHeaders();
//		requestHeaders.set("Content-Type", "application/json");
//		requestHeaders.set("Content-Length", ""+jsonParams.length());
//		requestHeaders.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
//		requestHeaders.set("Authorization", hmacEncoded);
//		HttpEntity<?> httpEntity = new HttpEntity<Object>(jsonParams, requestHeaders);
//		
//		Object result = restTemplate.exchange(endPoint, HttpMethod.POST, httpEntity, Object.class);
//		String jsonReturn = gson.toJson(result);
//		logger.debug(">> jsonReturn : "+jsonReturn);
//		return jsonReturn;
//	}

	
	public String executeGET(String endPoint, Object params, String secretKey){
		
		Gson gson = new Gson();
		String jsonParams = gson.toJson(params);
		logger.debug(">>> jsonParams : "+jsonParams);
		
		CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
	
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Authorization", secretKey);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(requestHeaders);
		
		Object result = restTemplate.exchange(endPoint, HttpMethod.GET, httpEntity, Object.class);
		ObjectMapper m = new ObjectMapper();
		Map resultMap = m.convertValue(result, Map.class);
		logger.debug(">> resultMap - body : "+resultMap.get("body"));
		Object body = resultMap.get("body");
		String jsonReturn = gson.toJson(body);
		
		logger.debug(">> jsonReturn : "+jsonReturn);
		return jsonReturn;
	}

	public String executePOST(String endPoint, Object params, String secretKey){
		
		Gson gson = new Gson();
		String jsonParams = gson.toJson(params);
		logger.debug(">>> jsonParams : "+jsonParams);
		
//		HMAC_SHA256Encoder hmacEncoder = new HMAC_SHA256Encoder();
//		String signatureBaseStr = endPoint+"|"+jsonParams;
//		logger.debug(">>> signatureBaseStr : "+signatureBaseStr);
//		String hmacEncoded = hmacEncoder.encode(signatureBaseStr, secretKey);
		
		CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
	
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Content-Type", "application/json");
		requestHeaders.set("Content-Length", ""+jsonParams.length());
		requestHeaders.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		requestHeaders.set("Authorization", secretKey);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(jsonParams, requestHeaders);
		
		Object result = restTemplate.exchange(endPoint, HttpMethod.POST, httpEntity, Object.class);
		String jsonReturn = gson.toJson(result);
		logger.debug(">> jsonReturn : "+jsonReturn);
		return jsonReturn;
	}
	
}
