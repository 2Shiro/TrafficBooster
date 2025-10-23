package com.andgivemarketing.trafficboostercore.context.service;


import com.andgivemarketing.trafficboostercore.context.dto.TrafficSourceDTO;
import com.andgivemarketing.trafficboostercore.context.entity.TrafficSourceEntity;
import com.andgivemarketing.trafficboostercore.global.parse.TrafficSourceParser;
import com.andgivemarketing.trafficboostercore.context.repository.TrafficSourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TrafficSourceService {

    private final TrafficSourceRepository trafficSourceRepository;
    private final TrafficSourceParser trafficSourceParser;

    /**
     * 유입경로 생성
     * @param trafficSourceDTO  유입경로 정보
     */
    public void createTrafficSource(TrafficSourceDTO trafficSourceDTO) {

        // DB에서 state DEFAULT 'RUNNING'
        TrafficSourceEntity trafficSourceEntity = TrafficSourceEntity.builder()
                .projectId(trafficSourceDTO.getProjectId())
                .trafficSourcePath(trafficSourceDTO.getTrafficSourcePath())
                .useScroll(trafficSourceDTO.isUseScroll())
                .stayDurationTime(trafficSourceDTO.getStayDurationTime())
                .createdAt(LocalDateTime.now())
                .build();

        trafficSourceRepository.save(trafficSourceEntity);
    }

    /**
     * 유입경로 조회
     * @param id    유입경로 id
     * @return      유입경로 정보
     */
    public TrafficSourceDTO getTrafficSource(Long id) {

        return trafficSourceRepository.findById(id)
                .map(trafficSourceParser::trafficSourceToDTO)
                .orElse(null);

    }

    /**
     * 유입경로 수정
     * @param trafficSourceDTO  유입경로 정보
     */
    public void updateTrafficSource(TrafficSourceDTO trafficSourceDTO) {

        TrafficSourceEntity trafficSourceEntity = new TrafficSourceEntity();

        trafficSourceEntity.setProjectId(trafficSourceDTO.getProjectId());
        trafficSourceEntity.setTrafficSourcePath(trafficSourceDTO.getTrafficSourcePath());
        trafficSourceEntity.setUseScroll(trafficSourceDTO.isUseScroll());
        trafficSourceEntity.setStayDurationTime(trafficSourceDTO.getStayDurationTime());

        trafficSourceRepository.save(trafficSourceEntity);
    }

    /**
     * 유입경로 삭제
     * @param id    유입경로 id
     */
    public void deleteTrafficSource(Long id) {
        trafficSourceRepository.deleteById(id);
    }

    /**
     * id로 유입경로 조회
     * @param id    유입경로 id
     * @return      유입경로 정보
     */
    public TrafficSourceEntity findTrafficSourceById(Long id) {
        return trafficSourceRepository.findById(id).orElse(null);
    }

    /**
     * 해당 프로젝트에 연결된 모든 유입경로 조회
     * @param projectId 프로젝트 id
     * @return          유입경로 정보
     */
    public List<TrafficSourceDTO> findTrafficSourceByProjectId(Long projectId) {
        return trafficSourceRepository.findAllByProjectId(projectId)
                .stream()
                .map(trafficSourceParser::trafficSourceToDTO)
                .collect(Collectors.toList());
    }
    
}
