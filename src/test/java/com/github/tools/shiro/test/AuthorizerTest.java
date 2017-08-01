package com.github.tools.shiro.test;

import org.junit.Assert;
import org.junit.Test;

/**
 * 授权
 * 
 * @author jiangyf
 * @date 2017年7月27日 下午2:47:29
 */
public class AuthorizerTest extends BaseTest {

	@Test
	public void testIsPermitted() {
		login("classpath:shiro/authorizer/shiro-authorizer.ini", "jack", "123456");

		Assert.assertEquals(true, subject().isPermitted("user1:read"));
		Assert.assertEquals(true, subject().isPermitted("user2:read"));
	}

	@Test
	public void testIsPermitted2() {
		login("classpath:shiro/authorizer/shiro-authorizer.ini", "jack", "123456");

		Assert.assertEquals(true, subject().isPermitted("+user1+2"));
		Assert.assertEquals(true, subject().isPermitted("+user1+4"));
		Assert.assertEquals(true, subject().isPermitted("+user1+8"));

		Assert.assertEquals(true, subject().isPermitted("+user1+11"));
		Assert.assertEquals(true, subject().isPermitted("+user2+10"));
	}

	@Test
	public void testIsPermitted3() {
		login("classpath:shiro/authorizer/shiro-jdbc-authorizer.ini", "tom", "123456");

		Assert.assertEquals(true, subject().isPermitted("user1:read"));
		Assert.assertEquals(true, subject().isPermitted("user2:*"));
	}

	@Test
	public void testIsPermitted4() {
		login("classpath:shiro/authorizer/shiro-jdbc-authorizer.ini", "tom", "123456");

		Assert.assertEquals(true, subject().isPermitted("+user2+1"));
		Assert.assertEquals(true, subject().isPermitted("+user2+2"));
		Assert.assertEquals(true, subject().isPermitted("+user2+4"));
		Assert.assertEquals(true, subject().isPermitted("+user2+8"));
	}

}
