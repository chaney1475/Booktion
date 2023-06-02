package com.project.Booktion.service;

import com.project.Booktion.model.Address;
import com.project.Booktion.model.User;
import com.project.Booktion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    public User authenticateUser(String username, String password) {
        return null;
    }

    public User getUser(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) return user.get();
        return null;
    }

    public User registerUser(User userDto) {
        return null;
    }

    public User getUserById(String userId) {
        return null;
    }

    public void updateUser(String userId, User user) {
    }

    public void deleteUser(String userId) {
    }

}
