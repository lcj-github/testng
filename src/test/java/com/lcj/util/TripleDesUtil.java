package com.lcj.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public final class TripleDesUtil {
	
	
	static final String ALGORITHM_DES = "DESede/ECB/PKCS5Padding";

	/**
	 * 3DES加密方法
	 * 
	 * @param value
	 *            待加密信息
	 * @param desKey
	 *            密钥 24个长度以上
	 * @return 3DES加密后用Base64编码的字符串
	 */
	public static String encrypt(String value, String desKey) {
		String result = null;
		try {
			SecretKeySpec key = new SecretKeySpec(desKey.getBytes(), 0, 24, "DESede");
			Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			result = new String(Base64.encodeBase64(cipher.doFinal(value.getBytes("UTF-8"))));
		} catch (Exception e) {
		} 

		return result;

	}

	/**
	 * 3DES解密方法
	 * 
	 * @param value
	 *            3DES加密后用Base64编码的字符串
	 * @param desKey
	 *            密钥 24个长度以上
	 * @return 加密原文
	 */
	public static String decrypt(String value, String desKey) {
		String result = null;
			try {
				SecretKeySpec key = new SecretKeySpec(desKey.getBytes(), 0, 24, "DESede");
				Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
				cipher.init(Cipher.DECRYPT_MODE, key);
				result = new String(cipher.doFinal(Base64.decodeBase64(value)), "UTF-8");
			} catch (Exception e) {
			} 

		return result;
	}


}
