package com.henry.blog.util;

import com.henry.blog.util.entity.ScoreJson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * @author Hoary (hoary_huang@foxmail.com)
 * @since 2015/6/4
 */
public class DictionaryUtil {
	
	private static String staticFilename;
	
	private String fileName;
	private StringBuilder sb  = new StringBuilder("");
	
	private static boolean initialize = false;
	
	/**
	 * 获取一个实例
	 * @param fileName	存储json数据的文件路径
	 * @return
	 */
	public DictionaryUtil(String fileName){
		
		if( fileName == null || fileName.length() == 0){
			final String jsFile = "json.js";
			if( !initialize ){
				initialize = true;
				Properties prop = new Properties();
				FileInputStream in = null;
				try {
					String cp = Thread.currentThread().getContextClassLoader()  
			                .getResource("").getPath();
					
					in = new FileInputStream( cp + "conf/ext.properties" );
					prop.load( in );
					String str = prop.getProperty( "jasonfilename" ).trim();
					if( str == null || str.length() == 0){
						str = jsFile;
					}
					DictionaryUtil.staticFilename = str;
					this.fileName = DictionaryUtil.staticFilename;
				}catch (Exception e) {
					e.printStackTrace();
					this.fileName = jsFile;
				}finally{
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}
		}else{
			this.fileName = fileName;
		}
	}
	
	
	/**
	 * 
	 * @param list	存放SoreJson对象
	 * @param name
	 * @throws Exception
	 */
	//@SuppressWarnings("unchecked")
	public void data2Json( List<ScoreJson> list, String name ) throws Exception{
		int size = list.size();
		if( list == null ||  size == 0){
			throw new Exception("No Data");
		}
		sb.append("var "+name+" = [");
		
		ScoreJson sj = null;
		for (int i = 0; i < list.size(); i++) {
			sj = (ScoreJson) list.get(i);
			sb.append("{'name':'");
	        sb.append(sj.getIndex());
	        sb.append("','value':'").append(sj.getDesc());
	        String sc = sj.getScore();
	        if( sc !=null && sc.length() > 0){
	        	sb.append("','score':'").append(sc);
	        }
	        sb.append("','uuid':'").append(sj.getUuid()).append("'},");
		}
		int len = sb.length();
	    sb.replace(len-1, len, "];\n");
	}
	
	

	/**
	 * 存储Json数据到文件
	 * @throws Exception
	 */
	public void saveJson2File( ) throws Exception{
		
		FileOutputStream fos = new FileOutputStream(new File(fileName));
		fos.write(sb.toString().getBytes());
		fos.flush();
		fos.close();
		
	}
	
}
