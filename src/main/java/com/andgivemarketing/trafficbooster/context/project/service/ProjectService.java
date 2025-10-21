package com.andgivemarketing.trafficbooster.context.project.service;

import com.andgivemarketing.trafficbooster.context.project.dto.ProjectDTO;
import com.andgivemarketing.trafficbooster.context.project.entity.ProjectEntity;
import com.andgivemarketing.trafficbooster.context.project.enums.ProjectStatus;
import com.andgivemarketing.trafficbooster.context.project.repository.ProjectRepository;
import com.andgivemarketing.trafficbooster.global.parse.ProjectParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectParser projectParser;

    /**
     * 프로젝트 생성
     * @param projectDTO    프로젝트 정보
     */
    public void createProject(ProjectDTO projectDTO) {

        // DB에서 state DEFAULT 'RUNNING'
        ProjectEntity projectEntity = ProjectEntity.builder()
                .name(projectDTO.getName())
                .targetAddress(projectDTO.getTargetAddress())
                .startedAt(projectDTO.getStartedAt())
                .status(ProjectStatus.RUNNING)
                .dailyTargetTrafficCount(projectDTO.getDailyTargetTrafficCount())
                .totalTargetTrafficCount(projectDTO.getTotalTargetTrafficCount())
                .createdAt(LocalDateTime.now())
                .build();

        projectRepository.save(projectEntity);
    }

    /**
     * 프로젝트 조회
     * @param id    프로젝트 id
     * @return      프로젝트 정보
     */
    public ProjectDTO getProject(Long id) {

        return projectRepository.findById(id)
                .map(projectParser::projectToDto)
                .orElse(null);

    }

    /**
     * 프로젝트 수정
     * @param projectDTO    프로젝트 정보
     */
    public void updateProject(ProjectDTO projectDTO) {

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName(projectDTO.getName());
        projectEntity.setTargetAddress(projectDTO.getTargetAddress());
        projectEntity.setStartedAt(projectDTO.getStartedAt());
        projectEntity.setStatus(projectDTO.getStatus());
        projectEntity.setDailyTargetTrafficCount(projectDTO.getDailyTargetTrafficCount());
        projectEntity.setTotalTargetTrafficCount(projectDTO.getTotalTargetTrafficCount());
        projectEntity.setUpdatedAt(LocalDateTime.now());

        projectRepository.save(projectEntity);
    }

    /**
     * 프로젝트 삭제
     * @param id
     */
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    /**
     * 이름으로 프로젝트 조회
     * @param name  프로젝트 명
     * @return      프로젝트 정보
     */
    public ProjectEntity findProjectByName(String name){
        return projectRepository.findByName(name).orElse(null);
    }

    /**
     * id로 프로젝트 조회
     * @param id    프로젝트 id
     * @return      프로젝트 정보
     */
    public ProjectEntity findProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

}
