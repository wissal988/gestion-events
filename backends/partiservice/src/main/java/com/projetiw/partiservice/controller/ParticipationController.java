package com.projetiw.partiservice.controller;

import com.projetiw.partiservice.DTO.ParticipationRequestDTO;
import com.projetiw.partiservice.model.Participation;
import com.projetiw.partiservice.service.ParticipationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participation")
@CrossOrigin(origins = "*")
public class ParticipationController {

    private final ParticipationService service;

    public ParticipationController(ParticipationService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Participation> getAll() {
        return service.getAll();
    }

    @PostMapping("/subscribe")
    public Participation subscribe(@RequestParam Long userId, @RequestParam Long eventId) {
        return service.subscribe(userId, eventId);
    }

    @DeleteMapping("/unsubscribe")
    public boolean unsubscribe(@RequestParam Long userId, @RequestParam Long eventId) {
        return service.unsubscribe(userId, eventId);
    }

    @GetMapping("/isSubscribed")
    public boolean isSubscribed(@RequestParam Long userId, @RequestParam Long eventId) {
        return service.isSubscribed(userId, eventId);
    }

    @GetMapping("/user/{userId}")
    public List<Participation> listByUser(@PathVariable Long userId) {
        return service.listByUser(userId);
    }

    @GetMapping("/event/{eventId}")
    public List<Participation> listByEvent(@PathVariable Long eventId) {
        return service.listByEvent(eventId);
    }

    @PutMapping("/{id}/status")
    public Participation updateStatus(@PathVariable Long id, @RequestParam int value) {
        return service.updateStatus(id, value);
    }

    @GetMapping("/all/details")
    public List<ParticipationRequestDTO> allDetails() {
        return service.listRequestsWithDetails(); // sans eventId
    }

}
