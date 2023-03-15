package com.dannielSouza.progDashboard.services;

import com.dannielSouza.progDashboard.models.Task;
import com.dannielSouza.progDashboard.models.User;
import com.dannielSouza.progDashboard.repositories.CompanyRepository;
import com.dannielSouza.progDashboard.repositories.TaskRepository;
import com.dannielSouza.progDashboard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;

    //CREATE NEW TASK
    public ResponseEntity<Map<String, String>> register(Task task){
        Map<String, String> message = new HashMap<>();

        Optional<User> user = userRepository.findById(task.getIdUser());
        if(user.isEmpty()){
            message.put("error", "Esse usuário não existe");
            return ResponseEntity.ok().body(message);
        }
        if(user.get().getIdCompany() != task.getIdCompany()){
            message.put("error", "Esse usuário não pertence a essa empresa");
            return ResponseEntity.ok().body(message);
        }

        Task newTask = new Task(task.getIdUser(), task.getIdCompany(), task.getName(), task.getDescription(), task.getTaskStatus(), task.getExpirationDate());
        repository.save(newTask);

        message.put("message", "Tarefa criada com sucesso!");
        return ResponseEntity.ok().body(message);
    }


    // DELETE TASK
    public ResponseEntity<Map<String, String>> deleteTask(Long id){

        Map<String, String> message = new HashMap<>();
        Optional<Task> task = repository.findById(id);

        if(task.isEmpty()){
            message.put("error", "Esta tarefa não existe");
            return ResponseEntity.badRequest().body(message);
        }

        repository.delete(task.get());
        message.put("message", "Tarefa excluida com sucesso!");
        return ResponseEntity.ok().body(message);
    }

}
