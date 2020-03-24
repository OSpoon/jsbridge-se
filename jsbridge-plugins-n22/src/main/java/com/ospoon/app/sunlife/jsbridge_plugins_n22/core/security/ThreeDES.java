package com.ospoon.app.sunlife.jsbridge_plugins_n22.core.security;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 * java版3DES加解密 加密选项：DESede/ECB/PKCS5Padding
 * 
 * @author mac
 *
 */
public class ThreeDES {

	public static byte[] decodeByte(String desStr, String key) throws Exception {
		String algorithm = "DESede/ECB/PKCS5Padding";
		Cipher cipher = Cipher.getInstance(algorithm);
		byte[] keybyte = key.getBytes("UTF-8");
		DESedeKeySpec desKeySpec = new DESedeKeySpec(keybyte);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] bytes = Base64Tool.decode(desStr);
		byte[] decodeBytes = cipher.doFinal(bytes);
		return decodeBytes;
	}
	
	/**
	 * 将数据使用3EDS算法加密，加密后使用Base64编码
	 * 
	 * @param message
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encode(String message, String key) throws Exception {
		String algorithm = "DESede/ECB/PKCS5Padding";
		Cipher cipher = Cipher.getInstance(algorithm);
		byte[] keybyte = key.getBytes("UTF-8");
		DESedeKeySpec desKeySpec = new DESedeKeySpec(keybyte);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		String result = Base64Tool.encode(cipher.doFinal(message.getBytes("UTF-8")));
		return result;
	}

	/**
	 * 将数据使用3EDS算法加密，加密后使用Base64编码
	 * 
	 * @param bytes
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encode(byte[] bytes, String key) throws Exception {
		String algorithm = "DESede/ECB/PKCS5Padding";
		Cipher cipher = Cipher.getInstance(algorithm);
		byte[] keybyte = key.getBytes("UTF-8");
		DESedeKeySpec desKeySpec = new DESedeKeySpec(keybyte);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		String result = Base64Tool.encode(cipher.doFinal(bytes));
		return result;
	}

	/**
	 * 将数据使用3EDS算法解密，解密前的数据为Base64编码
	 * 
	 * @param desStr
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decode(String desStr, String key) throws Exception {
		String algorithm = "DESede/ECB/PKCS5Padding";
		Cipher cipher = Cipher.getInstance(algorithm);
		byte[] keybyte = key.getBytes("UTF-8");
		DESedeKeySpec desKeySpec = new DESedeKeySpec(keybyte);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] bytes = Base64Tool.decode(desStr);
		byte[] decodeBytes = cipher.doFinal(bytes);

		String result = new String(decodeBytes, "UTF-8");
		return result;
	}

	/**
	 * 将数据使用3EDS算法解密，解密前的数据为Base64编码
	 * 
	 * @param bytes
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decode(byte[] bytes, String key) throws Exception {
		String algorithm = "DESede/ECB/PKCS5Padding";
		Cipher cipher = Cipher.getInstance(algorithm);
		byte[] keybyte = key.getBytes("UTF-8");
		DESedeKeySpec desKeySpec = new DESedeKeySpec(keybyte);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decodeBytes = cipher.doFinal(bytes);

		String result = new String(decodeBytes, "UTF-8");
		return result;
	}

	/**
	 * 将数据使用3EDS算法解密，解密前的数据为Base64编码
	 * 
	 * @param bytes
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decodeToBytes(byte[] bytes, String key) throws Exception {
		String algorithm = "DESede/ECB/PKCS5Padding";
		Cipher cipher = Cipher.getInstance(algorithm);
		byte[] keybyte = key.getBytes("UTF-8");
		DESedeKeySpec desKeySpec = new DESedeKeySpec(keybyte);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decodeBytes = cipher.doFinal(bytes);
		return decodeBytes;
	}

	public static void main(String[] args) throws Exception {
		
		String readFile = "{\"agent\":{\"loginPwd\": \"e87aebbb89270365ce1921fcdeaac05a\",\"token\": \"20170804165751\",\"fromChannel\": \"2\",\"agentCode\": \"1100D12336\",\"deviceNo\": \"67E271B2-C5CB-4450-BA5A-3D9B8E1C74E4\"}}";//FileUtil.readFile("C:/Users/zhanxiaolin-n22/Desktop/2.txt");
		String key = "ABCD12345678901234567890";
		//String desStr = ThreeDES.encode(message, key);
		String message2 = ThreeDES.encode(readFile, key);
		message2 = message2.replace("\n", "");
		System.out.println("加密:" + message2.trim());

	}
}