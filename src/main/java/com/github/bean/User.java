package com.github.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 
 * @author jiangyf
 * @date 2017年7月3日 下午2:07:14
 */
public class User {

	private String userId;

	@NotNull(message = "用户名不能为空")
	@Pattern(regexp = "[a-zA-Z0-9_]{5,10}", message = "{username.pattern.error}")
	private String userName;

	@Size(min = 6, max = 18, message = "{password.length.error}")
	private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User() {
	}

	public User(String userId, String userName, String password) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
	}

	public static User instance(String userName, String password) {
		return new User(null, userName, password);
	}

}
