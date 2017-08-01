package com.github.tools.shiro.resolver;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

import com.github.tools.shiro.perssion.BitPermission;

/**
 * 位权限解析器
 * 
 * @author jiangyf
 * @date 2017年7月27日 下午2:07:45
 */
public class BitAndWildPermissionResolver implements PermissionResolver {

	@Override
	public Permission resolvePermission(String permissionString) {
		if (permissionString.startsWith("+")) {
			return new BitPermission(permissionString);
		}
		return new WildcardPermission(permissionString);
	}

}
