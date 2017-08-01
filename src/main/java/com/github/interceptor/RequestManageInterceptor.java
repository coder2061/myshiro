package com.github.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.github.bean.RequestInfo;

/**
 * 防止同一IP多次请求攻击
 * 
 * 防止入侵者，通过死循环同一时间批量向服务器请求数据，导致服务器内存开销不断膨胀，最后直接瘫痪
 * 
 * @author jiangyf
 * @date 2017年7月5日 上午11:36:07
 */
public class RequestManageInterceptor implements HandlerInterceptor {

	/**
	 * 访问黑名单
	 */
	public static ThreadLocal<Map<String, Object>> blacklist = new ThreadLocal<Map<String, Object>>();
	/**
	 * 时间间隔
	 */
	private static final int INTERVAL_TIME = 3000;
	/**
	 * 限制请求次数
	 */
	private static final int LIMIT_REQUEST_COUNT = 10;
	/**
	 * 最大请求次数
	 */
	private static final int MAX_REQUEST_COUNT = 20;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 获取请求IP地址
		String ip = getIpAddr(request);

		Map<String, Object> blacklistMap = blacklist.get();
		// 判断是否加入黑名单
		if (blacklistMap != null && blacklistMap.get(ip) != null) {
			response.getWriter().append("{\"code\":\"error\",\"msg\":\"请求太过频繁，请稍后再试！\"}");
			return false;
		}

		// 获取session中的缓存对象
		RequestInfo requestInfo = (RequestInfo) request.getSession().getAttribute(ip);
		if (requestInfo == null) {
			return sessionRequestInfo(ip, request);
		}

		Long intervalTime = System.currentTimeMillis() - requestInfo.getCreateTime();
		int count = requestInfo.getCount().intValue();
		// 请求间隔时间大于3秒，可以直接通过,保存这次的请求
		if (intervalTime > INTERVAL_TIME) {
			return sessionRequestInfo(ip, request);
		}

		// 请求间隔时间小于3秒，但请求次数小于限制请求次数，更新session请求次数
		if (count < LIMIT_REQUEST_COUNT) {
			requestInfo.setCount(count + 1);
			request.getSession().setAttribute(ip, requestInfo);
			return true;
		}

		// 请求间隔时间小于3秒，但请求次数大于最大请求次数，加入黑名单
		if (count > MAX_REQUEST_COUNT) {
			if (blacklistMap == null) {
				blacklistMap = new HashMap<String, Object>();
			}
			blacklistMap.put(ip, count + 1);
			blacklist.set(blacklistMap);
		}

		// 请求间隔时间小于3秒，但请求次数不小于限制请求次数，返回请求失败提示
		response.getWriter().append("{\"code\":\"error\",\"msg\":\"请求太过频繁，请稍后再试！\"}");
		return false;
	}

	/**
	 * 保存请求信息到session
	 * 
	 * @param ip
	 * @param request
	 * @return
	 */
	private boolean sessionRequestInfo(String ip, HttpServletRequest request) {
		RequestInfo requestInfo = new RequestInfo();
		requestInfo.setIp(ip);
		requestInfo.setCount(1);
		requestInfo.setCreateTime(System.currentTimeMillis());
		request.getSession().setAttribute(ip, requestInfo);
		return true;
	}

	/**
	 * 获取请求IP地址
	 * 
	 * @param request
	 * @return
	 */
	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) throws Exception {
	}
}
