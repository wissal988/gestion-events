package com.projetiw.eventservice.controller;

import com.projetiw.eventservice.model.Event;
import com.projetiw.eventservice.repository.EventRepository;
import com.projetiw.eventservice.service.EventService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "*") // permet à Postman ou au navigateur d’envoyer des requêtes
public class EventController {

    private final EventRepository eventRepository;
    private final EventService eventService;

    public EventController(EventRepository eventRepository, EventService eventService) {
        this.eventRepository = eventRepository;
        this.eventService = eventService; // <-- AJOUT ICI
    }

    // POST : créer un événement
    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        System.out.println("POST reçu : " + event);
        Event saved = eventRepository.save(event);
        System.out.println("Événement sauvegardé avec ID : " + saved.getId());
        return saved;
    }

    // GET : récupérer tous les événements
    @GetMapping
    public List<Event> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        System.out.println("GET demandé, nombre d'événements : " + events.size());
        return events;
    }

    // GET : récupérer un événement par ID
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return eventRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT : mettre à jour un événement existant
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        return eventRepository.findById(id)
                .map(event -> {
                    event.setTitle(eventDetails.getTitle());
                    event.setDate(eventDetails.getDate());
                    event.setLocation(eventDetails.getLocation());
                    event.setDescription(eventDetails.getDescription());
                    event.setSupport(eventDetails.getSupport());
                    Event updatedEvent = eventRepository.save(event);
                    return ResponseEntity.ok(updatedEvent);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE : supprimer un événement
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // GET : récupérer seulement le titre (utile pour partiservice)
    @GetMapping("/{id}/title")
    public String getEventTitle(@PathVariable Long id) {
        return eventRepository.findById(id)
                .map(Event::getTitle)
                .orElse("Titre introuvable");
    }


    }

