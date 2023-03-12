package com.dannielSouza.progDashboard.services;

import com.dannielSouza.progDashboard.JWTconfig.JwtService;
import com.dannielSouza.progDashboard.models.Company;
import com.dannielSouza.progDashboard.models.DTO.CompanyDTO;
import com.dannielSouza.progDashboard.models.DTO.UserDTO;
import com.dannielSouza.progDashboard.models.User;
import com.dannielSouza.progDashboard.repositories.CompanyRepository;
import com.dannielSouza.progDashboard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;


    // CHECK IF THE COMPANY ALREADY EXIST
    public Optional<Company> checkExistingCompany(String email){
        return repository.findByEmail(email);
    };


    // REGISTER A NEW COMPANY
    public ResponseEntity<Map<String, String>> register(Company company){
        Map<String, String> message = new TreeMap<>();
        Optional<Company> existingCompany = checkExistingCompany(company.getEmail());

        if(existingCompany.isPresent()){
            message.put("error", "Email já em uso!");
            return ResponseEntity.badRequest().body(message);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Company newCompany = new Company(null, company.getName(), company.getEmail(), encoder.encode(company.getPassword()));
        repository.save(newCompany);
        var jwtToken = jwtService.generateCompanyToken(newCompany);

        message.put("token", jwtToken);
        message.put("companyId", newCompany.getId()+"");
        message.put("companyEmail", newCompany.getEmail());
        message.put("companyName", newCompany.getName());
        message.put("role",  newCompany.getRole()+"");
        return ResponseEntity.ok().body(message);
    };


    // COMPANY LOGIN
    public ResponseEntity<Map<String, String>> login(Company company){
        Map<String, String> message = new TreeMap<>();
        Optional<Company> thisCompany = repository.findByEmail(company.getEmail());

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(company.getEmail(), company.getPassword()));
            var jwtToken = jwtService.generateCompanyToken(company);

            message.put("token", jwtToken);
            message.put("id", thisCompany.get().getId()+"");
            message.put("username", thisCompany.get().getEmail());
            message.put("name",  thisCompany.get().getName());
            message.put("role",  thisCompany.get().getRole()+"");
            return ResponseEntity.ok().body(message);

        } catch (Exception e) {
            message.put("error", "Usuário ou senha inválido.");
            return ResponseEntity.badRequest().body(message);
        }
    }

    // GET COMPANY INFO
    public ResponseEntity<CompanyDTO> getCompanyInfo(Long id){

        Company company = repository.findById(id).orElseThrow(()->new RuntimeException("Não encontrado"));

        CompanyDTO companyDTO = companyDTOMapper(company);
        return ResponseEntity.ok().body(companyDTO);
    }


    // REGISTER A NEW COMPANY USER
    public ResponseEntity<Map<String, String>> createUser(User user) {
        Map<String, String> message = new HashMap<>();

        Optional<Company> company = repository.findById(user.getIdCompany());
        if(company.isEmpty()){
            message.put("error", "Esta empresa não existe");
            return ResponseEntity.ok().body(message);
        }

        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if(existingUser.isPresent()){
            message.put("error", "Usuário indiponivel.");
            return ResponseEntity.ok().body(message);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User newPerson = new User(user.getName(), user.getUsername(), encoder.encode(user.getPassword()), user.getIdCompany());
        userRepository.save(newPerson);

        message.put("message", "Pessoa criada com sucesso!");
        return ResponseEntity.ok().body(message);
    }


    // DELETE COMPANY USER
    public  ResponseEntity<Map<String, String>> deleteUser(Long id){
        Map<String, String> message = new HashMap<>();
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            message.put("error", "Este usuário não existe.");
            return ResponseEntity.ok().body(message);
        }

        userRepository.deleteById(id);
        message.put("message", "Usuário deletado com sucesso.");
        return ResponseEntity.ok().body(message);
    }


    // GET ALL USER'S COMPANY
    public Object getAllUsers(Long id){
        Map<String, String> message = new HashMap<>();

        Optional<List<User>> userList = userRepository.findAllByIdCompany(id);
        if(userList.get().size() < 1){
            message.put("error", "Ainda não existem usuários cadastrados.");
            return ResponseEntity.badRequest().body(message);
        }

        List<UserDTO> userDTOList = userList.get().stream().map(listItem-> userService.userDTOMapper(listItem)).collect(Collectors.toList());
        return ResponseEntity.ok().body(userDTOList);
    }






    // TRANSFORM A COMPANY TO COMPANYDTO
    private CompanyDTO companyDTOMapper(Company company) {
        Optional<List<User>> userList = userRepository.findAllByIdCompany(company.getId());
        return new CompanyDTO(
                company.getId(),
                company.getName(),
                company.getEmail(),
                company.getRole(),
                userList.get()
                        .stream()
                        .map(user -> userService.userDTOMapper(user))
                        .collect(Collectors.toList())

                );

    };
}
