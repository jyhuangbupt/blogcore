package com.henry.blog.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author Hoary 2015/4/14
 *
 */
public class Crypto {

	public static final String ENCODE = "UTF8";
	
	public static final String SHA256 = "SHA-256";
	
	public static byte[] makeDigest( byte[] msg, String alg ){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance( alg );
			md.update( msg );
		} catch (NoSuchAlgorithmException e) {
			//TODO log something
			e.printStackTrace();
			return null;
		}
		return md.digest();
	}	
	
	public static String makeDigest(String msgStr[], int parts,String alg){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance( alg );
			for (int i = 0; i < parts; i++) {
				byte[] msg = msgStr[i].getBytes();
				md.update( msg );
			}
		} catch (NoSuchAlgorithmException e) {
			//TODO log something
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return DataUtil.parseByte2HexStr(md.digest());
	}
	
	public static String sha256Digest( String msg ){
		byte[] byteMsg = null;
		try {
			byteMsg = msg.getBytes( ENCODE );
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			//TODO log something
			return null;
		}
		byte[] dig = makeDigest( byteMsg, SHA256 );
		
		return Base64.encode(dig);
	}
	
	public static void main(String[] args) {
		String passwd = "hym";
		System.out.println(sha256Digest(passwd));
	}
}
