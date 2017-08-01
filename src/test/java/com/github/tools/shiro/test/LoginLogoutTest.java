package com.github.tools.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * 几种登录方式
 * 
 * @author jiangyf
 * @date 2017年7月26日 上午10:57:54
 */
public class LoginLogoutTest {

	@Test
	public void testLogin() {
		login("classpath:shiro/realm/shiro.ini", "rambo", "123456");
	}

	@Test
	public void testCustomRealm() {
		login("classpath:shiro/realm/shiro-realm.ini", "rambo", "123456");
	}

	@Test
	public void testCustomMultiRealm() {
		login("classpath:shiro/realm/shiro-multi-realm.ini", "rambo", "123456");
	}

	@Test
	public void testJDBCRealm() {
		login("classpath:shiro/realm/shiro-jdbc-realm.ini", "tom", "123456");
	}

	private void login(String iniPath, String username, String password) {
		// 初始化SecurityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory();
		// 获取SecurityManager实例
		SecurityManager securityManager = factory.getInstance();
		// 绑定到SecurityUtils（全局设置，设置一次即可）
		SecurityUtils.setSecurityManager(securityManager);
		// subject自动绑定到当前线程
		Subject subject = SecurityUtils.getSubject();
		// 获取身份验证token
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		// 验证登录
		try {
			subject.login(token);
			System.out.println("------------- login success -------------");
		} catch (AuthenticationException e) {
			System.out.println("------------- login fail -------------");
			e.printStackTrace();
		}

		System.out.println("------------- isAuthenticated -------------" + subject.isAuthenticated());

		// 断言用户已经登录
		Assert.assertEquals(true, subject.isAuthenticated());

		// 退出登录
		subject.logout();
		System.out.println("------------- logout success -------------");

	}

	@After
	public void tearDown() throws Exception {
		ThreadContext.unbindSubject();// 退出时请解除绑定Subject到线程 否则对下次测试造成影响
	}

}
