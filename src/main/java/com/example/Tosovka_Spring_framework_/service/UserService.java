package com.example.Tosovka_Spring_framework_.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Tosovka_Spring_framework_.entity.Role;
import com.example.Tosovka_Spring_framework_.entity.User;
import com.example.Tosovka_Spring_framework_.repositories.RoleRepository;
import com.example.Tosovka_Spring_framework_.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(String username, String password, String email) {
        User user = new User();
        Role role = roleRepository.findByName("ROLE_USER");
        if (userRepository.findByUsername(username) != null || userRepository.findByEmail(email) != null)
            return false;

        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setRole(role);
        userRepository.save(user);
        return true;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
}
