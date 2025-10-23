package com.andgivemarketing.trafficboosterweb.controller;


import com.andgivemarketing.trafficboostercore.context.dto.ProjectDTO;
import com.andgivemarketing.trafficboostercore.context.dto.ProjectStatus;
import com.andgivemarketing.trafficboostercore.context.dto.TrafficSourceDTO;
import com.andgivemarketing.trafficboostercore.context.dto.TrafficSourceRecordDTO;
import com.andgivemarketing.trafficboostercore.context.entity.ProjectEntity;
import com.andgivemarketing.trafficboostercore.context.service.ProjectService;
import com.andgivemarketing.trafficboostercore.context.service.TrafficSourceRecordService;
import com.andgivemarketing.trafficboostercore.context.service.TrafficSourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final ProjectService projectService;
    private final TrafficSourceService trafficSourceService;
    private final TrafficSourceRecordService trafficSourceRecordService;

    /**
     * 대시보드 호출
     * @param model
     * @return
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        List<ProjectDTO> projectDTOS = projectService.findAllProjects();

        int totalProjectCount = projectDTOS.size();

        int ongoingProjectCount = projectDTOS.stream().filter(projectEntity ->
                projectEntity.getStatus().equals(ProjectStatus.ONGOING)).toList().size();

        int completedProjectCount = projectDTOS.stream().filter(projectEntity ->
                projectEntity.getStatus().equals(ProjectStatus.COMPLETED)).toList().size();

        model.addAttribute("totalProjectCount", totalProjectCount);
        model.addAttribute("ongoingProjectCount", ongoingProjectCount);
        model.addAttribute("completedProjectCount", completedProjectCount);

        return "dashboard";
    }

    /**
     * 프로젝트 설정
     * @param projectId 프로젝트 id
     * @return
     */
    @GetMapping("/dashboard/settings")
    public String settingsDashboard(@RequestParam(required = false, name = "id") Long projectId, Model model) {

        if (projectId == null) {
            // 프로젝트 아이디가 없으면 목록 등으로 리다이렉트하거나 에러 페이지로 보냄
            return "redirect:/dashboard";
        }

        // 1) 프로젝트 정보 (DTO)
        ProjectDTO projectDTO = projectService.findProject(projectId);
        if (projectDTO == null) {
            // 프로젝트가 없으면 적절히 처리
            return "redirect:/dashboard";
        }
        model.addAttribute("project", projectDTO);

        // 2) 해당 프로젝트에 연결된 유입경로 (DTO 리스트)
        List<TrafficSourceDTO> trafficSources = trafficSourceService.findTrafficSourceByProjectId(projectId);
        model.addAttribute("trafficSources", trafficSources == null ? Collections.emptyList() : trafficSources);
        model.addAttribute("trafficSourceCount", (trafficSources == null) ? 0 : trafficSources.size());

        // 3) 해당 프로젝트의 유입기록 (DTO 리스트)
        List<TrafficSourceRecordDTO> trafficSourceRecordDTOS = trafficSourceRecordService.findTrafficSourceRecordByProjectId(projectId);
        int trafficSourceRecordCountByProjectId = (trafficSourceRecordDTOS == null) ? 0 : trafficSourceRecordDTOS.size();
        model.addAttribute("trafficSourceRecords", trafficSourceRecordDTOS == null ? Collections.emptyList() : trafficSourceRecordDTOS);
        model.addAttribute("trafficSourceRecordCount", trafficSourceRecordCountByProjectId);

        // 4) 오늘의 유입 카운트 (서비스에서 Long 반환)
        Long todayCount = trafficSourceRecordService.findTodayTrafficSourceRecordCountByProjectId(projectId);
        int todayTrafficSourceRecordCount = (int) (todayCount == null ? 0L : todayCount);
        model.addAttribute("todayTrafficSourceRecordCount", todayTrafficSourceRecordCount);

        // 5) 목표 관련 파생값들 (DTO에 없는 값들은 addAttribute로 추가)
        Long totalTarget = projectDTO.getTotalTargetTrafficCount() == null ? 0L : projectDTO.getTotalTargetTrafficCount();
        Long dailyTarget = projectDTO.getDailyTargetTrafficCount() == null ? 0L : projectDTO.getDailyTargetTrafficCount();

        long remainingTotalTargetTrafficCount = totalTarget - trafficSourceRecordCountByProjectId;
        if (remainingTotalTargetTrafficCount < 0) remainingTotalTargetTrafficCount = 0;

        long remainingTodayTargetTrafficCount = dailyTarget - todayTrafficSourceRecordCount;
        if (remainingTodayTargetTrafficCount < 0) remainingTodayTargetTrafficCount = 0;

        model.addAttribute("totalTargetTrafficCount", totalTarget);
        model.addAttribute("dailyTargetTrafficCount", dailyTarget);
        model.addAttribute("remainingTotalTargetTrafficCount", remainingTotalTargetTrafficCount);
        model.addAttribute("remainingTodayTargetTrafficCount", remainingTodayTargetTrafficCount);

        // (선택) 뷰에서 편하게 사용할 추가 정보들
        model.addAttribute("hasTrafficSources", (trafficSources != null && !trafficSources.isEmpty()));
        model.addAttribute("hasRecords", trafficSourceRecordCountByProjectId > 0);

        return "settingsdashboard";
    }

    /**
     * 프로젝트 리포트
     * @param projectId 프로젝트 id
     * @return
     */
    @GetMapping("/dashboard/report")
    public String reportDashboard(@RequestParam(required = false, name = "projectId") Long projectId) {



        return "reportdashboard";
    }

}
