package com.example.Tosovka_Spring_framework_.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Tosovka_Spring_framework_.entity.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    @SuppressWarnings("null")
    Optional<Type> findById(Long id);
    Type findByName(String name);
}
