package com.github.tools.shiro.test;

import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.PrincipalCollection;
import org.junit.Assert;
import org.junit.Test;

/**
 * 几种验证策略
 * 
 * @author jiangyf
 * @date 2017年7月26日 上午9:55:21
 */
public class AuthenticatorTest extends BaseTest {

	@Test
	public void testAllSuccessfulStragetyWitSuccess() {
		login("classpath:shiro/strategy/shiro-authenticator-all-success.ini", "jack", "123456");
		// 得到一个身份集合，其包含了Realm验证成功的身份信息
		PrincipalCollection principalCollection = subject().getPrincipals();
		Assert.assertEquals(2, principalCollection.asList().size());
	}

	@Test(expected = UnknownAccountException.class)
	public void testAllSuccessfulStragetyWitFail() {
		login("classpath:shiro/strategy/shiro-authenticator-all-fail.ini", "jack", "123456");
	}

	@Test
	public void testAtLeastOneSuccessfulStrategyWithSuccess() {
		login("classpath:shiro/strategy/shiro-authenticator-atLeastOne-success.ini", "jack", "123456");
		// 得到一个身份集合，其包含了Realm验证成功的身份信息
		PrincipalCollection principalCollection = subject().getPrincipals();
		Assert.assertEquals(1, principalCollection.asList().size());
	}

	@Test
	public void testFirstOneSuccessfulStrategyWithSuccess() {
		login("classpath:shiro/strategy/shiro-authenticator-first-success.ini", "jack", "123456");
		// 得到一个身份集合，其包含了第一个Realm验证成功的身份信息
		PrincipalCollection principalCollection = subject().getPrincipals();
		Assert.assertEquals(1, principalCollection.asList().size());
	}

	@Test
	public void testAtLeastTwoStrategyWithSuccess() {
		login("classpath:shiro/strategy/shiro-authenticator-atLeastTwo-success.ini", "jack", "123456");
		// 得到一个身份集合，因为myRealm1和myRealm4返回的身份一样所以输出时只返回一个
		PrincipalCollection principalCollection = subject().getPrincipals();
		Assert.assertEquals(2, principalCollection.asList().size());
	}

	@Test
	public void testOnlyOneStrategyWithSuccess() {
		login("classpath:shiro/strategy/shiro-authenticator-onlyone-success.ini", "jack", "123456");
		// 得到一个身份集合，因为myRealm1和myRealm4返回的身份一样所以输出时只返回一个
		PrincipalCollection principalCollection = subject().getPrincipals();
		Assert.assertEquals(1, principalCollection.asList().size());
	}

}
