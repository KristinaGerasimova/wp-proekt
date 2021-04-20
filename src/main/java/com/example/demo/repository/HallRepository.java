package com.example.demo.repository;

import com.example.demo.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {
    Optional<Hall> findByName(String name);
    void deleteByName(String name);
}
