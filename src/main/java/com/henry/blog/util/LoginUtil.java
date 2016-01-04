package com.henry.blog.util;

import com.henry.blog.util.entity.IdToken;

/**
 * @author Hoary (hoary_huang@foxmail.com)
 */
public class LoginUtil {
	
	/**
	 * @param passwdPlain	口令原文
	 * @return	登录序列号
	 */
	public static String genLoginSequence(String passwdPlain ){
		int size = passwdPlain.length();
		byte[] random = AlgorithmUtil.generateRandom(size);
		byte[] result = AlgorithmUtil.xor(passwdPlain.getBytes(), random);
		byte[] dig = Crypto.makeDigest(result, Crypto.SHA256);
		return DataUtil.parseByte2HexStr(dig);
	}
	
	/**
	 * @param encPwd	数据库中存储的口令密文
	 * @param ip		IP 目前暂时为null
	 * @return			返回登录token值和产生token的随机数
	 */
	public static IdToken genLoginToken(String encPwd, String ip){
		return genLoginToken(encPwd,ip,AlgorithmUtil.genRandom(16));
	}
	
	/**
	 * @param token		登录时产生的token
	 * @param encPwd	数据库中存储的口令密文
	 * @param ip		P 目前暂时为null
	 * @return			验证结果
	 */
	public static boolean valifyToken( IdToken token, String encPwd, String ip){
		IdToken genToken = genLoginToken(encPwd,ip,token.getRandom());
		if( genToken.getToken().equals(token.getToken())){
			return true;
		}
		
		return false;
	}
	
	private static IdToken genLoginToken(String encPwd, String ip, String rdStr ){
		int count = 2;
		if( ip != null && ip.length() > 0 ){
			count++;
		}
		String factor[] = new String[ count ];
		factor[0] = encPwd;
		factor[1] = rdStr;
		if( count == 3 ){
			factor[2] = ip;
		}
		IdToken token = new IdToken();
		token.setToken(Crypto.makeDigest(factor, count, Crypto.SHA256));
		token.setRandom(rdStr);
		
		return token;
	}
	
	
}
