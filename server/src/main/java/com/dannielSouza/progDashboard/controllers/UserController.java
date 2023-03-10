package com.dannielSouza.progDashboard.controllers;

import com.dannielSouza.progDashboard.models.Company;
import com.dannielSouza.progDashboard.models.DTO.UserDTO;
import com.dannielSouza.progDashboard.models.User;
import com.dannielSouza.progDashboard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user){
        return service.login(user);
    }


    @GetMapping("{id}")
    public Object userInfo(@PathVariable Long id){
        return service.getInfo(id);
    }

}
