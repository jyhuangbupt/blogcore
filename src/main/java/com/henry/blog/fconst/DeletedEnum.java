package com.henry.blog.fconst;

import org.apache.commons.lang.StringUtils;


/**
 * 删除状态枚举。
 *
 * @author haoyanmei(yanmeihao1@creditease.cn)
 * @since 2015-04-15
 */
public enum DeletedEnum {

    /**
     * 正常。
     */
    NORMAL((int)0),

    /**
     * 回收站。
     */
    RECYCLE((int)1),
    
    /**
     * 已删除。
     */
    DELETED((int)2);

    /**
     * 数字形式。
     */
    private int number = -1;

    /**
     * 构造方法。
     * @param number 数字。
     */
    DeletedEnum(int number) {
        this.number = number;
    }

    /**
     * 获得与数字对应的枚举类型。
     * @param number 数字。
     * @return 对应的枚举类型。若无对应则返回null。
     */
    public static DeletedEnum fromNumber(int number) {
        for (DeletedEnum value : DeletedEnum.values()) {
            if (value.number == number) {
                return value;
            }
        }
        return null;
    }
    
    public static DeletedEnum fromNameStr(String name) {
    	if(!StringUtils.isEmpty(name)){
    		return DeletedEnum.valueOf(name.toUpperCase());
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
