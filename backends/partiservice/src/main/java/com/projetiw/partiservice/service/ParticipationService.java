package com.projetiw.partiservice.service;

import com.projetiw.partiservice.DTO.ParticipationRequestDTO;
import com.projetiw.partiservice.model.Participation;
import com.projetiw.partiservice.repository.ParticipationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipationService {

    private final ParticipationRepository repo;
    private final RestTemplate rest = new RestTemplate();

    private final NotificationService notificationService; // 👈 AJOUT

    public ParticipationService(ParticipationRepository repo, NotificationService notificationService) {
        this.repo = repo;
        this.notificationService = notificationService; // 👈 AJOUT
    }

    public List<Participation> getAll() {
        return repo.findAll();
    }

    public Participation subscribe(Long userId, Long eventId) {
        Participation existing = repo.findByUserIdAndEventId(userId, eventId);
        if (existing != null) return existing;

        Participation p = new Participation();
        p.setUserId(userId);
        p.setEventId(eventId);
        p.setStatus(0); // EN ATTENTE

        return repo.save(p);
    }

    public boolean unsubscribe(Long userId, Long eventId) {
        Participation p = repo.findByUserIdAndEventId(userId, eventId);
        if (p == null) return false;

        repo.delete(p);
        return true;
    }

    public boolean isSubscribed(Long userId, Long eventId) {
        return repo.findByUserIdAndEventId(userId, eventId) != null;
    }

    public List<Participation> listByUser(Long userId) {
        return repo.findByUserId(userId);
    }

    public List<Participation> listByEvent(Long eventId) {
        return repo.findByEventId(eventId);
    }

   public Participation updateStatus(Long id, int status) {
        Participation p = repo.findById(id).orElse(null);
        if (p == null) return null;

        p.setStatus(status);
        Participation saved = repo.save(p);

        // 🔥 Ajouter la notification automatique
        notificationService.sendNotification(
            p.getUserId(),
            "Votre demande pour l'événement " + p.getEventId() +
            " a été " + (status == 1 ? "acceptée" : status == 2 ? "refusée" : "traitée")
        );

        return saved;
    }


    public List<ParticipationRequestDTO> listRequestsWithDetails() {
        List<Participation> list = repo.findAll();

        return list.stream().map(p -> {
            String userName = "Utilisateur " + p.getUserId();
            try {
                userName = rest.getForObject(
                        "http://localhost:8081/api/user/" + p.getUserId() + "/name",
                        String.class
                );
            } catch (Exception ignored) {}

            String eventTitle = "Événement " + p.getEventId();
            try {
                eventTitle = rest.getForObject(
                        "http://localhost:8082/api/events/" + p.getEventId() + "/title",
                        String.class
                );
            } catch (Exception ignored) {}

            return new ParticipationRequestDTO(
                    p.getId(),
                    p.getUserId(),
                    userName,
                    p.getEventId(),
                    eventTitle,
                    p.getStatus()
            );
        }).collect(Collectors.toList());
    }

}
