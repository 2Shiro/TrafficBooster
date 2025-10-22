package com.andgivemarketing.trafficbooster.global.parse;

import com.andgivemarketing.trafficbooster.context.dto.ProjectDTO;
import com.andgivemarketing.trafficbooster.context.entity.ProjectEntity;
import org.springframework.stereotype.Component;

@Component
public class ProjectParser {

    // Entity를 DTO로 변환
    public ProjectDTO projectToDto(ProjectEntity projectEntity) {

        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setId(projectEntity.getId());
        projectDTO.setName(projectEntity.getName());
        projectDTO.setStartedAt(projectEntity.getStartedAt());
        projectDTO.setStatus(projectEntity.getStatus());
        projectDTO.setDailyTargetTrafficCount(projectEntity.getDailyTargetTrafficCount());
        projectDTO.setTotalTargetTrafficCount(projectEntity.getTotalTargetTrafficCount());
        projectDTO.setTargetAddress(projectEntity.getTargetAddress());
        projectDTO.setCreatedAt(projectEntity.getCreatedAt());
        projectDTO.setUpdatedAt(projectEntity.getUpdatedAt());

        return projectDTO;
    }

}
