package com.projetiw.eventservice.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Support {
    private String type;
    private String url;
}
