package com.andgivemarketing.trafficbooster.context.trafficsourcerecord.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TrafficSourceRecordDTO {

    private Long id;

    private Long projectId;

    private Long trafficSourceId;

    private String userAgent;

    private Long ip;

    private LocalDateTime createAt;

}
