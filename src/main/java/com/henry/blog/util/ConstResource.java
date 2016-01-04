package com.henry.blog.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConstResource {

	public static Map<String, String> keys = new HashMap<String, String>();
	public static final String URL_KEY = "random1";
	
	static{
		try {
			Properties prop = new Properties();
			InputStream in = ConstResource.class.getClassLoader()
			.getResourceAsStream( "conf/ext.properties" );
			
			prop.load( in );
			String str = prop.getProperty( URL_KEY ).trim().toUpperCase();
			keys.put(URL_KEY, str);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}
}
