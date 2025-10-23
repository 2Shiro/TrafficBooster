package com.andgivemarketing.trafficboostercore.context.dto;

import lombok.Getter;

public enum ProjectStatus {
    ONGOING("진행중"),
    CANCELED("취소됨"),
    COMPLETED("완료됨");

    @Getter
    private final String label;

    ProjectStatus(String label) {
        this.label = label;
    }

}
