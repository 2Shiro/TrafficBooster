package com.andgivemarketing.trafficbooster.context.controller;

import com.andgivemarketing.trafficbooster.context.dto.ProjectDTO;
import com.andgivemarketing.trafficbooster.context.dto.ProjectStatus;
import com.andgivemarketing.trafficbooster.context.entity.ProjectEntity;
import com.andgivemarketing.trafficbooster.context.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final ProjectService projectService;

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
}
