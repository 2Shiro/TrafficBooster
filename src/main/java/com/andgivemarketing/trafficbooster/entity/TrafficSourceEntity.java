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
@Table(name = "traffic_source")
public class TrafficSourceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "traffic_source_path")
    private String trafficSourcePath;

    @Column(name = "use_scroll")
    private boolean useScroll;

    @Column(name = "stay_duration_time")
    private Long stayDurationTime;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
