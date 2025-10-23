package com.andgivemarketing.trafficboostercore.context.service;


import com.andgivemarketing.trafficboostercore.context.dto.TrafficSourceRecordDTO;
import com.andgivemarketing.trafficboostercore.global.parse.TrafficSourceRecordParser;
import com.andgivemarketing.trafficboostercore.context.repository.TrafficSourceRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TrafficSourceRecordService {

    private final TrafficSourceRecordRepository trafficSourceRecordRepository;
    private final TrafficSourceRecordParser trafficSourceRecordParser;

    /**
     * 프로젝트 id로 유입기록 조회
     * @param projectId 프로젝트 id
     * @return          유입기록 정보
     */
    public List<TrafficSourceRecordDTO> findTrafficSourceRecordByProjectId(Long projectId) {

        return trafficSourceRecordRepository.findAllByProjectId(projectId)
                .stream()
                .map(trafficSourceRecordParser::trafficSourceRecordToDTO)
                .collect(Collectors.toList());
    }

    /**
     * 프로젝트 id로 금일 유입량 조회
     * @param projectId 프로젝트 id
     * @return          유입기록 정보
     */
    public Long findTodayTrafficSourceRecordCountByProjectId(Long projectId) {

        // 오늘 범위 계산 (startInclusive, endExclusive)
        LocalDateTime todayStart = LocalDate.now().atStartOfDay();
        LocalDateTime tomorrowStart = todayStart.plusDays(1);

        return trafficSourceRecordRepository.countByProjectIdAndCreatedAtBetween(projectId, todayStart, tomorrowStart);

    }
    
}
