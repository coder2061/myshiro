package com.github.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 任意角色授权拦截器
 * 
 * Shiro 提供 roles 拦截器，其验证用户拥有所有角色，没有提供验证用户拥有任意角色的拦截器
 * 
 * @author jiangyf
 * @date 2017年8月7日 下午6:41:40
 */
public class AnyRolesFilter extends AccessControlFilter {
	private String unauthorizedUrl = "/unauthorized";
	private String loginUrl = "/toLogin";

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		String[] roles = (String[]) mappedValue;
		if (roles == null) {
			return true;// 如果没有设置角色参数，默认成功
		}
		for (String role : roles) {
			if (getSubject(request, response).hasRole(role)) {
				return true;
			}
		}
		return false;// 跳到onAccessDenied处理
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);
		if (subject.getPrincipal() == null) {// 表示没有登录，重定向到登录页面
			saveRequest(request);
			WebUtils.issueRedirect(request, response, loginUrl);
		} else {
			if (StringUtils.hasText(unauthorizedUrl)) {// 如果有未授权页面跳转过去
				WebUtils.issueRedirect(request, response, unauthorizedUrl);
			} else {// 否则返回401未授权状态码
				WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}
		return false;
	}
}
