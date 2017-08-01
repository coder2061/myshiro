package com.github.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * shiro使用
 * 
 * @author jiangyf
 * @date 2017年7月20日 上午11:42:39
 */
@Controller
@RequestMapping("/myshiro/")
public class ShiroController {
	private static final Logger LOG = LoggerFactory.getLogger(ShiroController.class);

	@RequestMapping("/test")
	public String test(String username, String password) {
		return "success";
	}
}
