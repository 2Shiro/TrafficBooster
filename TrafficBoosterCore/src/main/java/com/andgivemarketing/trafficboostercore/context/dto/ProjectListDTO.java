package com.andgivemarketing.trafficboostercore.context.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectListDTO {
    private Long id;

    private String projectName;

    private String status;

    private String trafficType;

    private Long dailyTargetTrafficCount;

    private Long totalTargetTrafficCount;

    private Long currentDailyCount;

    private Long currentTotalCount;
}