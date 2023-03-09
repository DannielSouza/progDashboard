package com.dannielSouza.progDashboard.controllers;

import com.dannielSouza.progDashboard.models.User;
import com.dannielSouza.progDashboard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;


    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody User user){
        return  service.register(user);
    }
}
