package com.example.Tosovka_Spring_framework_.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private RoleDto role;
}
