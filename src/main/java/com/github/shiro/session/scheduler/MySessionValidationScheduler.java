package com.github.shiro.session.scheduler;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ReflectionUtils;

import com.github.shiro.util.SerializableUtil;
import com.github.util.JdbcTemplateUtil;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 会话验证调度器
 * 
 * quartz 会话验证调度器实现都是直接调用 AbstractValidatingSessionManager 的 validateSessions
 * 方法进行验证，其直接调用 SessionDAO 的 getActiveSessions
 * 方法获取所有会话进行验证，如果会话比较多，会影响性能；可以考虑如分页获取会话并进行验证
 * 
 * @author jiangyf
 * @date 2017年10月10日 下午2:37:24
 */
public class MySessionValidationScheduler implements SessionValidationScheduler, Runnable {
	private static final Logger LOGGER = LoggerFactory.getLogger(MySessionValidationScheduler.class);

	private JdbcTemplate jdbcTemplate = JdbcTemplateUtil.jdbcTemplate();

	ValidatingSessionManager sessionManager;
	// 使用 JDK 的 ScheduledExecutorService 进行定期调度并验证会话是否过期
	private ScheduledExecutorService service;
	// 默认时间间隔
	private long interval = DefaultSessionManager.DEFAULT_SESSION_VALIDATION_INTERVAL;
	// 是否开启会话验证
	private boolean enabled = false;

	public MySessionValidationScheduler() {
		super();
	}

	public ValidatingSessionManager getSessionManager() {
		return sessionManager;
	}

	public void setSessionManager(ValidatingSessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public void enableSessionValidation() {
		if (this.interval > 0l) {
			this.service = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
				public Thread newThread(Runnable r) {
					Thread thread = new Thread(r);
					thread.setDaemon(true);
					return thread;
				}
			});
			this.service.scheduleAtFixedRate(this, interval, interval, TimeUnit.MILLISECONDS);
			this.enabled = true;
		}
	}

	@Override
	public void run() {
		LOGGER.debug("Executing session validation...");
		long startTime = System.currentTimeMillis();

		// 分页获取会话并验证
		String sql = "select session from sessions limit ?,?";
		int start = 0; // 起始记录
		int size = 20; // 每页大小
		List<String> sessionList = jdbcTemplate.queryForList(sql, String.class, start, size);
		while (sessionList.size() > 0) {
			for (String sessionStr : sessionList) {
				validateSession(sessionStr);
			}
			start = start + size;
			sessionList = jdbcTemplate.queryForList(sql, String.class, start, size);
		}

		long stopTime = System.currentTimeMillis();
		LOGGER.debug("Session validation completed successfully in " + (stopTime - startTime) + " ms.");
	}

	/**
	 * 会话校验
	 * 
	 * @param sessionStr
	 */
	private void validateSession(String sessionStr) {
		try {
			Session session = SerializableUtil.deserialize(sessionStr);
			Method validateMethod = ReflectionUtils.findMethod(AbstractValidatingSessionManager.class,
					"validate", Session.class, SessionKey.class);
			validateMethod.setAccessible(true);
			ReflectionUtils.invokeMethod(validateMethod, sessionManager, session,
					new DefaultSessionKey(session.getId()));
		} catch (Exception e) {
			LOGGER.error("Session validation completed failed, session:{}, error:{}", sessionStr,
					e.getMessage());
		}
	}

	@Override
	public void disableSessionValidation() {
		this.service.shutdownNow();
		this.enabled = false;
	}
}
