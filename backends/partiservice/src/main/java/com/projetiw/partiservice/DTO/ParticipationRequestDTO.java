package com.projetiw.partiservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParticipationRequestDTO {

    private Long participationId;

    private Long userId;
    private String userName;

    private Long eventId;
    private String eventTitle;

    private int status;
}
