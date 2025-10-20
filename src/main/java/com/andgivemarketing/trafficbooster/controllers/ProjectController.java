package com.andgivemarketing.trafficbooster.controllers;

import com.andgivemarketing.trafficbooster.dto.ErrorResponse;
import com.andgivemarketing.trafficbooster.dto.ProjectDTO;
import com.andgivemarketing.trafficbooster.entity.ProjectEntity;
import com.andgivemarketing.trafficbooster.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ProjectController {

    private final ProjectService projectService;

    // 프로젝트 생성
    @PostMapping("/project/create")
    public ResponseEntity<?> createProject(@RequestBody(required = false) ProjectDTO projectDTO) {

        if (projectDTO == null
                || projectDTO.getName() == null || projectDTO.getName().isBlank()
                || projectDTO.getTargetAddress() == null  || projectDTO.getTargetAddress().isBlank()
                || projectDTO.getStartedAt() == null
                || projectDTO.getDailyTargetInflow() == null
                || projectDTO.getTotalTargetInflow() == null) {
            return ResponseEntity.status(400).body(new ErrorResponse(new ErrorResponse.ErrorDetails("올바르지 않은 이름입니다")));
        }

        String name = projectDTO.getName();

        // 이름 중복 체크
        ProjectEntity projectEntity = projectService.findByName(name);

        if (projectEntity != null) {
            return ResponseEntity.status(404).body(new ErrorResponse(new ErrorResponse.ErrorDetails("이미 존재하는 이름입니다")));
        }

        projectEntity = ProjectEntity.builder()
                .name(projectDTO.getName())
                .targetAddress(projectDTO.getTargetAddress())
                .startedAt(projectDTO.getStartedAt())
                .state(projectDTO.getState()) // 디폴트 값 0
                .dailyTargetInflow(projectDTO.getDailyTargetInflow())
                .totalTargetInflow(projectDTO.getTotalTargetInflow())
                .createdAt(LocalDateTime.now())
                .build();

        projectService.save(projectEntity);

        return ResponseEntity.ok().build();
    }

    // 프로젝트 수정
    @PostMapping("/project/update")
    public ResponseEntity<?> updateProject(@RequestBody(required = false) ProjectDTO projectDTO) {

        if (projectDTO == null
                || projectDTO.getId() == null
                || projectDTO.getName() == null || projectDTO.getName().isBlank()
                || projectDTO.getTargetAddress() == null  || projectDTO.getTargetAddress().isBlank()
                || projectDTO.getStartedAt() == null
                || projectDTO.getState() == null
                || projectDTO.getDailyTargetInflow() == null
                || projectDTO.getTotalTargetInflow() == null) {
            return ResponseEntity.status(400).body(new ErrorResponse(new ErrorResponse.ErrorDetails("올바르지 않은 이름입니다")));
        }

        // 수정할 문제 조회
        ProjectEntity projectEntity = projectService.findById(projectDTO.getId());
        if (projectEntity == null) {
            return ResponseEntity.status(404).body(new ErrorResponse(new ErrorResponse.ErrorDetails("해당 문제를 찾을 수 없습니다")));
        }

        projectEntity = new ProjectEntity();
        projectEntity.setName(projectDTO.getName());
        projectEntity.setTargetAddress(projectDTO.getTargetAddress());
        projectEntity.setStartedAt(projectDTO.getStartedAt());
        projectEntity.setState(projectDTO.getState());
        projectEntity.setDailyTargetInflow(projectDTO.getDailyTargetInflow());
        projectEntity.setTotalTargetInflow(projectDTO.getTotalTargetInflow());
        projectEntity.setUpdatedAt(LocalDateTime.now());

        projectService.save(projectEntity);

        return ResponseEntity.ok().build();
    }

    // 프로젝트 삭제
    @PostMapping("/project/delete")
    public ResponseEntity<?> deleteProject(@RequestParam(name = "id") Long id) {

        // 삭제할 문제 요청 id 확인
        if (id == null) {
            return ResponseEntity.status(400).body(new ErrorResponse(new ErrorResponse.ErrorDetails("올바르지 않은 요청입니다")));
        }

        // 문제 존재 여부 확인
        if (projectService.findById(id) == null) {
            return ResponseEntity.status(404).body(new ErrorResponse(new ErrorResponse.ErrorDetails("존재하지 않는 프로젝트입니다")));
        }

        projectService.delete(id);

        return ResponseEntity.ok().build();
    }

}
