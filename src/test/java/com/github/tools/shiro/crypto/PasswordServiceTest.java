package com.github.tools.shiro.crypto;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.junit.Test;

import com.github.tools.shiro.test.BaseTest;

/**
 * 验证密码
 * 
 * @author jiangyf
 * @date 2017年7月28日 下午7:02:54
 */
public class PasswordServiceTest extends BaseTest {

	@Test
	public void testPasswordServiceWithMyRealm() {
		login("classpath:shiro/crypto/shiro-passwordservice.ini", "jack", "123456");
	}

	@Test
	public void testPasswordServiceWithJdbcRealm() {
		login("classpath:shiro/crypto/shiro-jdbc-passwordservice.ini", "wu", "123");
	}

	@Test
	public void testHashedCredentialsMatcherWithMyRealm2() {
		// 使用testGeneratePassword生成的散列密码
		login("classpath:shiro-hashedCredentialsMatcher.ini", "liu", "123");
	}

	@Test
	public void testHashedCredentialsMatcherWithJdbcRealm() {
		BeanUtilsBean.getInstance().getConvertUtils().register(new EnumConverter(),
				JdbcRealm.SaltStyle.class);
		// 使用testGeneratePassword生成的散列密码
		login("classpath:shiro-jdbc-hashedCredentialsMatcher.ini", "liu", "123");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private class EnumConverter extends AbstractConverter {
		@Override
		protected String convertToString(final Object value) throws Throwable {
			return ((Enum) value).name();
		}

		@Override
		protected Object convertToType(final Class type, final Object value) throws Throwable {
			return Enum.valueOf(type, value.toString());
		}

		@Override
		protected Class getDefaultType() {
			return null;
		}

	}

	@Test(expected = ExcessiveAttemptsException.class)
	public void testRetryLimitHashedCredentialsMatcherWithMyRealm() {
		for (int i = 1; i <= 5; i++) {
			login("classpath:shiro-retryLimitHashedCredentialsMatcher.ini", "liu", "234");
		}
		login("classpath:shiro-retryLimitHashedCredentialsMatcher.ini", "liu", "234");
	}

}
