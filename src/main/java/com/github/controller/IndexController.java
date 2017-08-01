package com.github.controller;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录登出
 * 
 * @author jiangyf
 * @date 2017年7月27日 下午6:49:56
 */
@Controller
public class IndexController extends BaseController {
	private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping("/toLogin")
	public String toLogin() {
		return "login";
	}

	@RequestMapping("/unauthorized")
	public String unauthorized() {
		return "unauthorized";
	}

	@RequestMapping("/authenticated")
	public String authenticated() {
		return "success";
	}

	@RequestMapping("/role")
	public String role() {
		subject().checkRole("admin");
		return "success";
	}

	@RequestMapping("/permission")
	public String permission() {
		subject().checkPermissions("user:create");
		return "success";
	}

	@RequestMapping("/login")
	public String login(String username, String password) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		String result = null;
		try {
			subject().login(token);
		} catch (UnknownAccountException e) {
			result = "用户名/密码错误：" + e.getMessage();
		} catch (IncorrectCredentialsException e) {
			result = "用户名/密码错误：" + e.getMessage();
		} catch (AuthenticationException e) {
			result = "其他错误：" + e.getMessage();
		}
		if (result != null) {
			System.out.println("------- login fail:" + result);
			return "login";
		}
		return "success";
	}

}
