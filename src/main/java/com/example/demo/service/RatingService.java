package com.example.demo.service;

import com.example.demo.model.Rating;

import java.util.List;
import java.util.Optional;

public interface RatingService {

    Optional<Rating> create(String username, Long hallId, String grade);
    Optional<Rating> update(Long id, Character grade);
    Optional<Rating> delete(Long id);
    Optional<Optional<Rating>> findById(Long id);
    List<Rating> findAllByHallId(Long id);
    List<Rating> findAllByGradeAndHall_Id(Character grade, Long id);
}
