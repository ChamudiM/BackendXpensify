package com.devops.Backend_Xpensify.service;

import com.devops.Backend_Xpensify.model.UserPrincipal;
import com.devops.Backend_Xpensify.model.Users;
import com.devops.Backend_Xpensify.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    @CrossOrigin
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //to loadUserByUsername we have to connect with the database so we have to use repo layer
        Users user = repo.findByUsername(username);
        if(user == null){
            System.out.println("User Not Found");
            throw new UsernameNotFoundException(("user not found"));
        }
        //as userdetails is an interface so we have to look for a class that implements this
        //to get username and passwords we need USers class so we pass user
        return new UserPrincipal(user);
    }
}
