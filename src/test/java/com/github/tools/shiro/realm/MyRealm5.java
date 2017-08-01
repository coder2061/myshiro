package com.github.tools.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/**
 * 自定义realm
 * 
 * @author jiangyf
 * @date 2017年7月26日 上午9:41:56
 */
public class MyRealm5 implements Realm {

	@Override
	public String getName() {
		return "myRealm5";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		return new SimpleAuthenticationInfo("jack", "123456", getName());
	}

}
