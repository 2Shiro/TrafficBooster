package com.andgivemarketing.trafficboostercore.global.parse;


import com.andgivemarketing.trafficboostercore.context.dto.TrafficSourceDTO;
import com.andgivemarketing.trafficboostercore.context.entity.TrafficSourceEntity;
import org.springframework.stereotype.Component;

@Component
public class TrafficSourceParser {

    // Entity를 DTO로 변환
    public TrafficSourceDTO trafficSourceToDTO(TrafficSourceEntity trafficSourceEntity) {

        TrafficSourceDTO trafficSourceDTO = new TrafficSourceDTO();

        trafficSourceDTO.setId(trafficSourceEntity.getId());
        trafficSourceDTO.setProjectId(trafficSourceEntity.getProjectId());
        trafficSourceDTO.setTrafficSourcePath(trafficSourceEntity.getTrafficSourcePath());
        trafficSourceDTO.setUseScroll(trafficSourceEntity.isUseScroll());
        trafficSourceDTO.setStayDurationTime(trafficSourceEntity.getStayDurationTime());
        trafficSourceDTO.setCreatedAt(trafficSourceEntity.getCreatedAt());

        return trafficSourceDTO;
    }

}
