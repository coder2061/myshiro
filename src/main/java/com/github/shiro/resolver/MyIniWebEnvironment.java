package com.github.shiro.resolver;

import javax.servlet.Filter;

import org.apache.shiro.util.ClassUtils;
import org.apache.shiro.web.env.IniWebEnvironment;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;

/**
 * 自定义FilterChainResolver
 * 
 * @author jiangyf
 * @date 2017年8月7日 下午4:33:51
 */
public class MyIniWebEnvironment extends IniWebEnvironment {

	@Override
	protected FilterChainResolver createFilterChainResolver() {
		// 动态实现 url-拦截器的注册

		// 1、创建 FilterChainResolver
		PathMatchingFilterChainResolver filterChainResolver = new PathMatchingFilterChainResolver();
		// 2、创建 FilterChainManager
		DefaultFilterChainManager filterChainManager = new DefaultFilterChainManager();
		// 3、注册 Filter
		for (DefaultFilter filter : DefaultFilter.values()) {
			filterChainManager.addFilter(filter.name(),
					(Filter) ClassUtils.newInstance(filter.getFilterClass()));
		}
		// 4、配置 URL-Filter 的映射关系
		filterChainManager.addToChain("/login.jsp", "anon");
		filterChainManager.addToChain("/unauthorized.jsp", "anon");
		filterChainManager.addToChain("/**", "authc");
		filterChainManager.addToChain("/**", "roles", "admin");
		// 5、设置 Filter 的属性
		FormAuthenticationFilter authcFilter = (FormAuthenticationFilter) filterChainManager
				.getFilter("authc");
		authcFilter.setLoginUrl("/login.jsp");
		RolesAuthorizationFilter rolesFilter = (RolesAuthorizationFilter) filterChainManager
				.getFilter("roles");
		rolesFilter.setUnauthorizedUrl("/unauthorized.jsp");
		filterChainResolver.setFilterChainManager(filterChainManager);
		return filterChainResolver;
	}

}
