package com.example.Tosovka_Spring_framework_.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Tosovka_Spring_framework_.dto.TypeDto;
import com.example.Tosovka_Spring_framework_.entity.Type;
import com.example.Tosovka_Spring_framework_.mapper.TypeMapper;
import com.example.Tosovka_Spring_framework_.repositories.TypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TypeService {
    
    @Autowired
    private final TypeRepository typeRepository;


    public void saveType(TypeDto typeDto) {
        Type type = TypeMapper.INSTANCE.toEntity(typeDto);
        typeRepository.save(type);
    }
    
    public void deleteType(long id) {
        typeRepository.deleteById(id);
    }

    public List<TypeDto> getAllTypes() {
        return typeRepository.findAll().stream().map(TypeMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

}
