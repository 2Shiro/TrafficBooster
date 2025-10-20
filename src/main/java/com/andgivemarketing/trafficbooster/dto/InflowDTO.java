package com.andgivemarketing.trafficbooster.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InflowDTO {

    private Long id;

    private Long projectId;

    private String inflowPath;

    private Long hasScroll;

    private Long durationTime;

    private LocalDateTime createAt;

}
