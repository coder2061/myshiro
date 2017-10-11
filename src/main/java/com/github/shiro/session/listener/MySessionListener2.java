package com.github.shiro.session.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

/**
 * 会话监听器
 * 
 * 通过重写onStart,onStop或者onExpiration方法来实现只监听会话中的指定事件
 * 
 * @author jiangyf
 * @date 2017年10月10日 下午2:38:10
 */
public class MySessionListener2 extends SessionListenerAdapter {

	@Override
	public void onStart(Session session) {
		System.out.println("会话创建：" + session.getId());
	}

}
