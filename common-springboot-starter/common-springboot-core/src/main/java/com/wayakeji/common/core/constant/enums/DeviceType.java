package com.wayakeji.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户设备类型
 *
 */
@Getter
@AllArgsConstructor
public enum DeviceType {

	/**
	 * pc端
	 */
	PC("PC"),

	/**
	 * app端
	 */
	APP("APP");

	private final String device;

}
