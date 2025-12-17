package com.projetiw.eventservice.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventDTO {

    private Long id;
    private String title;
    private String description;
    private String location;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private Double price;
    private String mediaUrl;

    public EventDTO() {}

    public EventDTO(Long id, String title, String description, String location,
                    LocalDate eventDate, LocalTime eventTime,
                    Double price, String mediaUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.price = price;
        this.mediaUrl = mediaUrl;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public LocalDate getEventDate() { return eventDate; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }

    public LocalTime getEventTime() { return eventTime; }
    public void setEventTime(LocalTime eventTime) { this.eventTime = eventTime; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getMediaUrl() { return mediaUrl; }
    public void setMediaUrl(String mediaUrl) { this.mediaUrl = mediaUrl; }
}
