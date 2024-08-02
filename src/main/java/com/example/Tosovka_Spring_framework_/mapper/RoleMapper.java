package com.example.Tosovka_Spring_framework_.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.Tosovka_Spring_framework_.dto.RoleDto;
import com.example.Tosovka_Spring_framework_.entity.Role;

@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Mapping(source = "role_id", target = "id")
    @Mapping(source = "name", target = "name")
    RoleDto toRoleDto(Role role);

    @Mapping(source = "id", target = "role_id")
    @Mapping(source = "name", target = "name")
    Role toRoleEntity(RoleDto roleDto);
}
