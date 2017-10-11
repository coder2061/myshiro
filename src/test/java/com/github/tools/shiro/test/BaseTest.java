package com.github.tools.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;

import com.github.service.UserService;
import com.github.service.impl.UserServiceImpl;

/**
 * 基类
 * 
 * @author jiangyf
 * @date 2017年7月27日 下午2:21:59
 */
public abstract class BaseTest {

	protected UserService userService = new UserServiceImpl();

	/**
	 * 登录
	 * 
	 * @param configFile
	 * @param username
	 * @param password
	 */
	public void login(String configFile, String username, String password) {
		// IniSecurityManagerFactory支持“classpath:”（类路径）、“file:”（文件系统-默认）、“url:”（网络）三种路径格式
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(configFile);

		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		// 当前主机地址
		token.setHost("127.0.0.1");
		subject.login(token);
	}

	public Subject subject() {
		return SecurityUtils.getSubject();
	}

	@After
	public void tearDown() throws Exception {
		// 退出时请解除绑定Subject到线程 否则对下次测试造成影响
		ThreadContext.unbindSubject();
	}

}
