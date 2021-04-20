package com.example.demo.service.impl;

import com.example.demo.model.Trainer;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.service.TrainerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;

    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public Optional<Trainer> findById(Long id) {
        return this.trainerRepository.findById(id);
    }

    @Override
    public List<Trainer> findAll() {
        return this.trainerRepository.findAll();
    }

    @Override
    public Optional<Trainer> save(String name, String address, String img) {
        return Optional.of(this.trainerRepository.save(new Trainer(name, address, img)));
    }

    @Override
    public void deleteById(Long id) {
        this.trainerRepository.deleteById(id);
    }
}
