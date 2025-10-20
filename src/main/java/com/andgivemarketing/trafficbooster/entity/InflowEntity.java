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
@Table(name = "inflow")
public class InflowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "inflow_path")
    private String inflowPath;

    @Column(name = "has_scroll")
    private Long hasScroll;

    @Column(name = "duration_time")
    private Long durationTime;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
