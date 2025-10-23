package com.andgivemarketing.trafficboostercore.context.repository;


import com.andgivemarketing.trafficboostercore.context.entity.TrafficSourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrafficSourceRepository extends JpaRepository<TrafficSourceEntity, Long> {

    /**
     * 해당 프로젝트에 연결된 모든 유입경로 조회
     * @param projectId 프로젝트 id
     * @return          유입경로 정보
     */
    List<TrafficSourceEntity> findAllByProjectId(Long projectId);

}
