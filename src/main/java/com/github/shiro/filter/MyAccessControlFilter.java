package com.github.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;

/**
 * AccessControlFilter 继承了 PathMatchingFilter
 * 
 * @author jiangyf
 * @date 2017年8月7日 下午6:25:07
 */
public class MyAccessControlFilter extends AccessControlFilter {

	// 是否允许访问，返回 true 表示允许
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		System.out.println("---------- access allowed ：" + isAccessAllowed(request, response, mappedValue));
		return false;
	}

	// 表示访问拒绝时是否自己处理，如果返回 true 表示自己不处理且继续拦截器链执行，返回 false 表示自己已经处理了（比如重定向到另一个页面）
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		System.out.println("---------- 访问拒绝也不自己处理，继续拦截器链的执行");
		redirectToLogin(request, response);
		return false;
	}

}
