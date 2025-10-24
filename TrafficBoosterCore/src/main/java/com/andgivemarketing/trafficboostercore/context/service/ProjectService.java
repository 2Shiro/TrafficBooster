package com.andgivemarketing.trafficboostercore.context.service;


import com.andgivemarketing.trafficboostercore.context.dto.*;
import com.andgivemarketing.trafficboostercore.context.entity.ProjectEntity;
import com.andgivemarketing.trafficboostercore.global.parse.ProjectParser;
import com.andgivemarketing.trafficboostercore.context.repository.ProjectRepository;
import com.andgivemarketing.trafficboostercore.context.repository.TrafficSourceRecordRepository;
import com.andgivemarketing.trafficboostercore.context.repository.TrafficSourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectParser projectParser;
    private final TrafficSourceRepository trafficSourceRepository;
    private final TrafficSourceRecordRepository trafficSourceRecordRepository;

    /**
     * 프로젝트 생성
     * @param projectDTO    프로젝트 정보
     */
    public void createProject(ProjectDTO projectDTO) {

        // DB에서 state DEFAULT 'RUNNING'
        ProjectEntity projectEntity = ProjectEntity.builder()
                .name(projectDTO.getName())
                .targetAddress(projectDTO.getTargetAddress())
                .startDate(projectDTO.getStartDate())
                .endDate(projectDTO.getEndDate())
                .status(ProjectStatus.ONGOING)
                .trafficType(projectDTO.getTrafficType())
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

        ProjectEntity projectEntity = projectRepository.findById(projectDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("프로젝트를 찾을 수 없습니다: id=" + projectDTO.getId()));

        projectEntity.setName(projectDTO.getName());
        projectEntity.setTargetAddress(projectDTO.getTargetAddress());
        projectEntity.setStartDate(projectDTO.getStartDate());
        projectEntity.setEndDate(projectDTO.getEndDate());
        projectEntity.setStatus(projectDTO.getStatus());
        projectEntity.setTrafficType(projectDTO.getTrafficType());
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

    /**
     * 페이징 + 필터 처리된 프로젝트 목록 반환
     * @param req
     * @return
     */
    public ProjectPagingResponse loadProjectsPage(ProjectRequest req) {

        int page = (req.getPage() == null || req.getPage() < 0) ? 0 : req.getPage();
        int pageSize = (req.getPageSize() == null || req.getPageSize() <= 0) ? 20 : req.getPageSize();

        // 1) 기본 필터: name(contains) & status
        List<ProjectEntity> filtered = projectRepository.findAll()
                .stream()
                .filter(p -> {
                    // name filter
                    if (req.getSearchInput() != null && !req.getSearchInput().trim().isEmpty()) {
                        String q = req.getSearchInput().trim().toLowerCase(Locale.ROOT);
                        if (p.getName() == null || !p.getName().toLowerCase(Locale.ROOT).contains(q)) {
                            return false;
                        }
                    }
                    // status filter
                    if (req.getStatusFilter() != null && !"ALL".equalsIgnoreCase(req.getStatusFilter())) {
                        if (p.getStatus() == null || !p.getStatus().name().equalsIgnoreCase(req.getStatusFilter())) {
                            return false;
                        }
                    }
                    return true;
                })
                .toList();

        // 오늘 범위 계산 (startInclusive, endExclusive)
        LocalDateTime todayStart = LocalDate.now().atStartOfDay();
        LocalDateTime tomorrowStart = todayStart.plusDays(1);

        // 2) 각 프로젝트별로 현재 달성 수 계산하고 DTO로 변환, excludeDailyCompleted 적용
        List<ProjectListDTO> dtos = filtered.stream()
                .map(p -> {
                    long totalCount = trafficSourceRecordRepository.countByProjectId(p.getId());
                    long todayCount = trafficSourceRecordRepository.countByProjectIdAndCreatedAtBetween(p.getId(), todayStart, tomorrowStart);

                    Long dailyTarget = p.getDailyTargetTrafficCount() == null ? 0L : p.getDailyTargetTrafficCount();
                    Long totalTarget = p.getTotalTargetTrafficCount() == null ? 0L : p.getTotalTargetTrafficCount();

                    return new ProjectListDTO(
                            p.getId(),
                            p.getName(),
                            p.getStatus() == null ? null : p.getStatus().getLabel(),
                            p.getTrafficType() == null ? null : p.getTrafficType().getLabel(),
                            dailyTarget,
                            totalTarget,
                            todayCount,
                            totalCount
                    );
                })
                .filter(dto -> {
                    if (Boolean.TRUE.equals(req.getExcludeDailyCompleted())) {
                        // 일일 목표치와 금일 달성수가 같은 경우 제외
                        Long target = dto.getDailyTargetTrafficCount() == null ? 0L : dto.getDailyTargetTrafficCount();
                        Long today = dto.getCurrentDailyCount() == null ? 0L : dto.getCurrentDailyCount();
                        return !Objects.equals(target, today);
                    }
                    return true;
                })
                .collect(Collectors.toList());

        // 3) 정렬 처리 (중복 정렬 불가: 우선순위로 dailyTargetTrafficCountDesc가 있으면 그것을 사용, 없으면 totalTargetTrafficCountDesc 사용)
        if (req.getDailyTargetTrafficCountDesc() != null) {
            if (req.getDailyTargetTrafficCountDesc()) {
                dtos.sort(Comparator.comparing(ProjectListDTO::getDailyTargetTrafficCount, Comparator.nullsFirst(Long::compareTo)).reversed());
            } else {
                dtos.sort(Comparator.comparing(ProjectListDTO::getDailyTargetTrafficCount, Comparator.nullsFirst(Long::compareTo)));
            }
        } else if (req.getTotalTargetTrafficCountDesc() != null) {
            if (req.getTotalTargetTrafficCountDesc()) {
                dtos.sort(Comparator.comparing(ProjectListDTO::getTotalTargetTrafficCount, Comparator.nullsFirst(Long::compareTo)).reversed());
            } else {
                dtos.sort(Comparator.comparing(ProjectListDTO::getTotalTargetTrafficCount, Comparator.nullsFirst(Long::compareTo)));
            }
        }

        // 4) 수동 페이징
        long totalElements = dtos.size();
        int totalPages = (int) Math.ceil((double) totalElements / pageSize);
        int fromIndex = page * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, dtos.size());

        List<ProjectListDTO> pageContent;
        if (fromIndex >= dtos.size()) {
            pageContent = List.of();
        } else {
            pageContent = dtos.subList(fromIndex, toIndex);
        }

        return new ProjectPagingResponse(pageContent, page, pageSize, totalElements, totalPages);
    }

}
