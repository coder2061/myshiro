package com.github.tools.shiro.test;

import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.junit.Assert;
import org.junit.Test;

/**
 * 身份验证
 * 
 * @author jiangyf
 * @date 2017年7月31日 下午7:07:12
 */
public class UserRealmTest extends BaseTest {

	@Test
	public void testLogin() {
		login("classpath:shiro/realm/shiro-auth.ini", "jack", "123456");
		Assert.assertEquals(true, subject().isAuthenticated());
	}

	@Test
	public void testIsPermitted() {
		login("classpath:shiro/realm/shiro-auth.ini", "jack", "123456");
		Assert.assertEquals(true, subject().isPermitted("user:read"));
	}

	@Test(expected = UnknownAccountException.class)
	public void testLoginFailWithUnknownUsername() {
		login("classpath:shiro/realm/shiro-auth.ini", "jack" + "1", "123456");
	}

	@Test(expected = IncorrectCredentialsException.class)
	public void testLoginFailWithErrorPassowrd() {
		login("classpath:shiro/realm/shiro-auth.ini", "jack", "123456" + "1");
	}

	@Test(expected = LockedAccountException.class)
	public void testLoginFailWithLocked() {
		login("classpath:shiro/realm/shiro-auth.ini", "jack", "123456" + "1");
	}

	@Test(expected = ExcessiveAttemptsException.class)
	public void testLoginFailWithLimitRetryCount() {
		for (int i = 1; i <= 5; i++) {
			try {
				login("classpath:shiro/realm/shiro-auth.ini", "jack", "123456" + "1");
			} catch (Exception e) {
				System.out.println("身份验证错误次数：" + i);
			}
		}
		login("classpath:shiro/realm/shiro-auth.ini", "jack", "123456" + "1");
		// 需要清空缓存，否则后续的执行就会遇到问题(或者使用一个全新账户测试)
	}

	@Test
	public void testHasRole() {
		login("classpath:shiro/realm/shiro-auth.ini", "jack", "123456");
		Assert.assertTrue(subject().hasRole("admin"));
	}

	@Test
	public void testNoRole() {
		login("classpath:shiro/realm/shiro-auth.ini", "jack", "123456");
		Assert.assertFalse(subject().hasRole("admin"));
	}

	@Test
	public void testHasPermission() {
		login("classpath:shiro/realm/shiro-auth.ini", "jack", "123456");
		Assert.assertTrue(subject().isPermittedAll("user:create", "menu:create"));
	}

	@Test
	public void testNoPermission() {
		login("classpath:shiro/realm/shiro-auth.ini", "jack", "123456");
		Assert.assertFalse(subject().isPermitted("user:create"));
	}

}
