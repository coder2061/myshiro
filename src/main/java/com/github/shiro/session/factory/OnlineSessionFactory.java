package com.github.shiro.session.factory;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SimpleSessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.shiro.session.instance.OnlineSession;
import com.github.util.HttpUtil;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义 SessionFactory
 * 
 * 创建自定义的 session， 添加一些自定义的数据 如 用户登录到的系统ip，用户状态（在线 隐身 强制退出）等
 * 
 * @author jiangyf
 * @date 2017年10月10日 下午2:58:33
 */
public class OnlineSessionFactory extends SimpleSessionFactory {
	private static final Gson GSON = new Gson();
	private static final Logger LOGGER = LoggerFactory.getLogger(OnlineSessionFactory.class);

	@Override
	public Session createSession(SessionContext initData) {
		OnlineSession session = new OnlineSession();
		if (initData != null && initData instanceof WebSessionContext) {
			WebSessionContext sessionContext = (WebSessionContext) initData;
			HttpServletRequest request = (HttpServletRequest) sessionContext.getServletRequest();
			if (request != null) {
				session.setHost(HttpUtil.getIpAddr(request));
				session.setUserAgent(request.getHeader("User-Agent"));
				session.setSystemHost(request.getLocalAddr() + ":" + request.getLocalPort());
			}
		}
		LOGGER.info("OnlineSessionFactory created session : {}", GSON.toJson(session));
		System.out.println("OnlineSessionFactory created session : " + GSON.toJson(session));
		return session;
	}
}
