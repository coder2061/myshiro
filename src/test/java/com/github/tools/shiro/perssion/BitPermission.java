package com.github.tools.shiro.perssion;

import org.apache.shiro.authz.Permission;

import com.alibaba.druid.util.StringUtils;

/**
 * 实现位移方式的权限
 * 
 * 权限字符串格式：+资源字符串+权限位+实例 ID；
 * 
 * 规则：以+开头 中间通过+分割
 *
 * 权限：0 表示所有权限 ；1 新增 0001； 2 查看 0010； 4 修改 0100； 8 删除 1000
 *
 * 如 +user+10 表示对资源user拥有修改/查看权限
 * 
 * 注意：不能把我们自定义的如“+user1+10”配置到 INI 配置文件，即使有IniRealm 完成，因为 IniRealm 在 new
 * 完成后就会解析这些权限字符串，默认使用了WildcardPermissionResolver 完成
 *
 * 不考虑一些异常情况
 * 
 * @author jiangyf
 * @date 2017年7月27日 上午11:37:53
 */
public class BitPermission implements Permission {
	/**
	 * 资源标识
	 */
	private String resourceIdentify;
	/**
	 * 权限位
	 */
	private int permissionBit;
	/**
	 * 实例 ID
	 */
	private String instanceId;

	/**
	 * 解析权限字符串
	 * 
	 * @param permissionString
	 */
	public BitPermission(String permissionString) {
		String[] array = permissionString.split("\\+");
		// 资源标识
		if (array.length > 1) {
			this.resourceIdentify = array[1];
		}
		if (StringUtils.isEmpty(this.resourceIdentify)) {
			this.resourceIdentify = "*";
		}
		// 解析权限位
		if (array.length > 2) {
			this.permissionBit = Integer.valueOf(array[2]);
		}
		// 解析实例
		if (array.length > 3) {
			this.instanceId = array[3];
		}
		if (StringUtils.isEmpty(this.instanceId)) {
			this.instanceId = "*";
		}
	}

	@Override
	public boolean implies(Permission p) {
		if (!(p instanceof BitPermission)) {
			return false;
		}
		BitPermission other = (BitPermission) p;
		if (!("*".equals(this.resourceIdentify) || this.resourceIdentify.equals(other.resourceIdentify))) {
			return false;
		}
		if (!(this.permissionBit == 0 || (this.permissionBit & other.permissionBit) != 0)) {
			return false;
		}
		if (!("*".equals(this.instanceId) || this.instanceId.equals(other.instanceId))) {
			return false;
		}
		return true;
	}

}
