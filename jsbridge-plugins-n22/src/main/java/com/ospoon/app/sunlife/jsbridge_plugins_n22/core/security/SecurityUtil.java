package com.ospoon.app.sunlife.jsbridge_plugins_n22.core.security;
/**
 * Copyright: 版权所有 Company: 北京耀诚立信科技有限公司
 * @author 张鑫 Spoon
 * @version 1.0.0
 * @create 2019年8月20日 上午11:13:27
 * eg: 
	String sign_key = "";
	try {
		String decoder = SecurityUtil.decoder(sign_key, "");
		System.out.println(decoder);
		String encoder = SecurityUtil.encoder(sign_key, decoder);
		System.out.println(encoder);
		String json = "";
		String sign = SecurityUtil.getSign(sign_key, json);
		System.out.println(sign);
	} catch (Exception e) {
		e.printStackTrace();
	}
 */
public class SecurityUtil {

	/**
	* 获取报文验签
	* 
	* @Title: getSign 
	* @Description: TODO(获取报文验签) 
	* @param @param sign_key 对报文进行加密的钥匙
	* @param @param json 进行加密的报文
	* @param @return
	* @param @throws Exception  参数为传值会进行异常提示
	* @return String    返回类型 
	* @throws
	 */
	public static String getSign(String sign_key, String json) throws Exception {
		if (sign_key != null && sign_key != "") {
			if (sign_key.length() == 24) {
				if(json!=null && json!=""){
					MD5Tool md5 = new MD5Tool(sign_key.getBytes());
					byte[] result = md5.ComputeHash(json.getBytes("UTF-8"));
					return MD5Tool.byte2hex(result);
				}else{
					throw new Exception("Json Must not be empty");
				}
			}else{
				throw new Exception("Sign Key Length must be 24");
			}
		} else {
			throw new Exception("Sign Key Must not be empty");
		}
	}
	
	/**
	 * 对报文进行加密
	* @Title: encoder 
	* @Description: TODO(对报文进行加密) 
	* @param @param sign_key
	* @param @param json
	* @param @return
	* @param @throws Exception    参数为传值会进行异常提示
	* @return String    返回类型 
	* @throws
	 */
	public static String encoder(String sign_key, String json) throws Exception {
		if (sign_key != null && sign_key != "") {
			if (sign_key.length() == 24) {
				if(json!=null && json!=""){
					return ThreeDES.encode(json, sign_key);
				}else{
					throw new Exception("Json Must not be empty");
				}
			}else{
				throw new Exception("Sign Key Length must be 24");
			}
		} else {
			throw new Exception("Sign Key Must not be empty");
		}
	}
	
	/**
	 * 对报文进行解密
	* @Title: decoder 
	* @Description: TODO(对报文进行解密) 
	* @param @param sign_key
	* @param @param json
	* @param @return
	* @param @throws Exception    参数为传值会进行异常提示
	* @return String    返回类型 
	* @throws
	 */
	public static String decoder(String sign_key, String json) throws Exception {
		if (sign_key != null && sign_key != "") {
			if (sign_key.length() == 24) {
				if(json!=null && json!=""){
					return ThreeDES.decode(json, sign_key);
				}else{
					throw new Exception("Json Must not be empty");
				}
			}else{
				throw new Exception("Sign Key Length must be 24");
			}
		} else {
			throw new Exception("Sign Key Must not be empty");
		}
	}
}