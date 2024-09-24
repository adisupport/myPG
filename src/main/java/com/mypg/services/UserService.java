package com.mypg.services;

import com.mypg.models.User;
import com.mypg.repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService  {
    UserRepo userRepo;
    UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    public void save(User user) {
        userRepo.save(user);
    }
}
