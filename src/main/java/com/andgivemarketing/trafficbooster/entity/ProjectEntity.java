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
@Table(name = "project")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "target_address")
    private String targetAddress;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "state")
    private Long state;

    @Column(name = "daily_target_inflow")
    private Long dailyTargetInflow;

    @Column(name = "total_target_inflow")
    private Long totalTargetInflow;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
