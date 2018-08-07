package com.birumerah.kiostix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.birumerah.kiostix.exception.BusinessException;
import com.birumerah.kiostix.service.EventService;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService service;
  
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public String getEvents() throws BusinessException {
        return service.getEvents();
    }
	
}
