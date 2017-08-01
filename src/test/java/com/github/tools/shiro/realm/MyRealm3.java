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
 * @date 2017年7月26日 上午9:41:56
 */
public class MyRealm3 implements Realm {

	@Override
	public String getName() {
		return "myRealm3";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		String username = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());

		System.out.println("--------- username:" + username);
		System.out.println("--------- password:" + password);

		if (!"rambo".equals(username)) {
			throw new UnknownAccountException();
		}

		if (!"123456".equals(password)) {
			throw new IncorrectCredentialsException();
		}
		return new SimpleAuthenticationInfo(username + "@163.com", password, getName());
	}

}
