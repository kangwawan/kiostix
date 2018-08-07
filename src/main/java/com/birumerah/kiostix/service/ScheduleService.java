package com.birumerah.kiostix.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.birumerah.kiostix.helper.RestExecutor;

@Service("scheduleService")
public class ScheduleService {

	private static final Logger logger = LoggerFactory.getLogger(ScheduleService.class);
	
	private String authKey = "a2lvc3RpeEFQSTAxMDMwNDIwMTg=";
	
	private String SCHEDULE_URL = "http://devapi.kiostix.com/schedule";
	
	public String getSchedulesByEventId(Map paramMap){
		logger.debug(">>call getSchedulesByEventId");
		
		RestExecutor exec = new RestExecutor();
		return exec.executePOST(SCHEDULE_URL, paramMap, authKey);
		
	}	

}
