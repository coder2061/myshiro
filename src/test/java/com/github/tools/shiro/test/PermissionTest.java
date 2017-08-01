package com.github.tools.shiro.test;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.junit.Assert;
import org.junit.Test;

/**
 * 权限
 * 
 * @author jiangyf
 * @date 2017年7月27日 下午2:39:37
 */
public class PermissionTest extends BaseTest {

	/**
	 * 判断用户是否拥有某个/某些权限
	 */
	@Test
	public void testIsPermitted() {
		login("classpath:shiro/role/shiro-permission.ini", "jack", "123456");

		Assert.assertEquals(true, subject().isPermitted(new WildcardPermission("user:create")));
		Assert.assertEquals(true, subject().isPermitted("user:create"));
		Assert.assertEquals(true, subject().isPermitted("user:read"));
		Assert.assertEquals(true, subject().isPermittedAll("user:create", "user:read"));
		boolean[] results = subject().isPermitted("user:create", "user:read", "user:update");
		for (boolean result : results) {
			System.out.println("------------ result:" + result);
			Assert.assertEquals(true, result);
		}
	}

	/**
	 * 判断用户是否拥有某个/某些权限
	 * 
	 * 与isPermitted()区别，判断结果为false，抛出UnauthorizedException异常
	 */
	@Test(expected = UnauthorizedException.class)
	public void testCheckPermission() {
		login("classpath:shiro/role/shiro-permission.ini", "jack", "123456");

		// 单个资源单个权限
		subject().checkPermission("user:read");
		subject().checkPermissions("system:user:read");

		// 单个资源多个权限
		subject().checkPermissions("user:create", "user:read", "user:update", "user:delete");
		subject().checkPermissions("user:create,read,update,delete");

		subject().checkPermissions("system:user:create", "system:user:read", "system:user:update",
				"system:user:delete");
		subject().checkPermissions("system:user:create,read,update,delete");

		// 单个资源全部权限
		subject().checkPermissions("system:user:*");
		subject().checkPermissions("system:user");

		// 单个实例单个权限
		subject().checkPermissions("user:create:1");
		// 单个实例多个权限
		subject().checkPermissions("user:create:1", "user:read:1", "user:update:1", "user:delete:1");
		subject().checkPermissions("user:create,read,update,delete:1");
		// 单个实例全部权限
		subject().checkPermissions("user:*:1"); // 错误示例
		subject().checkPermissions("user:create:1", "user:read:1", "user:update:1", "user:delete:1");
		// 所有实例单个权限
		subject().checkPermissions("user:1:*"); // 错误示例
		subject().checkPermissions("user:create:1", "user:create:2", "user:create:3", "user:create:4");
		// 所有实例全部权限
		subject().checkPermissions("user:*:*"); // 错误示例
		subject().checkPermissions("user:create:1", "user:read:2", "user:update:3", "user:delete:4");
	}

}
