package com.example.Tosovka_Spring_framework_.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Set<Role> roles = new HashSet<>();

        if (userRepository.findByUsername(username) != null || userRepository.findByEmail(email) != null)
            return false;

        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setRoles(roles);
        userRepository.save(user);
        return true;
    }

}
