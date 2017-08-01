package com.github.po;

/**
 * 用户角色
 * 
 * @author jiangyf
 */
public class UserRole {
	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 角色id
	 */
	private Long roleId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserRole userRole = (UserRole) o;
		if (roleId != null ? !roleId.equals(userRole.roleId) : userRole.roleId != null)
			return false;
		if (userId != null ? !userId.equals(userRole.userId) : userRole.userId != null)
			return false;
		return true;
	}
}