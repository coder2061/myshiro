package com.github.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.bean.User;

/**
 * 
 * @author jiangyf
 * @date 2017年7月3日 上午10:51:35
 */
@Controller
@RequestMapping("/user/")
public class UserController {

	@RequestMapping("/index")
	public String index() {
		return "validator_test";
	}

	@RequestMapping("/login")
	public String login(@Validated @ModelAttribute("testModel") User user, BindingResult bindingResult,
			Model model) {
		model.addAttribute("user", user);
		return "validator_test";
	}

	@RequestMapping("/test")
	@Validated
	@ResponseBody
	public User test(@NotNull(message = "用户名不能为空") String userName,
			@Size(min = 6, max = 18, message = "{password.length.error}") String password) {
		return User.instance(userName, password);
	}

}
