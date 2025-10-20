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

    // id로 프로젝트Entity 조회
    public ProjectEntity findById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    // id로 프로젝트DTO 조회 (DTO)
    public ProjectDTO findDtoById(Long id) {
        ProjectEntity projectEntity = findById(id);
        return toDto(projectEntity);
    }

    // Entity를 DTO로 변환
    public static ProjectDTO toDto(ProjectEntity e) {
        if (e == null) return null;
        ProjectDTO dto = new ProjectDTO();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setStartedAt(e.getStartedAt());
        dto.setState(e.getState());
        dto.setDailyTargetInflow(e.getDailyTargetInflow());
        dto.setTotalTargetInflow(e.getTotalTargetInflow());
        dto.setTargetAddress(e.getTargetAddress());
        dto.setCreatedAt(e.getCreatedAt());
        dto.setUpdatedAt(e.getUpdatedAt());
        return dto;
    }
}
