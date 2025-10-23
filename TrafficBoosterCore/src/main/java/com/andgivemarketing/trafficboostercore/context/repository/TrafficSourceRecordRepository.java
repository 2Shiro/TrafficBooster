package com.andgivemarketing.trafficboostercore.context.repository;


import com.andgivemarketing.trafficboostercore.context.entity.TrafficSourceRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TrafficSourceRecordRepository extends JpaRepository<TrafficSourceRecordEntity, Long>  {

    /**
     * 특정 프로젝트의 전체 레코드 수
     * @param projectId 프로젝트 id
     * @return          유입기록량
     */
    Long countByProjectId(Long projectId);

    /**
     * 특정 프로젝트의 오늘(또는 주어진 범위) 레코드 수
     * @param projectId         프로젝트 id
     * @param startInclusive
     * @param endExclusive
     * @return                  유입기록량
     */
    Long countByProjectIdAndCreatedAtBetween(Long projectId, LocalDateTime startInclusive, LocalDateTime endExclusive);

    /**
     * 해당 프로젝트에 연결된 모든 유입기록 조회
     * @param projectId 프로젝트 id
     * @return          유입기록 정보
     */
    List<TrafficSourceRecordEntity> findAllByProjectId(Long projectId);
}
