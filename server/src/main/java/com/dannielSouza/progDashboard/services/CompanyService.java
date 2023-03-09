package com.dannielSouza.progDashboard.services;

import com.dannielSouza.progDashboard.JWTconfig.JwtService;
import com.dannielSouza.progDashboard.models.Company;
import com.dannielSouza.progDashboard.models.DTO.CompanyDTO;
import com.dannielSouza.progDashboard.models.User;
import com.dannielSouza.progDashboard.repositories.CompanyRepository;
import com.dannielSouza.progDashboard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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


    //CHECK IF THE COMPANY ALREADY EXIST
    public Optional<Company> checkExistingCompany(String email){
        return repository.findByEmail(email);
    };


    //REGISTER A NEW COMPANY
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
        message.put("companyEmail", newCompany.getEmail());
        message.put("companyName", newCompany.getName());
        return ResponseEntity.ok().body(message);
    };




    //GET COMPANY INFO
    public ResponseEntity<CompanyDTO> getCompanyInfo(Long id){

        Company company = repository.findById(id).orElseThrow(()->new RuntimeException("Não encontrado"));

        CompanyDTO companyDTO = companyDTOMapper(company);
        return ResponseEntity.ok().body(companyDTO);
    }









    // TRANSFORM A COMPANY TO COMPANYDTO
    private CompanyDTO companyDTOMapper(Company company) {
        Optional<List<User>> userList = userRepository.findAllByIdCompany(company.getId());
        return new CompanyDTO(
                company.getId(),
                company.getName(),
                company.getEmail(),
                userList.get()
                        .stream()
                        .map(user -> userService.userDTOMapper(user))
                        .collect(Collectors.toList())
                );

    };
}
