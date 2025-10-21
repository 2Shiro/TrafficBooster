package com.andgivemarketing.trafficbooster.context.project.entity;

import com.andgivemarketing.trafficbooster.context.project.enums.ProjectStatus;
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

    @Enumerated(EnumType.STRING) // DB에 문자열로 저장
    @Column(name = "status")
    private ProjectStatus status;

    @Column(name = "daily_target_traffic_count")
    private Long dailyTargetTrafficCount;

    @Column(name = "total_target_traffic_count")
    private Long totalTargetTrafficCount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
