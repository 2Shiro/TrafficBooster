package com.andgivemarketing.trafficbooster.context.service;

import com.andgivemarketing.trafficbooster.context.dto.ProjectDTO;
import com.andgivemarketing.trafficbooster.context.entity.ProjectEntity;
import com.andgivemarketing.trafficbooster.context.dto.ProjectStatus;
import com.andgivemarketing.trafficbooster.context.repository.ProjectRepository;
import com.andgivemarketing.trafficbooster.global.parse.ProjectParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
                .status(ProjectStatus.ONGOING)
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
    public ProjectDTO findProject(Long id) {

        return projectRepository.findById(id)
                .map(projectParser::projectToDto)
                .orElse(null);

    }

    /**
     * 프로젝트 전체 조회
     * @param
     * @return  프로젝트 정보 리스트
     */
    public List<ProjectDTO> findAllProjects() {

        return projectRepository.findAll()
                .stream()
                .map(projectParser::projectToDto)
                .collect(Collectors.toList());

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
