package com.andgivemarketing.trafficbooster.context.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectDTO {

    private Long id;

    private String name;

    private String targetAddress;

    private LocalDateTime startedAt;

    private ProjectStatus status;

    private Long dailyTargetTrafficCount;

    private Long totalTargetTrafficCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
