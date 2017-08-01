package com.github.tools.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

import com.github.po.User;

/**
 * 自定义realm
 * 
 * @author jiangyf
 * @date 2017年7月31日 下午8:25:55
 */
public class MyRealm4 implements Realm {

	@Override
	public String getName() {
		return "myRealm4";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		User user = new User();
		user.setId(2L);
		user.setUsername("jack");
		user.setPassword("123456");
		return new SimpleAuthenticationInfo(user, "123456", getName());
	}

}
