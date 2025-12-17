package com.projetiw.eventservice.dto;

import lombok.Data;

@Data
public class CreateEventRequest {
    private String title;
    private String date;
    private String location;
    private String description;
    private SupportDTO support;
}

@Data
class SupportDTO {
    private String type;
    private String url;
}
