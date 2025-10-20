package com.andgivemarketing.trafficbooster.service;

import com.andgivemarketing.trafficbooster.entity.ProjectEntity;
import com.andgivemarketing.trafficbooster.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    // 프로젝트 생성 또는 수정
    public void save(ProjectEntity projectEntity) {
        projectRepository.save(projectEntity);
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

}
