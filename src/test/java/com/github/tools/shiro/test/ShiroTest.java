package com.github.tools.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试
 * 
 * @author jiangyf
 * @date 2017年10月12日 下午2:24:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-*.xml", "classpath:ehcache.xml" })
public class ShiroTest {

	@Test
	public void test() {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("jack", "123456");
		subject.login(token);

		Assert.assertTrue(subject.isAuthenticated());
		subject.checkRole("admin");
		subject.checkPermission("user:create");

		token = new UsernamePasswordToken("rambo", "123456");
		subject.login(token);
	}

}
