package com.example.Tosovka_Spring_framework_.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Tosovka_Spring_framework_.entity.Type;
import com.example.Tosovka_Spring_framework_.repositories.TypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TypeService {
    
    @Autowired
    private final TypeRepository typeRepository;


    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }

}
