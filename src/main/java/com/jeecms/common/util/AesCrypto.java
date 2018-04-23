package com.jeecms.common.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AesCrypto {

	/**
	 * Turns array of bytes into string
	 * 
	 * @param buf
	 *                Array of bytes to convert to hex string
	 * @return Generated hex string
	 */
	private final static String algorithm = "AES";
	private static String rawKey = "b0b1f641313b2eafdd6522c4b638a5f7"; // 使用自定义密钥
	private static String cookieRawKey = "99b1f641313b2eafdd6522c4b638a599";

	private static String asHex(byte buf[]) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;
		for (i = 0; i < buf.length; i++) {
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append("0");

			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}
		return strbuf.toString();
	}

	private static byte[] asBin(String src) {
		if (src.length() < 1)
			return null;
		byte[] encrypted = new byte[src.length() / 2];
		for (int i = 0; i < src.length() / 2; i++) {
			int high = Integer.parseInt(src.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(src.substring(i * 2 + 1, i * 2 + 2), 16);
			encrypted[i] = (byte) (high * 16 + low);
		}
		return encrypted;
	}

	// 密钥生成函数
	public static String genarateRawKey() {
		try {
			// Get the KeyGenerator
			KeyGenerator kgen = KeyGenerator.getInstance(algorithm);
			kgen.init(128); // 192 and 256 bits may not be available
			// Generate the secret key specs.
			SecretKey skey = kgen.generateKey();
			byte[] raw = skey.getEncoded();
			return asHex(raw);
		} catch (Exception e) {
			// App.log.info("AES", "获取密钥出错," + e.getMessage());
			return "";
		}
	}

	public static String getEncrypt(String message, String rawKey) {
		byte[] key = asBin(rawKey);
		// Instantiate the cipher
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(key, algorithm);
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(message.getBytes());
			return asHex(encrypted);
		} catch (Exception e) {
			return null;
		}
	}

	public static String getDecrypt(String encrypted, String rawKey) {
		if (encrypted != null && !encrypted.equals("")) {
			byte[] tmp = asBin(encrypted);
			byte[] key = asBin(rawKey);

			try {
				SecretKeySpec skeySpec = new SecretKeySpec(key, algorithm);
				Cipher cipher = Cipher.getInstance(algorithm);
				cipher.init(Cipher.DECRYPT_MODE, skeySpec);
				byte[] decrypted = cipher.doFinal(tmp);
				return new String(decrypted);
			} catch (Exception e) {
				return "";
			}
		} else {
			return null;
		}
	}

	public static String getRawKey() {
		return rawKey;
	}

	public void setRawKey(String rawKey) {
		this.rawKey = rawKey;
	}

	public static String getCookieRawKey() {
		return cookieRawKey;
	}

	public static void setCookieRawKey(String cookieRawKey) {
		AesCrypto.cookieRawKey = cookieRawKey;
	}

	public static void main(String[] args) throws Exception {
		String message = "123456";//原数据
		String encrypted = getEncrypt(message, rawKey);
		System.out.println("加密后：" + encrypted);// 加密后

		String tmpStr = "fae13460b4ec3aa05b857f42f52ad3bf";//加密后数据
		String decrypted = getDecrypt(tmpStr, rawKey);// 解密串
		System.out.println("解密后：" + decrypted);
	}
}
