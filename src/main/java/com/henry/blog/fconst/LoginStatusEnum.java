package com.henry.blog.fconst;

import org.apache.commons.lang.StringUtils;


/**
 * 登录状态枚举。
 *
 * @author haoyanmei(yanmeihao1@creditease.cn)
 * @since 2015-04-15
 */
public enum LoginStatusEnum {

    /**
     * 成功。
     */
    SUCCESS((int)0),

    /**
     * 密码错误。
     */
    ERR_PWD((int)1),
    
    /**
     * 用户名不存在。
     */
    ERR_NAME((int)2),
    
    /**
     * 非法操作
     */
    ILLEGAL((int)-1);
    

    /**
     * 数字形式。
     */
    private int number = -1;

    /**
     * 构造方法。
     * @param number 数字。
     */
    LoginStatusEnum(int number) {
        this.number = number;
    }

    /**
     * 获得与数字对应的枚举类型。
     * @param number 数字。
     * @return 对应的枚举类型。若无对应则返回null。
     */
    public static LoginStatusEnum fromNumber(int number) {
        for (LoginStatusEnum value : LoginStatusEnum.values()) {
            if (value.number == number) {
                return value;
            }
        }
        return null;
    }
    
    public static LoginStatusEnum fromNameStr(String name) {
    	if(!StringUtils.isEmpty(name)){
    		return LoginStatusEnum.valueOf(name.toUpperCase());
    	}
    	return null;
    }

    /**
     * 获得与当前枚举类型对应的数字。
     * @return 数字。
     */
    public int toNumber() {
        return number;
    }
}
