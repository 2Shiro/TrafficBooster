package com.andgivemarketing.trafficboostercore.context.entity;


import com.andgivemarketing.trafficboostercore.context.dto.ProjectStatus;
import com.andgivemarketing.trafficboostercore.context.dto.TrafficType;
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

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProjectStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "traffic_type")
    private TrafficType trafficType;

    @Column(name = "daily_target_traffic_count")
    private Long dailyTargetTrafficCount;

    @Column(name = "total_target_traffic_count")
    private Long totalTargetTrafficCount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
