package com.example.Tosovka_Spring_framework_.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.Tosovka_Spring_framework_.dto.TypeDto;
import com.example.Tosovka_Spring_framework_.entity.Type;

@Mapper
public interface TypeMapper {
    
    TypeMapper INSTANCE = Mappers.getMapper(TypeMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    TypeDto toDto(Type type);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    Type toEntity(TypeDto typeDto);

}
