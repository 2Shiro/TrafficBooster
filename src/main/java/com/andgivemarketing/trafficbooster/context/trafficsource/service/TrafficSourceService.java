package com.andgivemarketing.trafficbooster.context.trafficsource.service;

import com.andgivemarketing.trafficbooster.context.trafficsource.dto.TrafficSourceDTO;
import com.andgivemarketing.trafficbooster.context.trafficsource.entity.TrafficSourceEntity;
import com.andgivemarketing.trafficbooster.context.trafficsource.repository.TrafficSourceRepository;
import com.andgivemarketing.trafficbooster.global.parse.TrafficSourceParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
        trafficSourceEntity.setUpdatedAt(LocalDateTime.now());

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
    
}
