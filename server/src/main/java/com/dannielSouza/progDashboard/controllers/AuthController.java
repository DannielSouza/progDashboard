package com.dannielSouza.progDashboard.controllers;

import com.dannielSouza.progDashboard.models.Company;
import com.dannielSouza.progDashboard.services.AuthService;
import com.dannielSouza.progDashboard.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;


    @PostMapping
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Map<String, String>> authUser(@RequestBody String token){
        return service.autoAutheticate(token);
    }
}
