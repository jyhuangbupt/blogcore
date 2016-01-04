package com.henry.blog.fconst;

import org.apache.commons.lang.StringUtils;


/**
 * 修改密码的枚举。
 *
 * @author haolin (linhao@creditease.cn)
 * @since 2014-07-18
 */
public enum UpdatePwdStatusEnum {

    /**
     * 成功。
     */
    SUCCESS((int)0),

    /**
     * 旧密码错误。
     */
    ERR_OLDPWD((int)1),
    
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
    UpdatePwdStatusEnum(int number) {
        this.number = number;
    }

    /**
     * 获得与数字对应的枚举类型。
     * @param number 数字。
     * @return 对应的枚举类型。若无对应则返回null。
     */
    public static UpdatePwdStatusEnum fromNumber(int number) {
        for (UpdatePwdStatusEnum value : UpdatePwdStatusEnum.values()) {
            if (value.number == number) {
                return value;
            }
        }
        return null;
    }
    
    public static UpdatePwdStatusEnum fromNameStr(String name) {
    	if(!StringUtils.isEmpty(name)){
    		return UpdatePwdStatusEnum.valueOf(name.toUpperCase());
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
