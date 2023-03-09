package com.dannielSouza.progDashboard.controllers;

import com.dannielSouza.progDashboard.models.Company;
import com.dannielSouza.progDashboard.models.DTO.CompanyDTO;
import com.dannielSouza.progDashboard.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService service;


    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Company company){
        return  service.register(company);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getAll(@PathVariable Long id){
        return  service.getCompanyInfo(id);
    }
}
