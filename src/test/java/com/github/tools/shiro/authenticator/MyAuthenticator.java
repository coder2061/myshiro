package com.github.tools.shiro.authenticator;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

/**
 * 自定义认证中心
 * 
 * @author jiangyf
 * @date 2017年7月27日 下午5:26:29
 */
public class MyAuthenticator extends ModularRealmAuthenticator {

	public void setBytes(byte[] bytes) {
		System.out.println(new String(bytes));
	}

	public void setArray(int[] ints) {
		System.out.println(Arrays.toString(ints));
	}

	public void setSet(Set<Realm> realms) {
		System.out.println(realms);
	}

	public void setMap(Map<Object, Object> maps) {
		System.out.println(maps);
		System.out.println(maps.get("1"));
	}

}
