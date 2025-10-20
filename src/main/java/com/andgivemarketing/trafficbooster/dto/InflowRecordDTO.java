package com.andgivemarketing.trafficbooster.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InflowRecordDTO {

    private Long id;

    private Long projectId;

    private Long inflowId;

    private String userAgent;

    private Long ip;

    private LocalDateTime createAt;

}
