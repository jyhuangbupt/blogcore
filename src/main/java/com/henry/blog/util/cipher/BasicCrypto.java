package com.henry.blog.util.cipher;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;


/**
 * @author Hoary (hoary_huang@foxmail.com)
 * @since 2015/4/20
 */
public class BasicCrypto {
	
	private static final String BC = "BC";
	
	static{
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());   
	}
	
	public enum KeyOpt{
		AES,
		DES,
		DESede,
		;
	}

	public enum DigestOpt{
		SHA1("SHA1"),
		SHA224("SHA-224"),
		SHA256("SHA-256"),
		SHA384("SHA-384"),
		SHA512("SHA1-512"),
		;
		
		private String alg;
		DigestOpt( String alg ){
			this.alg = alg;
		}
		public String getAlg(){
			return this.alg;
		}
	} 
	
	public enum SymmetricOpt{
		
		AES_CBC_NO("AES","AES/CBC/NoPadding",false),
		AES_CBC_PKCS5("AES","AES/CBC/PKCS5Padding",true),
		AES_ECB_NO("AES","AES/ECB/NoPadding",false),
		AES_ECB_PKCS5("AES","AES/ECB/PKCS5Padding",true),
		
		DES_CBC_NO("DES","DES/CBC/NoPadding",false),
		DES_CBC_PKCS5("DES","DES/CBC/PKCS5Padding",true),
		DES_ECB_NO("DES","DES/ECB/NoPadding",false),
		DES_ECB_PKCS5("DES","DES/ECB/PKCS5Padding",true),
		
		DESede_CBC_NO("DESede","DESede/CBC/NoPadding",false),
		DESede_CBC_PKCS5("DESede","DESede/CBC/PKCS5Padding",true),
		DESede_ECB_NO("DESede","DESede/ECB/NoPadding",false),
		DESede_ECB_PKCS5("DESede","DESede/ECB/PKCS5Padding",true),
		
//	    BLOWFISH("DESEDE"),
//	    TWOFISH("DESEDE"),
	    ;
		private String alg;
		private String padding;
		private boolean needIv;
		SymmetricOpt( String alg, String padding, boolean needIv ) {
			this.alg = alg;
			this.padding = padding;
			this.needIv = needIv;
		}
		public String getAlg() {
			return alg;
		}
		public String getPadding() {
			return padding;
		}
		public boolean isNeedIv() {
			return needIv;
		}
	}
	
	public enum AsymmetricOpt{
		
	}
	
	public enum DefaultIV{
		DFIV8("5@BjTAM!"),
		DFIV16("87655@BjTAM!1234"),
		;
		private String defIv;
		DefaultIV( String defIv ) {
			this.defIv = defIv;
		}
		public String getDefIv() {
			return defIv;
		}
		
	}
	
	public static byte[] genIv( int ivLength ){
		try{
			byte[] iv = new byte[ivLength];
			SecureRandom ivRandom = new SecureRandom();
			ivRandom.nextBytes( iv );
			return iv;
		}catch (Exception e) {
			//TODO 
		}
		
		return DefaultIV.DFIV8.getDefIv().getBytes();
	}
	
	public static byte[] genKey( KeyOpt ko ){
		//KeyGenerator提供对称密钥生成器的功能，支持各种算法          
		KeyGenerator keygen;
		SecretKey deskey = null;
		try {
			keygen = KeyGenerator.getInstance( ko.toString() );
			deskey = keygen.generateKey(); 
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//SecretKey负责保存对称密钥           
		return deskey.getEncoded();
	}

	public static byte[] makeDigest( byte[] msg, DigestOpt alg ){
		MessageDigest dig = null;
		byte[] digest = null;
		try {
			dig = MessageDigest.getInstance( alg.getAlg() );
			digest = dig.digest( msg );
		} catch (NoSuchAlgorithmException e) {
			//TODO log something
			e.printStackTrace();
			return null;
		}
		return digest;
	}
	
	public static byte[] encrypt(byte[] key, byte[] msg, byte[] iv, SymmetricOpt sep){
		return innerDo( key, msg, iv, sep, CipherMode.ENCRYPT_MODE );
	}
	
	public static byte[] decrypt(byte[] key, byte[] msg, byte[] iv, SymmetricOpt sep){
		return innerDo( key, msg, iv, sep, CipherMode.DECRYPT_MODE );
	}
	
	
	private enum CipherMode{
		ENCRYPT_MODE(Cipher.ENCRYPT_MODE),
		DECRYPT_MODE(Cipher.DECRYPT_MODE),
		;
		
		private int mode;
		private CipherMode( int mode ) {
			this.mode = mode;
		}
		public int getMode() {
			return mode;
		}
		
	}
	
	private static byte[] innerDo(byte[] key, byte[] msg, byte[] iv, SymmetricOpt sep,CipherMode cm ){
		int mode = cm.getMode();
		boolean needIv = sep.isNeedIv();
		SecretKey deskey = null;
        
		deskey = new SecretKeySpec(key, sep.getAlg() );
		Cipher c1 = null;
		byte[] result = null;
		try {
			c1 = Cipher.getInstance( sep.getPadding(), BC );
			if( needIv ){
				IvParameterSpec param = null;
				param = new IvParameterSpec( iv );
				c1.init(mode, deskey, param);
			}else{
				c1.init(mode, deskey);
			}
			result = c1.doFinal( msg );
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			//TODO log something
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
