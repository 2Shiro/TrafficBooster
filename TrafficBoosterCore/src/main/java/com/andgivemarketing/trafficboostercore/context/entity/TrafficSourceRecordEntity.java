package com.andgivemarketing.trafficboostercore.context.entity;

import com.andgivemarketing.trafficboostercore.context.dto.UserAgent;
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
@Table(name = "traffic_source_record")
public class TrafficSourceRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "traffic_source_id")
    private Long trafficSourceId;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_agent")
    private UserAgent userAgent;

    @Column(name = "ip")
    private String ip;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
