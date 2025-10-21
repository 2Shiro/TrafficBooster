package com.andgivemarketing.trafficbooster.context.project.repository;

import com.andgivemarketing.trafficbooster.context.project.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long>  {

    /**
     * 이름으로 프로젝트 조회
     * @param name  프로젝트 명
     * @return      프로젝트 정보
     */
    Optional<ProjectEntity> findByName(String name);

}
