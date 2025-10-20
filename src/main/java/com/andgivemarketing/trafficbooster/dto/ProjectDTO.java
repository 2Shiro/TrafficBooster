package com.andgivemarketing.trafficbooster.dto;

import com.andgivemarketing.trafficbooster.enums.ProjectState;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectDTO {

    private Long id;

    private String name;

    private String targetAddress;

    private LocalDateTime startedAt;

    private ProjectState state;

    private Long dailyTargetInflow;

    private Long totalTargetInflow;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
