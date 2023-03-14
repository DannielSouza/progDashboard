package com.dannielSouza.progDashboard.services;

import com.dannielSouza.progDashboard.JWTconfig.JwtService;
import com.dannielSouza.progDashboard.models.Company;
import com.dannielSouza.progDashboard.models.Payload;
import com.dannielSouza.progDashboard.models.User;
import com.dannielSouza.progDashboard.repositories.CompanyRepository;
import com.dannielSouza.progDashboard.repositories.UserRepository;
import com.google.gson.Gson;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;


    // AUTHENTICATE USER BY TOKEN
    public ResponseEntity<Map<String, String>> autoAutheticate(String token){
        Map<String, String> message = new TreeMap<>();
        String[] chunks = token.split("\\.");

        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));

        Gson g = new Gson();
        Payload payloadJSON =g.fromJson(payload, Payload.class);

        Optional<Company> companyUsername = Optional.empty();
        Optional<User> userUsername = Optional.empty();;

        if(payloadJSON.getSub().contains("@")){
            try{
                companyUsername = companyRepository.findByEmail(payloadJSON.getSub());
                companyUsername.get().getEmail();
            }catch (Exception e){
                message.put("error", "Token inválido.");
                return ResponseEntity.badRequest().body(message);
            }
        }
        if(!payloadJSON.getSub().contains("@")){
            try{
                userUsername = userRepository.findByUsername(payloadJSON.getSub());
                userUsername.get().getUsername();
            }catch (Exception e){
                message.put("error", "Token inválido.");
                return ResponseEntity.badRequest().body(message);
            }
        }


        if(companyUsername.isPresent()){

            if(companyUsername.get().getEmail().equals(payloadJSON.getSub())){
                message.put("message", "Token valido.");
                message.put("id", companyUsername.get().getId()+"");
                message.put("username", companyUsername.get().getEmail());
                message.put("name", companyUsername.get().getName());
                message.put("role", companyUsername.get().getRole()+"");
                message.put("token", token);
                return ResponseEntity.ok().body(message);
            }

            message.put("error", "Token inválido.");
            return ResponseEntity.badRequest().body(message);

        }

        if(userUsername.isPresent()){

            if(userUsername.get().getUsername().equals(payloadJSON.getSub())){
                message.put("message", "Token valido.");
                message.put("id", userUsername.get().getId()+"");
                message.put("username", userUsername.get().getUsername());
                message.put("name", userUsername.get().getName());
                message.put("role", userUsername.get().getRole()+"");
                message.put("token", token);
                return ResponseEntity.ok().body(message);
            }

            message.put("error", "Token inválido.");
            return ResponseEntity.badRequest().body(message);

        }
        message.put("error", "Token inválido.");
        return ResponseEntity.badRequest().body(message);
    }
}
