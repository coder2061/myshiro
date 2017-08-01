package com.github.tools.shiro.test;

import java.util.Arrays;

import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Assert;
import org.junit.Test;

/**
 * 角色
 * 
 * 隐式角色（explicit role）：基于角色的访问控制，粗粒度，代码耦合性高，实现简单，操作复杂，不灵活
 * 
 * 显示角色（implicit role）：基于资源（权限）的访问控制，细粒度，代码耦合性低，实现复杂，操作简单，较灵活
 * 
 * @author jiangyf
 * @date 2017年7月26日 下午4:05:55
 */
public class RoleTest extends BaseTest {

	/**
	 * 判断用户是否拥有某个/某些角色
	 */
	@Test
	public void testHasRole() {
		login("classpath:shiro/role/shiro-role.ini", "jack", "123456");

		Assert.assertEquals(true, subject().hasRole("role2"));
		Assert.assertEquals(true, subject().hasAllRoles(Arrays.asList("role1", "role2")));

		boolean[] results = subject().hasRoles(Arrays.asList("role1", "role2", "role3"));
		for (boolean result : results) {
			System.out.println("------------ result:" + result);
			Assert.assertEquals(true, result);
		}
	}

	/**
	 * 判断用户是否拥有某个/某些角色
	 * 
	 * 与hasRole()区别，判断结果为false，抛出UnauthorizedException异常
	 */
	@Test(expected = UnauthorizedException.class)
	public void testCheckRole() {
		login("classpath:shiro/role/shiro-role.ini", "jack", "123456");

		subject().checkRole("role1");
		subject().checkRoles(Arrays.asList("role1", "role2"));
		subject().checkRoles("role1", "role2", "role3");
	}

}
