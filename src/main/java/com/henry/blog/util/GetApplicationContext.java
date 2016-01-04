package com.henry.blog.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 得到ApplicationContext
 * @author haoyanmei(yanmeihao1@creditease.cn)
 * @since 2015-04-13
 */
public class GetApplicationContext {
	
	public static ApplicationContext getContext() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
                ,"classpath:conf/spring-mybatis.xml"});
		return context;
	}
}
