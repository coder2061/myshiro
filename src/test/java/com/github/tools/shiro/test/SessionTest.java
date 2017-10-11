package com.github.tools.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

/**
 * shiro 会话测试
 * 
 * @author jiangyf
 * @date 2017年10月11日 下午2:20:52
 */
public class SessionTest extends BaseTest {

	@Test
	public void testGetSession() throws Exception {
		login("classpath:shiro/session/shiro.ini", "jack", "123456");
		Subject subject = SecurityUtils.getSubject();
		// 获取会话
		Session session = subject.getSession(false);
		if (session == null) {
			return;
		}

		System.out.println("获取会话ID--->" + session.getId());
		System.out.println("获取当前登录用户主机地址--->" + session.getHost());
		System.out.println("获取超时时间--->" + session.getTimeout());
		System.out.println("获取会话创建时间--->" + session.getStartTimestamp());
		System.out.println("获取最后访问时间--->" + session.getLastAccessTime());

		Thread.sleep(1000L);
		session.touch();

		System.out.println("获取更新会话后最后访问时间--->" + session.getLastAccessTime());

		// 会话属性操作
		session.setAttribute("key", "123");
		System.out.println("获取会话属性key值--->" + session.getAttribute("key"));
		Assert.assertEquals("123", session.getAttribute("key"));
		session.removeAttribute("key");
		System.out.println("获取会话属性key值--->" + session.getAttribute("key"));
	}

}
