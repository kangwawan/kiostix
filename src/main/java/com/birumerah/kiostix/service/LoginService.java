package com.birumerah.kiostix.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.birumerah.kiostix.dto.ResponseLoginDTO;
import com.birumerah.kiostix.helper.RestExecutor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

@Service("LoginService")
public class LoginService {
	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
	
	private String authKey = "a2lvc3RpeEFQSTAxMDMwNDIwMTg=";
	
	private String LOGIN_URL = "http://devapi.kiostix.com/login";
	
	public ResponseLoginDTO login(Map paramMap){
		logger.debug(">>call getSchedulesByEventId");
		
		RestExecutor exec = new RestExecutor();
		return exec.loginPOST(LOGIN_URL, paramMap, authKey);
		
	}	
	
//	public static void main(String[] args){
//		LoginService s = new LoginService();
//		Map paramMap = new HashMap();
//		paramMap.put("email", "slimz@mailinator.com");
//		paramMap.put("password", "asdasd");
//		System.out.println("token : "+s.login(paramMap).getData().getToken());
//	}
}
