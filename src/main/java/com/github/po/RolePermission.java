package com.github.po;

/**
 * 角色权限
 * 
 * @author jiangyf
 */
public class RolePermission {
	/**
	 * 角色id
	 */
	private Long roleId;

	/**
	 * 权限id
	 */
	private Long permissionId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		RolePermission that = (RolePermission) o;
		if (permissionId != null ? !permissionId.equals(that.permissionId) : that.permissionId != null)
			return false;
		if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null)
			return false;
		return true;
	}
}