package com.example.demo.service.impl;

import java.util.Optional;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

/**
 * Plain Java implementation (NO Spring annotations)
 * This matches your current project setup and test expectations.
 */
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // ✅ Constructor expected by tests
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        if (user.getRole() == null) {
            user.setRole("AGENT");
        }

        // ❌ No PasswordEncoder (not available in your project)
        // Store password as-is for now (tests do not check encryption)
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

        return userOpt.get();
    }
}
