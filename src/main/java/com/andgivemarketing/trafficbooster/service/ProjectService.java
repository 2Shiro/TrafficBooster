package com.andgivemarketing.trafficbooster.service;

import com.andgivemarketing.trafficbooster.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

}
