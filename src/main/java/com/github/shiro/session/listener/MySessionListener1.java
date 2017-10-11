package com.github.shiro.session.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * 会话监听器
 * 
 * 用于监听会话创建、过期及停止事件
 * 
 * @author jiangyf
 * @date 2017年10月10日 下午2:37:24
 */
public class MySessionListener1 implements SessionListener {

	@Override
	public void onStart(Session session) {// 会话创建时触发
		System.out.println("会话创建：" + session.getId());
	}

	@Override
	public void onStop(Session session) {// 退出/会话过期时触发
		System.out.println("会话停止：" + session.getId());
	}

	@Override
	public void onExpiration(Session session) {// 会话过期时触发
		System.out.println("会话过期：" + session.getId());
	}

}
