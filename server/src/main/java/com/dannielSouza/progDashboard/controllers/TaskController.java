package com.dannielSouza.progDashboard.controllers;

import com.dannielSouza.progDashboard.models.Task;
import com.dannielSouza.progDashboard.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService service;


    @PostMapping("/register")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Map<String, String>> create(@RequestBody Task task){
        return service.register(task);
    }
}
