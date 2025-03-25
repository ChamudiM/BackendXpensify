package com.devops.Backend_Xpensify.service;

import com.devops.Backend_Xpensify.dto.UserDTO;
import com.devops.Backend_Xpensify.model.UserPrincipal;
import com.devops.Backend_Xpensify.model.Users;
import com.devops.Backend_Xpensify.repo.UserRepo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Getter
    @Value("${app.microservice-url}")
    private String microserviceUrl;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users user){

        user.setPassword(encoder.encode(user.getPassword()));

        // Using RestTemplate for temporary use until implementing a service registry
//        String uri = "${microserviceUrl}/add-user";
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.postForObject(microserviceUrl, user, String.class);

        return repo.save(user);
    }

    public UserDTO verify(Users user) {
        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated()) {
            String token = jwtService.generateToken(user.getUsername());
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            Users authenticatedUser = userPrincipal.getUser();
            return new UserDTO(token, authenticatedUser);
        }
        return null;

    }
}
