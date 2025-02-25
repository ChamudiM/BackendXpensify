package com.devops.Backend_Xpensify.service;

import com.devops.Backend_Xpensify.model.Users;
import com.devops.Backend_Xpensify.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users user){
        // USing RestTemplate for temporary use until implement a service registry
        String uri = "http://localhost:8080/add-user";
        RestTemplate restTemplate = new RestTemplate();

        user.setPassword(encoder.encode(user.getPassword()));

        restTemplate.postForObject(uri, user, String.class);

        return repo.save(user);
    }

    public String verify(Users user) {
        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated())
                return jwtService.generateToken(user.getUsername());
        return "fail";

    }
}
