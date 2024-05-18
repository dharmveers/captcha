package com.captcha.services.Impl;

import com.captcha.model.User;
import com.captcha.repositories.UserRepo;
import com.captcha.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public String createUser(User user) {
        userRepo.save(user);
        return "User Saved Successfully";
    }
}
