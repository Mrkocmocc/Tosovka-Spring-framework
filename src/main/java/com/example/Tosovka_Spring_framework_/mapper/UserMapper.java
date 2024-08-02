package com.example.Tosovka_Spring_framework_.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.Tosovka_Spring_framework_.dto.UserDto;
import com.example.Tosovka_Spring_framework_.entity.User;

@Mapper(uses = {RoleMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "role", target = "role")
    UserDto toDto(User user);

    @Mapping(source = "role", target = "role")
    @Mapping(target = "password", ignore = true)
    User toEntity(UserDto userDto);

}
