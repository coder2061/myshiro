package com.github.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.util.HttpUtil;

/**
 * 登录登出
 * 
 * @author jiangyf
 * @date 2017年7月27日 下午6:49:56
 */
@Controller
public class IndexController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping("/toLogin")
	public String toLogin() {
		return "view/login";
	}

	@RequestMapping("/unauthorized")
	public String unauthorized() {
		return "view/unauthorized";
	}

	@RequestMapping("/authenticated")
	public String authenticated() {
		return "view/success";
	}

	@RequestMapping("/role")
	public String role() {
		subject().checkRole("admin");
		return "view/success";
	}

	@RequestMapping("/permission")
	public String permission() {
		subject().checkPermissions("user:create");
		return "view/success";
	}

	@RequestMapping("/login")
	public String login(String username, String password, HttpServletRequest request) {
		// boolean rememberMe = true;
		// String host = HttpUtil.getIpAddr(request);
		// UsernamePasswordToken token = new UsernamePasswordToken(username,
		// password, rememberMe, host);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		String result = null;
		try {
			subject().login(token);
		} catch (UnknownAccountException e) {
			result = "用户名/密码错误：" + e.getMessage();
		} catch (IncorrectCredentialsException e) {
			result = "用户名/密码错误：" + e.getMessage();
		} catch (AuthenticationException e) {
			e.printStackTrace();
			result = "其他错误：" + e.getMessage();
		}
		if (result != null) {
			System.out.println("------- login fail:" + result);
			return "view/login";
		}
		return "view/success";
	}

	@RequestMapping("/logout")
	public String logout() {
		subject().logout();
		return "view/login";
	}

	@RequestMapping("/guest")
	public String tag() {
		return "shiro/guest";
	}

	/**
	 * 表示当前 Subject 已经通过 login 进行了身份验证；即 Subject.isAuthenticated()返回 true
	 */
	@RequiresAuthentication
	@RequestMapping("/testAuthc")
	public String testAuthc() {
		return "view/success";
	}

	/**
	 * 表示当前 Subject 已经身份验证或者通过记住我登录的
	 */
	@RequiresUser
	@RequestMapping("/testUser")
	public String testUser() {
		return "view/success";
	}

	/**
	 * 表示当前 Subject 没有身份验证或通过记住我登录过，即是游客身份
	 */
	@RequiresGuest
	@RequestMapping("/testGuest")
	public String testGuest() {
		return "view/success";
	}

	/**
	 * 表示当前 Subject 需要角色 admin 或 user
	 */
	@RequiresRoles(value = { "admin", "user" }, logical = Logical.OR)
	@RequestMapping("/testRole")
	public String testRole() {
		return "view/success";
	}

	/**
	 * 表示当前 Subject 需要权限 user:a 和 user:b
	 */
	@RequiresPermissions(value = { "menu:read", "user:read" }, logical = Logical.AND)
	@RequestMapping("/testPermission")
	public String testPermission() {
		return "view/success";
	}

}
