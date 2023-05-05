package com.yiyang.manager.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmergencyAllDto {

    private Long emergencyEventsID;

    private int status;

    private String summary;

    private LocalDateTime startTime;

    private LocalDateTime finalTime;

    private String elderName;

    private Long elderId;

}
