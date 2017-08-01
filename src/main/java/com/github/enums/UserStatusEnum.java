package com.github.enums;

/**
 * 用户状态（0：正常，1：锁定）
 * 
 * @author jiangyf
 */
public enum UserStatusEnum {
	NORMAL(0, "正常"), LOCKED(1, "锁定");

	private int code;
	private String desc;

	private UserStatusEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static UserStatusEnum getEnum(int code) {
		for (UserStatusEnum value : values()) {
			if (value.getCode() == (code)) {
				return value;
			}
		}
		return null;
	}
}
