package com.andgivemarketing.trafficboostercore.context.dto;

import lombok.Getter;

public enum TrafficType {
    DEDICATED_PROXY("고정 IP"),
    DATACENTER_PROXY("유동 IP"),
    TETHERING("테더링");

    @Getter
    private final String label;

    TrafficType(String label) {
        this.label = label;
    }

}
