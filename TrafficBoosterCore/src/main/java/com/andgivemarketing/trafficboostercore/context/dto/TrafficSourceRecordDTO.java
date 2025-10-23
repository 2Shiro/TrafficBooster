package com.andgivemarketing.trafficboostercore.context.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TrafficSourceRecordDTO {

    private Long id;

    private Long projectId;

    private Long trafficSourceId;

    private UserAgent userAgent;

    private String ip;

    private LocalDateTime createdAt;

}
