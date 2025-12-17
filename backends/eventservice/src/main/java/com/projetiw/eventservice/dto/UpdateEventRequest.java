package com.projetiw.eventservice.dto;

import lombok.Data;

@Data
public class UpdateEventRequest {
    private String title;
    private String date;
    private String location;
    private String description;
    private SupportDTO support;
}
