package com.project.Booktion.service;

import com.project.Booktion.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User authenticateUser(String username, String password) {
        return null;
    }

    public User getUser(String userId) {
        User user = new User();
        return user;
    }
}
