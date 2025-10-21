package com.andgivemarketing.trafficbooster.global.parse;

import com.andgivemarketing.trafficbooster.context.project.dto.ProjectDTO;
import com.andgivemarketing.trafficbooster.context.project.entity.ProjectEntity;
import com.andgivemarketing.trafficbooster.context.trafficsource.dto.TrafficSourceDTO;
import com.andgivemarketing.trafficbooster.context.trafficsource.entity.TrafficSourceEntity;
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
        trafficSourceDTO.setUpdatedAt(trafficSourceEntity.getUpdatedAt());

        return trafficSourceDTO;
    }

}
