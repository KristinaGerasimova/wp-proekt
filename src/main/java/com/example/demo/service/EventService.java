package com.example.demo.service;

import com.example.demo.model.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    List<Event> findAllByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Event> findAll();
    Event save(Event event);
    void deleteEvent(Event event);
    List<Event> findAllByUser(String username);
}
