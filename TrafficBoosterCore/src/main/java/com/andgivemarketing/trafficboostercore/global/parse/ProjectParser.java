package com.andgivemarketing.trafficboostercore.global.parse;


import com.andgivemarketing.trafficboostercore.context.dto.ProjectDTO;
import com.andgivemarketing.trafficboostercore.context.entity.ProjectEntity;
import org.springframework.stereotype.Component;

@Component
public class ProjectParser {

    // Entity를 DTO로 변환
    public ProjectDTO projectToDto(ProjectEntity projectEntity) {

        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setId(projectEntity.getId());
        projectDTO.setName(projectEntity.getName());
        projectDTO.setStartDate(projectEntity.getStartDate());
        projectDTO.setEndDate(projectEntity.getEndDate());
        projectDTO.setStatus(projectEntity.getStatus());
        projectDTO.setTrafficType(projectEntity.getTrafficType());
        projectDTO.setDailyTargetTrafficCount(projectEntity.getDailyTargetTrafficCount());
        projectDTO.setTotalTargetTrafficCount(projectEntity.getTotalTargetTrafficCount());
        projectDTO.setTargetAddress(projectEntity.getTargetAddress());
        projectDTO.setCreatedAt(projectEntity.getCreatedAt());
        projectDTO.setUpdatedAt(projectEntity.getUpdatedAt());

        return projectDTO;
    }

}
