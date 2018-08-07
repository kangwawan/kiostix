package com.birumerah.kiostix.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.birumerah.kiostix.helper.RestExecutor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@Service("bookingService")
public class BookingService {
	private static final Logger logger = LoggerFactory.getLogger(BookingService.class);
	
	private String authKey = "a2lvc3RpeEFQSTAxMDMwNDIwMTg=";
	
	private String TICKET_URL = "http://devapi.kiostix.com/transaction/booking";
	
    @Autowired
    private LoginService loginService;

	public String addBooking(Map paramMap){
		logger.debug(">>call addBooking");

		LoginService login = new LoginService();
		Map loginMap = new HashMap();
		loginMap.put("email", "tbpos1@kiostix.com");
		loginMap.put("password", "admin123");
		String token = login.login(loginMap).getData().getToken();

		paramMap.put("token", token);
		
		RestExecutor exec = new RestExecutor();
		return exec.executePOST(TICKET_URL, paramMap, authKey);
		
	}	

//	public static void main(String[] args){
//		BookingService booking = new BookingService();
//		String paramStr = "{\"item\":{\"pw934umzpl\":\"2\"},\"time\":\"pwz377bzpl\",\"name\":\"Test API\",\"email\":\"apidev@kiostix.io\",\"gender\":\"Male\",\"phone\":\"085814174847\",\"dob\":\"1996-03-06\"}";		
//		Gson gson = new Gson();
//		ObjectMapper m = new ObjectMapper();
//		Map paramMap = m.convertValue(gson.toJson(paramStr), Map.class);
//
//		booking.addBooking(paramMap);
//	}
	
}
