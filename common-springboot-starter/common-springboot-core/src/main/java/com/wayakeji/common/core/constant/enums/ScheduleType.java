package com.wayakeji.common.core.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ScheduleType {
    /**
     * 正常
     */
    NORMAL("0"),
    /**
     * 暂停
     */
    PAUSE("1");

    private final String value;
}
