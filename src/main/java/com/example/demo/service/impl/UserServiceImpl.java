package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        user.setPassword(hashPassword(user.getPassword()));
        if (user.getRole() == null) {
            user.setRole("AGENT");
        }
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    private String hashPassword(String plainPassword) {
        return org.springframework.security.crypto.bcrypt.BCrypt
                .hashpw(plainPassword, org.springframework.security.crypto.bcrypt.BCrypt.gensalt());
    }
}
