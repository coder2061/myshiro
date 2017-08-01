package com.github.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 基类
 * 
 * @author jiangyf
 * @date 2017年7月27日 下午6:52:17
 */
public class BaseController {

	protected Subject subject() {
		return SecurityUtils.getSubject();
	}

}
