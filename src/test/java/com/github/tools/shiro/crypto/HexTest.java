package com.github.tools.shiro.crypto;

import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.junit.Assert;
import org.junit.Test;

/**
 * 16进制编码/解码
 * 
 * @author jiangyf
 * @date 2017年7月28日 下午2:45:49
 */
public class HexTest {
	private String str = "abc123";

	@Test
	public void test() {
		String encodeStr = Hex.encodeToString(str.getBytes());
		String decodeStr = new String(Hex.decode(encodeStr));
		System.out.println(encodeStr);
		System.out.println(decodeStr);
		Assert.assertEquals(str, decodeStr);

		// CodecSupport提供了toBytes(str, "utf-8")/toString(bytes,"utf-8")用于在byte数组/String之间转换
		String encodeStr2 = Hex.encodeToString(CodecSupport.toBytes(str));
		String decodeStr2 = CodecSupport.toString(Hex.decode(encodeStr));
		System.out.println(encodeStr2);
		System.out.println(decodeStr2);
		Assert.assertEquals(str, decodeStr2);
	}

}
