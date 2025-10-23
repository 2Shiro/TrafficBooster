package com.andgivemarketing.trafficboostercore.context.dto;

import lombok.Data;

@Data
public class ProjectRequest {

    //요청한 페이지
    private Integer page;

    //표시 개수
    private Integer pageSize;

    //검색어(프로젝트 명)
    private String searchInput;

    private String statusFilter;

    //null 인 경우 정렬 X, true 인 경우 내림차순, false인 경우 오름차순
    private Boolean dailyTargetTrafficCountDesc;

    private Boolean totalTargetTrafficCountDesc;

    private Boolean excludeDailyCompleted;
}
