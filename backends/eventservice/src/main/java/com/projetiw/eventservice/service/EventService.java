package com.projetiw.eventservice.service;

import com.projetiw.eventservice.model.Event;
import com.projetiw.eventservice.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public List<Event> getAllEvents() {
        return repository.findAll();
    }

    public Event createEvent(Event event) {
        return repository.save(event);
    }

    public Event updateEvent(Long id, Event updatedEvent) {
        Event event = repository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
        event.setTitle(updatedEvent.getTitle());
        event.setDate(updatedEvent.getDate());
        event.setLocation(updatedEvent.getLocation());
        event.setDescription(updatedEvent.getDescription());
        event.setSupport(updatedEvent.getSupport());
        return repository.save(event);
    }

    public void deleteEvent(Long id) {
        repository.deleteById(id);
    }
    
    public Event getEventById(Long id) {
    return repository.findById(id).orElse(null);
}
}
