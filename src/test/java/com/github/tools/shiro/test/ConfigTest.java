package com.github.tools.shiro.test;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.tools.shiro.realm.MyRealm;

/**
 * shiro配置
 * 
 * @author jiangyf
 * @date 2017年7月27日 下午4:41:00
 */
public class ConfigTest extends BaseTest {

	@Test
	public void testConfig() {
		// 初始化securityManager
		DefaultSecurityManager securityManager = new DefaultSecurityManager();

		// 认证中心
		ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
		AtLeastOneSuccessfulStrategy authenticationStrategy = new AtLeastOneSuccessfulStrategy();
		authenticator.setAuthenticationStrategy(authenticationStrategy);
		securityManager.setAuthenticator(authenticator);

		// 授权中心
		ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
		WildcardPermissionResolver permissionResolver = new WildcardPermissionResolver();
		authorizer.setPermissionResolver(permissionResolver);
		securityManager.setAuthorizer(authorizer);

		// 域
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/shiro");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		JdbcRealm realm = new JdbcRealm();
		realm.setDataSource(dataSource);
		realm.setPermissionsLookupEnabled(true);

		MyRealm realm2 = new MyRealm();
		securityManager.setRealms(Arrays.asList((Realm) realm, realm2));

		// securityManager注入
		SecurityUtils.setSecurityManager(securityManager);

		// 主体
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("rambo", "123456");
		subject.login(token);

		Assert.assertEquals(true, subject.isAuthenticated());
	}

	@Test
	public void testConfig2() {
		login("classpath:shiro/config/shiro-config.ini", "rambo", "123456");
		Assert.assertTrue(subject().isAuthenticated());
	}

	@Test
	public void testConfig3() {
		login("classpath:shiro/config/shiro-main-config.ini", "rambo", "123456");
		Assert.assertTrue(subject().isAuthenticated());
	}
}
