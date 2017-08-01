package com.github.tools.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * 自定义AuthorizingRealm
 * 
 * @author jiangyf
 * @date 2017年7月28日 下午7:18:44
 */
public class MyAuthorizingRealm3 extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		String username = "liu";
		// 加密后的密码
		String password = "be320beca57748ab9632c4121ccac0db";
		String salt = "0072273a5d87322163795118fdd7c45e";
		SimpleAuthenticationInfo sai = new SimpleAuthenticationInfo(username, password, getName());
		// 盐=用户名+随机数
		sai.setCredentialsSalt(ByteSource.Util.bytes(username + salt));
		return sai;
	}

}
