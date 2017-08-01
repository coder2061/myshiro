package com.github.tools.shiro.crypto;

import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.BlowfishCipherService;
import org.apache.shiro.crypto.DefaultBlockCipherService;

/**
 * 加密/解密算法
 * 
 * Shiro 还提供对称式加密/解密算法的支持，如 AES、Blowfish 等；当前还没有提供对非对称加密/解密算法支持，未来版本可能提供
 * 
 * 加密/解密相关知识可参考snowolf的博客 http://snowolf.iteye.com/category/68576
 * 
 * @author jiangyf
 * @date 2017年7月28日 下午4:25:35
 */
public class CryptoTest {
	private static AesCipherService aesCipherService;
	private static byte[] aes_key;

	private static BlowfishCipherService blowfishCipherService;
	private static byte[] blowfish_key;

	private static DefaultBlockCipherService jcaBlockCipherService;
	private static byte[] jca_key;

	static {
		aesCipherService = new AesCipherService();
		// 设置 key 长度
		aesCipherService.setKeySize(128);
		// 生成 key
		aes_key = aesCipherService.generateNewKey().getEncoded();

		blowfishCipherService = new BlowfishCipherService();
		// 设置 key 长度
		blowfishCipherService.setKeySize(128);
		// 生成 key
		blowfish_key = blowfishCipherService.generateNewKey().getEncoded();

		// 对称加密，使用Java的JCA（javax.crypto.Cipher）加密API，常见的如 'AES', 'Blowfish'
		jcaBlockCipherService = new DefaultBlockCipherService("AES");
		// 设置 key 长度
		jcaBlockCipherService.setKeySize(128);
		// 生成 key
		jca_key = jcaBlockCipherService.generateNewKey().getEncoded();
	}

	/**
	 * AES对称式加密算法
	 * 
	 * @param plaintext
	 * @return
	 */
	public static String encryptByAES(String toEncryptStr) {
		return aesCipherService.encrypt(toEncryptStr.getBytes(), aes_key).toHex();
	}

	/**
	 * AES对称式解密算法
	 * 
	 * @param plaintext
	 * @return
	 */
	public static String decryptByAES(String toDecryptStr) {
		return new String(aesCipherService.decrypt(Hex.decode(toDecryptStr), aes_key).getBytes());
	}

	/**
	 * Blowfish对称式加密算法
	 * 
	 * @param plaintext
	 * @return
	 */
	public static String encryptByBlowfish(String toEncryptStr) {
		return blowfishCipherService.encrypt(toEncryptStr.getBytes(), blowfish_key).toHex();
	}

	/**
	 * Blowfish对称式解密算法
	 * 
	 * @param plaintext
	 * @return
	 */
	public static String decryptByBlowfish(String toDecryptStr) {
		return new String(blowfishCipherService.decrypt(Hex.decode(toDecryptStr), blowfish_key).getBytes());
	}

	/**
	 * Java的JCA对称式加密算法
	 * 
	 * @param plaintext
	 * @return
	 */
	public static String encryptByJCA(String toEncryptStr) {
		return jcaBlockCipherService.encrypt(toEncryptStr.getBytes(), jca_key).toHex();
	}

	/**
	 * Java的JCA对称式解密算法
	 * 
	 * @param plaintext
	 * @return
	 */
	public static String decryptByJCA(String toDecryptStr) {
		return new String(jcaBlockCipherService.decrypt(Hex.decode(toDecryptStr), jca_key).getBytes());
	}

	public static void main(String[] args) {
		String toEncryptStr = "abc123";

		String encryptStr = encryptByAES(toEncryptStr);
		String decryptStr = decryptByAES(encryptStr);
		System.out.println("--------- encryptByAES ---------" + encryptStr);
		System.out.println("--------- decryptByAES ---------" + decryptStr);

		String encryptStr2 = encryptByBlowfish(toEncryptStr);
		String decryptStr2 = decryptByBlowfish(encryptStr2);
		System.out.println("--------- encryptByBlowfish ---------" + encryptStr2);
		System.out.println("--------- decryptByBlowfish ---------" + decryptStr2);

		String encryptStr3 = encryptByJCA(toEncryptStr);
		String decryptStr3 = decryptByJCA(encryptStr3);
		System.out.println("--------- encryptByJCA ---------" + encryptStr3);
		System.out.println("--------- decryptByJCA ---------" + decryptStr3);
	}

}
