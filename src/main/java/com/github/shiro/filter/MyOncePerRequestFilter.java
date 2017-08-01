package com.github.shiro.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.servlet.OncePerRequestFilter;

/**
 * OncePerRequestFilter
 * 
 * 保证一次请求只调用一次 doFilterInternal，即如内部的 forward 不会再多执行一次 doFilterInternal
 * 
 * @author jiangyf
 * @date 2017年8月7日 下午5:24:22
 */
public class MyOncePerRequestFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		System.out.println("------------------- MyOncePerRequestFilter ");
		chain.doFilter(request, response);
	}

}
