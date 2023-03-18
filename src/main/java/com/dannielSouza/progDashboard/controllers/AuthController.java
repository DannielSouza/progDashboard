package com.dannielSouza.progDashboard.controllers;

import com.dannielSouza.progDashboard.models.Company;
import com.dannielSouza.progDashboard.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;


    @PostMapping
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Map<String, String>> authUser(@RequestHeader Map<String, String> header){
        String token = header.get("token");
        Map<String, String> verified = service.autoAutheticate(token);
        return ResponseEntity.ok().body(verified);
    }
}
