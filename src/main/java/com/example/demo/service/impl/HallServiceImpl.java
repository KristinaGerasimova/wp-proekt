package com.example.demo.service.impl;

import com.example.demo.model.Category;
import com.example.demo.model.Dto.HallDto;
import com.example.demo.model.Hall;
import com.example.demo.model.Trainer;
import com.example.demo.model.User;
import com.example.demo.model.exception.CategoryNotFoundException;
import com.example.demo.model.exception.HallNotFoundException;
import com.example.demo.model.exception.TrainerNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.HallRepository;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.HallService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;
    private final CategoryRepository categoryRepository;
    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;

    public HallServiceImpl(HallRepository hallRepository, CategoryRepository categoryRepository, TrainerRepository trainerRepository, UserRepository userRepository) {
        this.hallRepository = hallRepository;
        this.categoryRepository = categoryRepository;
        this.trainerRepository = trainerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Hall> findAll() {
        return this.hallRepository.findAll();
    }

    @Override
    public Optional<Hall> findById(Long id) {
        return this.hallRepository.findById(id);
    }

    @Override
    public Optional<Hall> findByName(String name) {
        return this.hallRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Hall> save(String name, Double price, Integer quantity, Long category, Long trainer, String img) {
        Trainer trainer1= this.trainerRepository.findById(trainer)
                .orElseThrow(()->new TrainerNotFoundException(trainer));
        Category category1=this.categoryRepository.findById(category)
                .orElseThrow(()->new CategoryNotFoundException(category));

        this.hallRepository.deleteByName(name);
        return Optional.of(this.hallRepository.save(new Hall(name, price, quantity, category1, trainer1, img)));
    }

    @Override
    public Optional<Hall> save(HallDto hallDto) {
        Trainer trainer1= this.trainerRepository.findById(hallDto.getTrainer())
                .orElseThrow(()->new TrainerNotFoundException(hallDto.getTrainer()));
        Category category1=this.categoryRepository.findById(hallDto.getCategory())
                .orElseThrow(()->new CategoryNotFoundException(hallDto.getCategory()));

        this.hallRepository.deleteByName(hallDto.getName());
        return Optional.of(this.hallRepository.save(new Hall(hallDto.getName(), hallDto.getPrice(), hallDto.getQuantity(), category1, trainer1, hallDto.getImg())));
    }

    @Override
    @Transactional
    public Optional<Hall> edit(Long id, String name, Double price, Integer quantity, Long category, Long trainer, String img) {
        Hall hall = this.hallRepository.findById(id).orElseThrow(()-> new HallNotFoundException(id));

        hall.setName(name);
        hall.setPrice(price);
        hall.setQuantity(quantity);
        hall.setImg(img);

        Category category1 = this.categoryRepository.findById(category)
                .orElseThrow(()-> new CategoryNotFoundException(category));
        hall.setCategory(category1);

        Trainer trainer1 = this.trainerRepository.findById(trainer)
                .orElseThrow(()-> new TrainerNotFoundException(trainer));
        hall.setTrainer(trainer1);

        return Optional.of(this.hallRepository.save(hall));

    }

    @Override
    public Optional<Hall> edit(Long id, HallDto hallDto) {
       Hall hall = this.hallRepository.findById(id).orElseThrow(()-> new HallNotFoundException(id));

       hall.setName(hallDto.getName());
       hall.setPrice(hallDto.getPrice());
       hall.setQuantity(hallDto.getQuantity());
       hall.setImg(hallDto.getImg());

        Category category1 = this.categoryRepository.findById(hallDto.getCategory())
                .orElseThrow(()-> new CategoryNotFoundException(hallDto.getCategory()));
        hall.setCategory(category1);

        Trainer trainer1 = this.trainerRepository.findById(hallDto.getTrainer())
                .orElseThrow(()-> new TrainerNotFoundException(hallDto.getTrainer()));
        hall.setTrainer(trainer1);

        return Optional.of(this.hallRepository.save(hall));


    }

    @Override
    public void deleteById(Long id) {
        this.hallRepository.deleteById(id);
    }
}
