package com.dannielSouza.progDashboard.controllers;

import com.dannielSouza.progDashboard.models.Task;
import com.dannielSouza.progDashboard.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService service;


    @PostMapping("/create")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Map<String, String>> create(@RequestBody Task task){
        return service.register(task);
    }


    @DeleteMapping("delete/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id){
        return service.deleteTask(id);
    }
}
