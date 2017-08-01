package com.github.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.servlet.AdviceFilter;

/**
 * AdviceFilter
 * 
 * 提供了 AOP 的功能，其实现和 SpringMVC 中的 Interceptor 思想一样
 * 
 * @author jiangyf
 * @date 2017年8月5日 下午2:42:24
 */
public class MyAdviceFilter extends AdviceFilter {

	// 进行请求的预处理，然后根据返回值决定是否继续处理（true：继续过滤器链；false：将中断后续拦截器链的执行）；可以通过它实现权限控制返回
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		System.out.println("====预处理/前置处理");
		return true;
	}

	// 执行完拦截器链之后正常返回后执行
	@Override
	protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
		System.out.println("====后处理/后置返回处理");
	}

	// 不管最后有没有异常，afterCompletion 都会执行，完成如清理资源功能
	@Override
	public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception)
			throws Exception {
		System.out.println("====完成处理/后置最终处理");
	}

}
