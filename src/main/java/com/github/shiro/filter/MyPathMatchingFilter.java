package com.github.shiro.filter;

import java.util.Arrays;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.PathMatchingFilter;

/**
 * PathMatchingFilter
 * 
 * 继承了 AdviceFilter，提供 url 模式过滤的功能，如果需要对指定的请求进行处理，可以扩展 PathMatchingFilter
 * 
 * @author jiangyf
 * @date 2017年8月7日 下午6:11:33
 */
public class MyPathMatchingFilter extends PathMatchingFilter {

	// 会进行 url 模式与请求 url 进行匹配，如果匹配会调用 onPreHandle；如果没有配置 url 模式/没有 url 模式匹配，默认直接返回 true
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		return true;
	}

	// 如果 url 模式与请求 url 匹配，那么会执行 onPreHandle，并把该拦截器配置的参数传入。默认什么都不处理直接返回 true
	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		System.out.println("url matches, config is " + Arrays.toString((String[]) mappedValue));
		return true;
	}

}
