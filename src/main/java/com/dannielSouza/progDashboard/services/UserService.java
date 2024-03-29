package com.dannielSouza.progDashboard.services;


import com.dannielSouza.progDashboard.JWTconfig.JwtService;
import com.dannielSouza.progDashboard.models.Task;
import com.dannielSouza.progDashboard.models.User;
import com.dannielSouza.progDashboard.models.DTO.UserDTO;
import com.dannielSouza.progDashboard.repositories.CompanyRepository;
import com.dannielSouza.progDashboard.repositories.TaskRepository;
import com.dannielSouza.progDashboard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;



    // GET USER'S INFO
    public ResponseEntity<User> findById(Long id){
        User user = repository.findById(id).orElseThrow();
        return ResponseEntity.ok().body(user);
    }


    // USER LOGIN
    public ResponseEntity<Map<String, String>> login(User user){
        Map<String, String> message = new TreeMap<>();
        Optional<User> thisUser = repository.findByUsername(user.getUsername());

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            var jwtToken = jwtService.generateUserToken(user);

            message.put("token", jwtToken);
            message.put("id", thisUser.get().getId()+"");
            message.put("username", thisUser.get().getUsername());
            message.put("name",  thisUser.get().getName());
            message.put("role",  thisUser.get().getRole()+"");

            LocalDateTime lastLogin = LocalDateTime.now();

            thisUser.get().setLastLogin(lastLogin);
            repository.save(thisUser.get());

            return ResponseEntity.ok().body(message);

        } catch (Exception e) {
            message.put("error", "Usuário ou senha inválido.");
            return ResponseEntity.badRequest().body(message);
        }
    }


    // GET USER INFO
    public Object getInfo(Long id){
        Optional<User> user = repository.findById(id);
        UserDTO userDTO = userDTOMapper(user.get());

        if(user.isPresent()){
            return ResponseEntity.ok().body(userDTO);
        }
        Map<String, String> message = new TreeMap<>();
        message.put("error", "Este usuário não existe.");
        return ResponseEntity.badRequest().body(message);
    }


    // CHANGE USER'S TASK STATUS
    public ResponseEntity<Map<String, String>> changeTaskStatus(Long id, Task task){
        Map<String, String> message = new TreeMap<>();
        Optional<Task> updateTask = taskRepository.findById(task.getId());

        if(updateTask.isEmpty()){
            message.put("error", "Essa tarefa não existe.");
            return ResponseEntity.badRequest().body(message);
        }
        if(updateTask.get().getIdUser() != id){
            message.put("error", "Essa tarefa não pertence a esse usuário.");
            return ResponseEntity.badRequest().body(message);
        }

        LocalDateTime updateTime = LocalDateTime.now();

        updateTask.get().setUpdateDate(updateTime);
        updateTask.get().setTaskStatus(task.getTaskStatus());
        taskRepository.save(updateTask.get());

        message.put("messasge", "Status da tarefa alterado com sucesso.");
        return ResponseEntity.ok().body(message);
    }






    // TRANSFORM A USER TO USERDTO
    public UserDTO userDTOMapper(User user) {
        Optional<List<Task>> tasksList = taskRepository.findAllByIdUser(user.getId());
        return new UserDTO(
                user.getId(),
                user.getImage(),
                user.getName(),
                user.getUsername(),
                user.getOffice(),
                user.getIdCompany(),
                user.getRole(),
                user.getLastLogin(),
                tasksList.get()
        );
    }
}
