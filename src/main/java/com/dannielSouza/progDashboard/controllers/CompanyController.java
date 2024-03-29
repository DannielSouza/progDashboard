package com.dannielSouza.progDashboard.controllers;

import com.dannielSouza.progDashboard.models.Company;
import com.dannielSouza.progDashboard.models.DTO.CompanyDTO;
import com.dannielSouza.progDashboard.models.Task;
import com.dannielSouza.progDashboard.models.User;
import com.dannielSouza.progDashboard.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService service;


    @PostMapping("/register")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Map<String, String>> register(@RequestBody Company company){
        return  service.register(company);
    }


    @PostMapping("/login")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Map<String, String>> login(@RequestBody Company company){
        return service.login(company);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<CompanyDTO> companyInfo(@PathVariable Long id){
        return  service.getCompanyInfo(id);
    }


    @GetMapping("getusers/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @CrossOrigin("http://localhost:3000")
    public Object getAllUsersCompany(@PathVariable Long id){
        return  service.getAllUsers(id);
    }


    @GetMapping("gettasks/{id}")
    @CrossOrigin("http://localhost:3000")
    public Object getAllTasksCompany(@PathVariable Long id){
        return  service.getAllTasks(id);
    }


    @PostMapping("/createUser")
    @PreAuthorize("hasAuthority('ADMIN')")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Map<String, String>> createUser(@RequestBody User user){
        return  service.createUser(user);
    }


    @DeleteMapping("/deleteAllUser/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Map<String, String>> deleteUserAndTasks(@PathVariable Long id){
        return  service.deleteUserAndTasks(id);
    }

    @DeleteMapping("/deleteUser/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id){
        return  service.deleteUser(id);
    }

}
