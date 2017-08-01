package com.github.po;

/**
 * 角色
 * 
 * @author jiangyf
 */
public class Role {
	/**
	 * 角色id
	 */
	private Long id;

	/**
	 * 角色
	 */
	private String role;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 状态（0：正常，1：禁用）
	 */
	private Byte status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Role role = (Role) o;
		if (id != null ? !id.equals(role.id) : role.id != null)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}