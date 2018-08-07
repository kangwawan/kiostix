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
import com.birumerah.kiostix.service.BookingService;

@RestController
@RequestMapping("/transaction")
public class BookingController {

	@Autowired
	private BookingService service;
	
	/*paramMap=
		{
		  "token":"{{token}}",
		  "item":{
		    "pw934umzpl":"2"
		  },
		  "time":"pwz377bzpl",
		  "name":"Test API",
		  "email":"apidev@kiostix.io",
		  "gender":"Male",
		  "phone":"085814174847",
		  "dob":"1996-03-06"
		}
	*/	
    @ResponseBody
    @RequestMapping(value = "/booking", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public String findTicket (@RequestBody Map paramMap) throws BusinessException {
    	return service.addBooking(paramMap);
    }
	
}
