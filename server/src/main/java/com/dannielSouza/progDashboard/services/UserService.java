package com.dannielSouza.progDashboard.services;


import com.dannielSouza.progDashboard.models.Company;
import com.dannielSouza.progDashboard.models.Task;
import com.dannielSouza.progDashboard.models.User;
import com.dannielSouza.progDashboard.models.DTO.UserDTO;
import com.dannielSouza.progDashboard.repositories.CompanyRepository;
import com.dannielSouza.progDashboard.repositories.TaskRepository;
import com.dannielSouza.progDashboard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private TaskRepository taskRepository;

    // REGISTER NEW USER
    public ResponseEntity<Map<String, String>> register(User user) {
        Map<String, String> message = new HashMap<>();

        Optional<Company> company = companyRepository.findById(user.getIdCompany());
        if(company.isEmpty()){
            message.put("error", "Essa empresa não existe");
            return ResponseEntity.ok().body(message);
        }

        Optional<User> existingUser = repository.findByUsername(user.getUsername());
        if(existingUser.isPresent()){
            message.put("error", "Usuário indiponivel.");
            return ResponseEntity.ok().body(message);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User newPerson = new User(user.getName(), user.getUsername(), encoder.encode(user.getPassword()), user.getIdCompany());
        repository.save(newPerson);

        message.put("message", "Pessoa criada com sucesso!");
        return ResponseEntity.ok().body(message);
    }


    // GET USER'S INFO
    public ResponseEntity<User> findById(Long id){
        User user = repository.findById(id).orElseThrow();
        return ResponseEntity.ok().body(user);
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
