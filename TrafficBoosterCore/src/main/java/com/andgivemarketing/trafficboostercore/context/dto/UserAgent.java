package com.andgivemarketing.trafficboostercore.context.dto;

import lombok.Getter;

public enum UserAgent {
    PC("PC"),
    MOBILE("모바일");

    @Getter
    private final String label;

    UserAgent(String label) {
        this.label = label;
    }

}
