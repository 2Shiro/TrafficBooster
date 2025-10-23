package com.andgivemarketing.trafficboostercore.global.parse;


import com.andgivemarketing.trafficboostercore.context.dto.TrafficSourceRecordDTO;
import com.andgivemarketing.trafficboostercore.context.entity.TrafficSourceRecordEntity;
import org.springframework.stereotype.Component;

@Component
public class TrafficSourceRecordParser {

    // Entity를 DTO로 변환
    public TrafficSourceRecordDTO trafficSourceRecordToDTO(TrafficSourceRecordEntity trafficSourceRecordEntity) {

        TrafficSourceRecordDTO trafficSourceRecordDTO = new TrafficSourceRecordDTO();

        trafficSourceRecordDTO.setId(trafficSourceRecordEntity.getId());
        trafficSourceRecordDTO.setProjectId(trafficSourceRecordEntity.getProjectId());
        trafficSourceRecordDTO.setTrafficSourceId(trafficSourceRecordEntity.getTrafficSourceId());
        trafficSourceRecordDTO.setUserAgent(trafficSourceRecordEntity.getUserAgent());
        trafficSourceRecordDTO.setIp(trafficSourceRecordEntity.getIp());
        trafficSourceRecordDTO.setCreatedAt(trafficSourceRecordEntity.getCreatedAt());

        return trafficSourceRecordDTO;
    }

}
