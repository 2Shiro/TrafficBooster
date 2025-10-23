package com.andgivemarketing.trafficboostercore.context.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TrafficSourceDTO {

    private Long id;

    private Long projectId;

    private String trafficSourcePath;

    private boolean useScroll;

    private Long stayDurationTime;

    private LocalDateTime createdAt;

}
