package com.github.bo;

/**
 * 用户
 * 
 * @author jiangyf
 * @date 2017年7月31日 下午2:18:37
 */
public class UserBO {

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 状态（0：正常，1：锁定）
	 */
	private Byte status;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public UserBO() {
	}

	public UserBO(Long userId, String username, Byte status) {
		super();
		this.userId = userId;
		this.username = username;
		this.status = status;
	}

	public static UserBO of(Long userId, String username, Byte status) {
		return new UserBO(userId, username, status);
	}

}
