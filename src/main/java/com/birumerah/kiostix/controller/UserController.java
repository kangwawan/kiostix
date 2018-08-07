package com.birumerah.kiostix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.birumerah.kiostix.exception.BusinessException;
import com.birumerah.kiostix.model.UserMenu;
import com.birumerah.kiostix.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
  
    @ResponseBody
    @RequestMapping(value = "/{username}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<UserMenu> getMenuByUsername(@PathVariable("username") String username) throws BusinessException {
        return userService.findMenuByUsername(username);
    }
}







