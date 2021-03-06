package com.birumerah.kiostix.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.birumerah.kiostix.helper.RestExecutor;

@Service("ticketService")
public class TicketService {
	private static final Logger logger = LoggerFactory.getLogger(TicketService.class);
	
	private String authKey = "a2lvc3RpeEFQSTAxMDMwNDIwMTg=";
	
	private String TICKET_URL = "http://devapi.kiostix.com/transaction/quota";

	private String TICKET_REDEEM_URL = "http://devapi.kiostix.com/tickets/ticketReedem";

	private String REDEEM_URL = "http://devapi.kiostix.com/tickets/redeem";

	private String REDEEM_CONFIRM_URL = "http://devapi.kiostix.com/tickets/redeemConfirmation";

	private String TICKET_TRANSACTION_URL = "http://devapi.kiostix.com/tickets/transaction";
	
    @Autowired
    private LoginService loginService;

    @Autowired
    private RestExecutor exec;
	
	public String getTicket(Map paramMap){
		logger.debug(">>call getTicket");

		LoginService login = new LoginService();
		Map loginMap = new HashMap();
		loginMap.put("email", "tbpos1@kiostix.com");
		loginMap.put("password", "admin123");
		String token = login.login(loginMap).getData().getToken();

		MultiValueMap<String, String> paramTicket = new LinkedMultiValueMap<String, String>();
		paramTicket.add("token", token);
		paramTicket.add("schedule_id", (String) paramMap.get("schedule_id"));
		
		//RestExecutor exec = new RestExecutor();
		return exec.executeFormDataPOST(TICKET_URL, paramTicket, authKey);
		
	}	

	public String redeemTicket(Map paramMap){
		logger.debug(">>call redeemTicket");

		return exec.executePOST(TICKET_REDEEM_URL, paramMap, authKey);
		
	}	

	public String redeem(Map paramMap){
		logger.debug(">>call redeem");

		return exec.executePOST(REDEEM_URL, paramMap, authKey);
		
	}	
	
	public String redeemConfirmation(Map paramMap){
		logger.debug(">>call redeemConfirmation");

		return exec.executePOST(REDEEM_CONFIRM_URL, paramMap, authKey);
		
	}	

	public String ticketTransaction(Map paramMap){
		logger.debug(">>call ticketTransaction");

		LoginService login = new LoginService();
		Map loginMap = new HashMap();
		loginMap.put("email", "tbpos1@kiostix.com");
		loginMap.put("password", "admin123");
		String token = login.login(loginMap).getData().getToken();
		
		paramMap.put("token", token);
		
		return exec.executePOST(TICKET_TRANSACTION_URL, paramMap, authKey);
		
	}	
	
}
