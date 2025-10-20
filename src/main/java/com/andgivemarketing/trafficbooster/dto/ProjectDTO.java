package com.andgivemarketing.trafficbooster.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectDTO {

    private Long id;

    private String name;

    private String targetAddress;

    private LocalDateTime startedAt;

    private Long state;

    private Long dailyTargetInflow;

    private Long totalTargetInflow;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
