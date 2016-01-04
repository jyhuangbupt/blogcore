package com.henry.blog.util;

public class ErrorCode {

	/**
	 * 成功
	 */
	public static final int EC_ORDER_SUCCESS = 0;
	
	/**
	 * 异常
	 */
	public static final int EC_ORDER_EXCEPTION = 5000;
	
	/**
	 * 客户不存在
	 */
	public static final int EC_ORDER_NO_CUS = 5001;
	
	/**
	 * 不是会员
	 */
	public static final int EC_ORDER_NOT_MEM = 5002;
	
	/**
	 * 客户是黑名单
	 */
	public static final int EC_ORDER_IS_BLACK = 5003;
	
	/**
	 * 客户未评级
	 */
	public static final int EC_ORDER_NOT_RATE = 5004;
}
