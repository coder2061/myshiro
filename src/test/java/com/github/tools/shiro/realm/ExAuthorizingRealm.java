package com.github.tools.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 实现对and，or，not权限的支持
 * 
 * @author jiangyf
 * @date 2017年7月27日 下午4:24:59
 */
public class ExAuthorizingRealm extends AuthorizingRealm {
	private static final String AND_PERMISSION = "and";
	private static final String OR_PERMISSION = "or";
	private static final String NOT_PERMISSION = "not";

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		return null;
	}

	private boolean isPermittedWithNot(PrincipalCollection principals, String permissonString) {
		if (permissonString.startsWith(NOT_PERMISSION)) {
			return true;
		}
		return false;
	}

}
