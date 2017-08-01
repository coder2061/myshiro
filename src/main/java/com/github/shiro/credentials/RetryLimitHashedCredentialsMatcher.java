package com.github.shiro.credentials;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 身份验证匹配（限制验证次数）
 * 
 * @author jiangyf
 * @date 2017年7月28日 下午2:44:46
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
	// 限制验证次数
	private static final int LIMIT_RETRY_COUNT = 5;
	private Ehcache passwordRetryCache;

	public RetryLimitHashedCredentialsMatcher() {
		CacheManager cacheManager = CacheManager
				.newInstance(CacheManager.class.getClassLoader().getResource("ehcache.xml"));
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String username = (String) token.getPrincipal();
		// 判断账号是否缓存，是则表示该账号请求登录过
		Element element = passwordRetryCache.get(username);
		if (element == null) {
			element = new Element(username, new AtomicInteger(0));
			passwordRetryCache.put(element);
		}
		// 判断验证次数是否超限
		AtomicInteger retryCount = (AtomicInteger) element.getObjectValue();
		if (retryCount.incrementAndGet() > LIMIT_RETRY_COUNT) {
			throw new ExcessiveAttemptsException();
		}
		// 身份验证成功
		boolean matches = super.doCredentialsMatch(token, info);
		if (matches) {
			// 清除请求登录重试记录
			passwordRetryCache.remove(username);
		}
		return matches;
	}
}
