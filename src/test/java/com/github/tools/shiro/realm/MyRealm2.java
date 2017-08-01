package com.github.tools.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/**
 * 自定义realm
 * 
 * @author jiangyf
 * @date 2017年7月20日 下午4:42:15
 */
public class MyRealm2 implements Realm {

	@Override
	public String getName() {
		return "myRealm2";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		// 该realm仅支持UsernamePasswordToken
		return token instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		String username = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());

		System.out.println("--------- username:" + username);
		System.out.println("--------- password:" + password);

		if (!"jack".equals(username)) {
			throw new UnknownAccountException();
		}
		if (!"123456".equals(password)) {
			throw new IncorrectCredentialsException();
		}

		// 身份认证成功，返回认证信息
		return new SimpleAuthenticationInfo(username, password, getName());
	}

}
