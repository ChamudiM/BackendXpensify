package com.devops.Backend_Xpensify.repo;

import com.devops.Backend_Xpensify.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//inside brackets, we give the class which represent the tables and the type of the primary key
public interface UserRepo extends JpaRepository<Users, Integer> {
    //we used interface so it gives all the methods
    Users findByUsername(String username);
}
