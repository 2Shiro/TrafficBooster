package com.andgivemarketing.trafficboosterweb.controller;


import com.andgivemarketing.trafficboostercore.context.dto.ProjectDTO;
import com.andgivemarketing.trafficboostercore.context.dto.ProjectPagingResponse;
import com.andgivemarketing.trafficboostercore.context.dto.ProjectRequest;
import com.andgivemarketing.trafficboostercore.context.entity.ProjectEntity;
import com.andgivemarketing.trafficboostercore.global.dto.ErrorResponse;
import com.andgivemarketing.trafficboostercore.global.parse.ProjectParser;
import com.andgivemarketing.trafficboostercore.context.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectParser projectParser;

    /**
     * 프로젝트 생성
     * @param projectDTO    프로젝트 정보
     * @return              프로젝트 id
     */
    @PostMapping("/project/create")
    public ResponseEntity<?> createProject(@RequestBody(required = false) ProjectDTO projectDTO) {

        if (projectDTO == null
                || projectDTO.getName() == null || projectDTO.getName().isBlank()
                || projectDTO.getTargetAddress() == null  || projectDTO.getTargetAddress().isBlank()
                || projectDTO.getStartDate() == null
                || projectDTO.getTrafficType() == null
                || projectDTO.getDailyTargetTrafficCount() == null
                || projectDTO.getTotalTargetTrafficCount() == null) {
            return ResponseEntity.status(400).body(new ErrorResponse(new ErrorResponse.ErrorDetails("올바르지 않은 요청입니다.")));
        }

        String name = projectDTO.getName();

        // 이름 중복 체크
        ProjectEntity projectEntity = projectService.findProjectByName(name);

        if (projectEntity != null) {
            return ResponseEntity.status(404).body(new ErrorResponse(new ErrorResponse.ErrorDetails("이미 사용 중인 이름입니다.")));
        }

        projectService.createProject(projectDTO);

        return ResponseEntity.ok().build();
    }

    /**
     * 프로젝트 수정
     * @param projectDTO    프로젝트 정보
     * @return              프로젝트 id
     */
    @PutMapping("/project/update")
    public ResponseEntity<?> updateProject(@RequestBody(required = false) ProjectDTO projectDTO) {

        if (projectDTO == null
                || projectDTO.getId() == null
                || projectDTO.getName() == null || projectDTO.getName().isBlank()
                || projectDTO.getTargetAddress() == null  || projectDTO.getTargetAddress().isBlank()
                || projectDTO.getStartDate() == null
                || projectDTO.getStatus() == null
                || projectDTO.getTrafficType() == null
                || projectDTO.getDailyTargetTrafficCount() == null
                || projectDTO.getTotalTargetTrafficCount() == null) {
            return ResponseEntity.status(400).body(new ErrorResponse(new ErrorResponse.ErrorDetails("올바르지 않은 요청입니다.")));
        }

        // 수정할 프로젝트 조회
        ProjectEntity projectEntity = projectService.findProjectById(projectDTO.getId());
        if (projectEntity == null) {
            return ResponseEntity.status(404).body(new ErrorResponse(new ErrorResponse.ErrorDetails("요청하신 프로젝트를 찾을 수 없습니다.")));
        }

        projectService.updateProject(projectDTO);

        return ResponseEntity.ok(projectDTO.getId());
    }

    /**
     * 프로젝트 삭제
     * @param id    프로젝트 id
     * @return
     */
    @DeleteMapping("/project/delete")
    public ResponseEntity<?> deleteProject(@RequestParam(required = false, name = "id") Long id) {

        // 삭제할 프로젝트 요청 id 확인
        if (id == null) {
            return ResponseEntity.status(400).body(new ErrorResponse(new ErrorResponse.ErrorDetails("올바르지 않은 요청입니다.")));
        }

        // 프로젝트 존재 여부 확인
        if (projectService.findProjectById(id) == null) {
            return ResponseEntity.status(404).body(new ErrorResponse(new ErrorResponse.ErrorDetails("요청하신 프로젝트를 찾을 수 없습니다.")));
        }

        projectService.deleteProject(id);

        return ResponseEntity.ok().build();
    }

    /**
     * 프로젝트 데이터 조회
     * @param   projectRequest
     * @return
     */
    @PostMapping("/projects/load")
    public ResponseEntity<?> loadProjectsPage(@RequestBody(required = false) ProjectRequest projectRequest) {
        try {
            if (projectRequest == null) {
                projectRequest = new ProjectRequest();
            }
            ProjectPagingResponse resp = projectService.loadProjectsPage(projectRequest);
            return ResponseEntity.ok(resp);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ErrorResponse(new ErrorResponse.ErrorDetails("프로젝트를 불러오는 중 오류가 발생했습니다.")));
        }
    }

}
