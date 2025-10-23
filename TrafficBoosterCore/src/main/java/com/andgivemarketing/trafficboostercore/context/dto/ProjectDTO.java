package com.andgivemarketing.trafficboostercore.context.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectDTO {

    private Long id;

    private String name;

    private String targetAddress;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private ProjectStatus status;

    private TrafficType trafficType;

    private Long dailyTargetTrafficCount;

    private Long totalTargetTrafficCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
