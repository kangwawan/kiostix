package com.birumerah.kiostix.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.birumerah.kiostix.helper.RestExecutor;

@Service("eventService")
public class EventService {

	private static final Logger logger = LoggerFactory.getLogger(EventService.class);
	
	private String authKey = "a2lvc3RpeEFQSTAxMDMwNDIwMTg=";
	
	private String GET_EVENT_URL = "http://devapi.kiostix.com/event";
	
	public String getEvents(){
		logger.debug(">>call getEvents");
		
		RestExecutor exec = new RestExecutor();
		return exec.executeGET(GET_EVENT_URL, null, authKey);
		
	}	

//	public static void main(String[] arg){
//		EventService s = new EventService();
//		s.getEvents();
//	}

}
