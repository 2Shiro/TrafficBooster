package com.andgivemarketing.trafficbooster.service;

import com.andgivemarketing.trafficbooster.dto.ProjectDTO;
import com.andgivemarketing.trafficbooster.entity.ProjectEntity;
import com.andgivemarketing.trafficbooster.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    // 프로젝트 저장 (생성/수정)
    public ProjectEntity save(ProjectEntity projectEntity) {
        return projectRepository.save(projectEntity);
    }

    // 프로젝트 삭제
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    // 이름으로 프로젝트 조회
    public ProjectEntity findByName(String name){
        return projectRepository.findByName(name).orElse(null);
    }

    // id로 프로젝트 조회
    public ProjectEntity findById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    // Entity를 DTO로 변환
    public static ProjectDTO parseDto(ProjectEntity projectEntity) {

        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setId(projectEntity.getId());
        projectDTO.setName(projectEntity.getName());
        projectDTO.setStartedAt(projectEntity.getStartedAt());
        projectDTO.setState(projectEntity.getState());
        projectDTO.setDailyTargetTrafficCount(projectEntity.getDailyTargetTrafficCount());
        projectDTO.setTotalTargetTrafficCount(projectEntity.getTotalTargetTrafficCount());
        projectDTO.setTargetAddress(projectEntity.getTargetAddress());
        projectDTO.setCreatedAt(projectEntity.getCreatedAt());
        projectDTO.setUpdatedAt(projectEntity.getUpdatedAt());

        return projectDTO;
    }
}
