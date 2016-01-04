package com.henry.blog.util;

import com.henry.blog.util.cipher.BasicCrypto;
import com.henry.blog.util.cipher.BasicCrypto.SymmetricOpt;

import java.util.Arrays;

public class DataUtil {

	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
	
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex);
		}
		return sb.toString();
	}
	
	static final int DIGEST_LEN = 20;
	/**
	 * @param url 待加密的URL
	 * @return 加密后的URL，Hex格式的。若返回null，说明加密过程出现问题
	 */
	public static String encUrl( String url ){
		try{
			byte[] key = DataUtil.parseHexStr2Byte(ConstResource.keys.get(ConstResource.URL_KEY));
			String ivStr = BasicCrypto.DefaultIV.DFIV16.getDefIv();
			byte[] iv = ivStr.getBytes();
			byte[] urlBytes = url.getBytes();
			int length = urlBytes.length;
			byte[] digest = BasicCrypto.makeDigest(urlBytes, BasicCrypto.DigestOpt.SHA1);
			byte[] sig = new byte[length + DIGEST_LEN];
			System.arraycopy(urlBytes, 0, sig, 0, length);
			System.arraycopy(digest, 0, sig, length, DIGEST_LEN);
			byte[] encRes = BasicCrypto.encrypt(key, sig, iv, SymmetricOpt.AES_CBC_PKCS5);
			return parseByte2HexStr(encRes);
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	
	/**
	 * @param encUrl 解密URL得到随机数和时间值
	 * @return 返回解密后的结果。若返回null，则可能URL被篡改过
	 */
	public static String decUrl( String encUrl ){
		try{
			byte[] key = DataUtil.parseHexStr2Byte(ConstResource.keys.get(ConstResource.URL_KEY));
			String ivStr = BasicCrypto.DefaultIV.DFIV16.getDefIv();
			byte[] iv = ivStr.getBytes();
			byte[] enc = parseHexStr2Byte(encUrl);
			byte[] sig = BasicCrypto.decrypt(key, enc, iv, SymmetricOpt.AES_CBC_PKCS5);
			int len = sig.length - DIGEST_LEN;
			byte[] urlBytes = new byte[ len ];
			byte[] remoteDig = new byte[ DIGEST_LEN ];
			System.arraycopy(sig, 0, urlBytes, 0, len);
			System.arraycopy(sig, len, remoteDig, 0, DIGEST_LEN);
			byte[] digest = BasicCrypto.makeDigest(urlBytes, BasicCrypto.DigestOpt.SHA1);
			if( Arrays.equals(digest, remoteDig)){
				return new String(urlBytes);
			}
			System.out.println("Why null");
		}catch (Exception e) {
			System.out.println("解密发生异常");
			//e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
}
