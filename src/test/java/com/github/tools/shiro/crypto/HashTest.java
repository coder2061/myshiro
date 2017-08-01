package com.github.tools.shiro.crypto;

import java.security.SecureRandom;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.Sha384Hash;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

/**
 * 散列算法
 * 
 * 散列算法一般用于生成数据的摘要信息，是一种不可逆的算法，一般适合存储密码之类的数据，常见的散列算法如 MD5、SHA 等
 * 
 * @author jiangyf
 * @date 2017年7月28日 下午3:30:17
 */
public class HashTest {
	// 算法名，如：MD5，SHA1，SHA256，SHA1512
	public static String algorithmName = "MD5";
	// 源字符串
	public static String source = "abc123";
	// 盐值，只有系统知道的干扰数据
	public static String salt = "abcdef";
	// 散列迭代次数
	public static int hashIterations = 2;

	public static void testHash() {
		// Md5算法
		String encryptStr = new Md5Hash(source, salt, hashIterations).toBase64();
		String encryptStr2 = new Md5Hash(source, salt, hashIterations).toHex();
		System.out.println("--- Md5Hash ---" + encryptStr);
		System.out.println("--- Md5Hash ---" + encryptStr2);

		// Sha1算法
		String encryptStr3 = new Sha1Hash(source, salt, hashIterations).toBase64();
		String encryptStr4 = new Sha1Hash(source, salt, hashIterations).toHex();
		System.out.println("--- Sha1Hash ---" + encryptStr3);
		System.out.println("--- Sha1Hash ---" + encryptStr4);

		// Sha256算法
		String encryptStr5 = new Sha256Hash(source, salt, hashIterations).toBase64();
		String encryptStr6 = new Sha256Hash(source, salt, hashIterations).toHex();
		System.out.println("--- Sha256Hash ---" + encryptStr5);
		System.out.println("--- Sha256Hash ---" + encryptStr6);

		// Sha384算法
		String encryptStr51 = new Sha384Hash(source, salt, hashIterations).toBase64();
		String encryptStr61 = new Sha384Hash(source, salt, hashIterations).toHex();
		System.out.println("--- Sha384Hash ---" + encryptStr51);
		System.out.println("--- Sha384Hash ---" + encryptStr61);

		// Sha512算法
		String encryptStr7 = new Sha512Hash(source, salt, hashIterations).toBase64();
		String encryptStr8 = new Sha512Hash(source, salt, hashIterations).toHex();
		System.out.println("--- Sha512Hash ---" + encryptStr7);
		System.out.println("--- Sha512Hash ---" + encryptStr8);

		// 通用散列算法
		String encryptStr9 = new SimpleHash(algorithmName, source, salt, hashIterations).toBase64();
		String encryptStr10 = new SimpleHash(algorithmName, source, salt, hashIterations).toHex();
		System.out.println("--- SimpleHash ---" + encryptStr9);
		System.out.println("--- SimpleHash ---" + encryptStr10);
	}

	public static void testHashService() {
		// 默认算法 SHA-512
		DefaultHashService hashService = new DefaultHashService();
		// 设置算法
		hashService.setHashAlgorithmName(algorithmName);
		// 设置私盐，默认无
		ByteSource privateSalt = new SimpleByteSource(salt);
		hashService.setPrivateSalt(privateSalt);
		// 是否设置公盐，默认false
		hashService.setGeneratePublicSalt(true);
		// 设置公盐，默认就是这个
		RandomNumberGenerator rng = new SecureRandomNumberGenerator();
		hashService.setRandomNumberGenerator(rng);
		// 设置迭代次数
		hashService.setHashIterations(hashIterations);
		// 构建一个 HashRequest，传入算法、数据、公盐、迭代次数
		HashRequest request = new HashRequest.Builder().setAlgorithmName(algorithmName)
				.setSource(ByteSource.Util.bytes(source)).setSalt(ByteSource.Util.bytes(salt))
				.setIterations(2).build();
		String base64 = hashService.computeHash(request).toBase64();
		String hex = hashService.computeHash(request).toHex();
		System.out.println("--- HashService ---" + hex);
		System.out.println("--- HashService ---" + base64);
	}

	/**
	 * 生成一个随机数
	 * 
	 * @param seedStr
	 * @return
	 */
	public static String getRamdomNumber(String seedStr) {
		SecureRandomNumberGenerator generator = new SecureRandomNumberGenerator();
		generator.setSeed(seedStr.getBytes());
		generator.setDefaultNextBytesSize(2);
		generator.setSecureRandom(new SecureRandom());
		return generator.nextBytes().toHex();
	}

	public static void main(String[] args) {
		System.out.println("--- getRamdomNumber ---" + getRamdomNumber("123"));
	}

}
