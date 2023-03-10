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






    // TRANSFORM A USER TO USERDTO
    public UserDTO userDTOMapper(User user) {
        Optional<List<Task>> tasksList = taskRepository.findAllByIdUser(user.getId());
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getIdCompany(),
                tasksList.get()
        );
    }
}
