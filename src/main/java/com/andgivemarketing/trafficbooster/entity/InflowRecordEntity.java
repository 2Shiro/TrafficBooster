package com.andgivemarketing.trafficbooster.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inflow_record")
public class InflowRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "inflow_id")
    private Long inflowId;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "ip")
    private Long ip;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
