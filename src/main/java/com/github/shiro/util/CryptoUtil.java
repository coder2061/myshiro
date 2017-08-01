package com.github.shiro.util;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 加密/解密
 * 
 * @author jiangyf
 * @date 2017年7月31日 上午11:25:36
 */
public class CryptoUtil {
	private static final RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private static final String algorithmName = "md5";
	private static final int hashIterations = 2;

	public static String getSalt() {
		return randomNumberGenerator.nextBytes().toHex();
	}

	public static String encryptPassword(String password) {
		return encryptPassword(password, getSalt());
	}

	public static String encryptPassword(String password, String salt) {
		return new SimpleHash(algorithmName, password, ByteSource.Util.bytes(salt), hashIterations).toHex();
	}

//	public static void main(String[] args) {
//		String salt = randomNumberGenerator.nextBytes().toHex();
//		System.out.println("----- salt -----" + salt);
//		System.out.println("----- password -----" + encryptPassword("123456", salt));
//	}
}
