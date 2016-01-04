package com.henry.blog.util;

import java.security.SecureRandom;

public class AlgorithmUtil {

	public static byte[] xor(byte[] a, byte[] b){
		int aLen = a.length;
		int bLen = b.length;
		int min = 0;
		int size = aLen > bLen ? aLen:bLen;
		byte[] c = new byte[size];
		
		if( size == aLen ){
			min = bLen;
			System.arraycopy(a, min, c, min, size-min);
		}else{
			min = aLen;
			System.arraycopy(b, min, c, min, size-min);
		}
		for (int i = 0; i < min; i++) {
			c[i] = (byte) (a[i] ^ b[i]);
		}
		return c;
	}
	
	public static byte[] generateRandom(int size) {
        byte[] iv = new byte[size];
        SecureRandom ivRandom = new SecureRandom();
        ivRandom.nextBytes(iv );
        return iv;
     }
	

	public static String genRandom(int size) {
		return DataUtil.parseByte2HexStr(generateRandom(size));
     }
}
