package com.andgivemarketing.trafficbooster.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectDTO {

    private Long id;

    private String name;

    private LocalDateTime startedAt;

    private Enum state;

    private Long dailyTargetInflow;

    private Long totalTargetInflow;

    private String targetAddress;

    private LocalDateTime createAt;

}
