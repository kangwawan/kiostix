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
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.birumerah.kiostix.dto.ResponseLoginDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@Service
public class RestExecutor {

	private static final Logger logger = LoggerFactory.getLogger(RestExecutor.class);

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
		Object body = resultMap.get("body");
		String jsonReturn = gson.toJson(body);
		
		logger.debug(">> jsonReturn : "+jsonReturn);
		return jsonReturn;
	}

	public String executePOST(String endPoint, Object params, String secretKey){
		
		Gson gson = new Gson();
		String jsonParams = gson.toJson(params);
		logger.debug(">>> jsonParams : "+jsonParams);
		
		CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
	
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Content-Type", "application/json");
		requestHeaders.set("Authorization", secretKey);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(jsonParams, requestHeaders);
		
		Object result = restTemplate.exchange(endPoint, HttpMethod.POST, httpEntity, Object.class);
		ObjectMapper m = new ObjectMapper();
		Map resultMap = m.convertValue(result, Map.class);
		Object body = resultMap.get("body");
		String jsonReturn = gson.toJson(body);

		logger.debug(">> jsonReturn : "+jsonReturn);
		return jsonReturn;
	}

	public Object executePOSTwithCasting(String endPoint, Object params, String secretKey, String className) throws Exception{
		
		Gson gson = new Gson();
		String jsonParams = gson.toJson(params);
		logger.debug(">>> jsonParams : "+jsonParams);
		
		CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
	
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Content-Type", "application/json");
		requestHeaders.set("Authorization", secretKey);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(jsonParams, requestHeaders);
		
		Object result = restTemplate.exchange(endPoint, HttpMethod.POST, httpEntity, Object.class);
		ObjectMapper m = new ObjectMapper();
		Map resultMap = m.convertValue(result, Map.class);
		Object body = resultMap.get("body");
		logger.debug(">> body : "+body);
		return m.convertValue(body, Class.forName(className));
	}
	
	
	public ResponseLoginDTO loginPOST(String endPoint, Object params, String secretKey){
		
		Gson gson = new Gson();
		String jsonParams = gson.toJson(params);
		logger.debug(">>> jsonParams : "+jsonParams);
		
		CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
	
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Content-Type", "application/json");
		requestHeaders.set("Authorization", secretKey);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(jsonParams, requestHeaders);
		
		Object result = restTemplate.exchange(endPoint, HttpMethod.POST, httpEntity, Object.class);
		ObjectMapper m = new ObjectMapper();
		Map resultMap = m.convertValue(result, Map.class);
		Object body = resultMap.get("body");
		logger.debug(">> body : "+body);
		ResponseLoginDTO responseLoginDTO = m.convertValue(body, ResponseLoginDTO.class);
		return responseLoginDTO;
	}
	
	
	public String executeFormDataPOST(String endPoint, MultiValueMap<String, String> params, String secretKey){
//	public String executeFormDataPOST(String endPoint, Map params, String secretKey){
		
		Gson gson = new Gson();

		CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
	
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
		requestHeaders.set("Authorization", secretKey);
//		HttpEntity<?> httpEntity = new HttpEntity<Object>(params, requestHeaders);

		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(params, requestHeaders);
		
		Object result = restTemplate.exchange(endPoint, HttpMethod.POST, httpEntity, Object.class);
		ObjectMapper m = new ObjectMapper();
		Map resultMap = m.convertValue(result, Map.class);
		logger.debug(">> resultMap - body : "+resultMap.get("body"));
		Object body = resultMap.get("body");
		String jsonReturn = gson.toJson(body);

		logger.debug(">> jsonReturn : "+jsonReturn);
		return jsonReturn;
	}
	
}
