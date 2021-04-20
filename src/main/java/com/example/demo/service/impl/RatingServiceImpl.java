package com.example.demo.service.impl;

import com.example.demo.model.Hall;
import com.example.demo.model.Rating;
import com.example.demo.model.User;
import com.example.demo.model.exception.UserNotFoundException;
import com.example.demo.repository.HallRepository;
import com.example.demo.repository.RatingRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RatingService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final HallRepository hallRepository;

    public RatingServiceImpl(RatingRepository ratingRepository, UserRepository userRepository, HallRepository hallRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.hallRepository = hallRepository;
    }

    @Override
    public Optional<Rating> create(String username, Long hallId, String gradeChar) {
        User user = this.userRepository.findByUsername(username).get();
        Hall hall = this.hallRepository.findById(hallId).get();
        Rating rating = new Rating(user, hall, gradeChar.charAt(0));
        return Optional.of(this.ratingRepository.save(rating));
    }

    @Override
    public Optional<Rating> update(Long id, Character gradeChar) {
        Rating rating = this.findById(id).get().orElseThrow(()->new UserNotFoundException("Grade not found"));
        rating.setGrade(gradeChar);
        return Optional.of(this.ratingRepository.save(rating));
    }

    @Override
    public Optional<Rating> delete(Long id) {
        Rating rating = this.findById(id).get().orElseThrow(()->new UserNotFoundException("Grade not found"));
        this.ratingRepository.delete(rating);
        return Optional.of(rating);
    }

    @Override
    public Optional<Optional<Rating>> findById(Long id) {
        return Optional.of(this.ratingRepository.findById(id));
    }

    @Override
    public List<Rating> findAllByHallId(Long id) {
        return this.ratingRepository.findAllByHall_Id(id);
    }

    @Override
    public List<Rating> findAllByGradeAndHall_Id(Character grade, Long id) {
        return this.ratingRepository.findAllByGradeAndHall_Id(grade, id);
    }

}
