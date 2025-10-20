package com.andgivemarketing.trafficbooster.repository;

import com.andgivemarketing.trafficbooster.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long>  {

    // 이름으로 프로젝트 조회
    Optional<ProjectEntity> findByName(String name);

}
