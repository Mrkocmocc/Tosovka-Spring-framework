package com.example.Tosovka_Spring_framework_.service;

import java.security.Principal;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Tosovka_Spring_framework_.dto.UserDto;
import com.example.Tosovka_Spring_framework_.entity.Role;
import com.example.Tosovka_Spring_framework_.entity.User;
import com.example.Tosovka_Spring_framework_.mapper.UserMapper;
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

    public boolean saveUser(UserDto userDto, String password) {
        User user = UserMapper.INSTANCE.toEntity(userDto);
        Role role = roleRepository.findByName("ROLE_USER");
        if (userRepository.findByUsername(user.getUsername()) != null || userRepository.findByEmail(user.getEmail()) != null)
            return false;

        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        userRepository.save(user);
        return true;
    }

    public boolean updateUser(Long id, String username, String password, String email) {
        User user = userRepository.findById(id).get();
        if (userRepository.findByUsername(username) != null || userRepository.findByEmail(email) != null)
            return false;

        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        return true;
    }

    public UserDto getUserById(long id) {
        return UserMapper.INSTANCE.toDto(userRepository.findById(id).get());
    }

    public UserDto getUserByUsername(String username) {
        return UserMapper.INSTANCE.toDto(userRepository.findByUsername(username));
    }

    public UserDto getUserByPrincipal(Principal principal) {
        if (principal == null)
            return new UserDto();
        return UserMapper.INSTANCE.toDto((userRepository.findByUsername(principal.getName())));
    }
}
