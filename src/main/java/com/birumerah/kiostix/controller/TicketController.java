package com.birumerah.kiostix.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.birumerah.kiostix.exception.BusinessException;
import com.birumerah.kiostix.service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {

	@Autowired
	private TicketService service;
	
    //paramMap = {"schedule_id":"pw23771apa"}
    @ResponseBody
    @RequestMapping(value = "/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public String findTicket (@RequestBody Map paramMap) throws BusinessException {
    	return service.getTicket(paramMap);
    }

    //paramMap = {"order_no":12345678910}
    @ResponseBody
    @RequestMapping(value = "/redeemticket", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public String redeemTicket (@RequestBody Map paramMap) throws BusinessException {
    	return service.redeemTicket(paramMap);
    }
    
    //paramMap = {"order_no":12345678910}
    @ResponseBody
    @RequestMapping(value = "/redeem", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public String redeem(@RequestBody Map paramMap) throws BusinessException {
    	return service.redeem(paramMap);
    }

    //paramMap = {"order_no":12345678910}
    @ResponseBody
    @RequestMapping(value = "/redeemconfirm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public String redeemConfirmation(@RequestBody Map paramMap) throws BusinessException {
    	return service.redeemConfirmation(paramMap);
    }
   
}
