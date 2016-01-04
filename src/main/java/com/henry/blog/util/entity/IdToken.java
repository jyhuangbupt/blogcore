package com.henry.blog.util.entity;

/**
 * @author Hoary
 * @since 2015/05/13
 *
 */
public class IdToken {
	/*
	 * 登录验证身份值
	 */
	private String token;
	
	/*
	 * 随机数内容
	 */
	private String random;
	
	/*
	 * 登录序列
	 */
	private String seq;
	
	private String oid;
	
	public IdToken() {
		
	}
	
	public IdToken(String token, String random) {
		this.token = token;
		this.random = random;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getSeq() {
		return seq;
	}
	

    public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public void setSeq(String seq) {
        this.seq = seq;
    }
}
