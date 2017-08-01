package com.github.tools.shiro.crypto;

import org.apache.shiro.codec.Base64;
import org.junit.Assert;
import org.junit.Test;

/**
 * Base64编码/解码
 * 
 * @author jiangyf
 * @date 2017年7月28日 下午2:44:46
 */
public class Base64Test {

	private String str = "abc123";

	@Test
	public void test() {
		String encodeStr = Base64.encodeToString(str.getBytes());
		String decodeStr = Base64.decodeToString(encodeStr);
		System.out.println(encodeStr);
		System.out.println(decodeStr);
		Assert.assertEquals(str, decodeStr);
	}

}
