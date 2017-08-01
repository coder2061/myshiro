package com.github.tools.shiro.test;

import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import com.github.po.User;

import java.util.Collection;
import java.util.Set;

/**
 * 身份信息聚合测试
 *
 * @author jiangyf
 */
public class PrincialCollectionTest extends BaseTest {

	@Test
	public void test() {
		// 因为Realm里没有进行验证，所以相当于每个Realm都身份验证成功了
		login("classpath:shiro/realm/shiro-multi-realm2.ini", "jack", "123456");
		Subject subject = subject();
		// 获取Primary Principal（即第一个）
		Object primaryPrincipal1 = subject.getPrincipal();
		PrincipalCollection princialCollection = subject.getPrincipals();
		Object primaryPrincipal2 = princialCollection.getPrimaryPrincipal();

		// 但是因为多个Realm都返回了Principal，所以此处到底是哪个是不确定的
		Assert.assertEquals(primaryPrincipal1, primaryPrincipal2);
	}

	@Test
	public void test2() {
		// 因为Realm里没有进行验证，所以相当于每个Realm都身份验证成功了
		login("classpath:shiro/realm/shiro-multi-realm2.ini", "jack", "123456");
		PrincipalCollection princialCollection = subject().getPrincipals();

		Set<String> realmNames = princialCollection.getRealmNames();
		System.out.println(realmNames);

		// 因为MyRealm1和MyRealm2返回的凭据相同，所以排重了
		@SuppressWarnings("unchecked")
		Set<Object> principals = princialCollection.asSet(); // asList和asSet的结果一样
		System.out.println(principals);

		// 根据Realm名字获取
		@SuppressWarnings("unchecked")
		Collection<User> users = princialCollection.fromRealm("myRealm4");
		System.out.println(users);
	}
}
