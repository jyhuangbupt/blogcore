package com.henry.blog.util;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author:      huhao
 * @date:        2014年9月10日上午10:59:11
 * @Description: jackson 工具类
 *
 */
public class JsonUtils {
	
	private final static ObjectMapper objectMapper=new ObjectMapper();
	
	
	public static ObjectMapper getObjectMapper(){
		return objectMapper;
	}
	
	/**
	 * @Description：根据json字符串和json节点名称获取json节点
	 * @author： huhao
	 * @Time  ：2014年9月10日上午11:08:07
	 * @return：JsonNode json节点
	 * @param：  @param json json字符串
	 * @param：  @param nodeName 节点名称
	 * @param：  @return
	 */
	public static JsonNode getNode(String json, String nodeName) {
        JsonNode node = null;
        try {
            node = JsonUtils.getObjectMapper().readTree(json);
            return node.get(nodeName);
        } catch (JsonProcessingException e) {
        	//TODO add log
        	
        } catch (IOException e) {
        	//TODO add log
        }
        return node;
    }
	/**
	 * @Description：
	 * @author： huhao
	 * @Time  ：2014年9月10日上午11:09:20
	 * @return：T
	 * @param：  @param jsonString
	 * @param：  @param tr
	 * @param：  @return
	 */
	 @SuppressWarnings("unchecked")
	public static <T> T json2GenericObject(String jsonString, TypeReference<T> tr) {
		 
	        if (jsonString == null || "".equals(jsonString)) {
	            return null;
	        } else {
	            try {
	                return (T) objectMapper.readValue(jsonString, tr);
	            } catch (Exception e) {
	            	//TODO add log
	            }
	        }
	        return null;
	    }
	/**
	 * @Description：JsonNode转换为Java泛型对象
	 * @author： huhao
	 * @Time  ：2014年9月10日上午11:09:33
	 * @return：T
	 * @param：  @param node
	 * @param：  @param tr
	 * @param：  @return
	 */
	 @SuppressWarnings("unchecked")
	public static <T> T jsonNode2GenericObject(JsonNode node, TypeReference<T> tr) {
		 
	        if (node == null || "".equals(node)) {
	            return null;
	        } else {
	            try {
	                return (T) objectMapper.readValue(node, tr);
	            } catch (Exception e) {
	            	//TODO add log
	            }
	        }
	        return null;
	    }
	 
	 public static String genericObjectToJsonStr(Object obj){
		 
		 try {
			return objectMapper.writeValueAsString(obj);
		} catch (IOException e) {
			//TODO add log
			e.printStackTrace();
		}
		 return null;
	 };
	 
	 public static <T> T json2ObjectByInputStread(InputStream in,TypeReference<T> tr) {
		 if(in == null) {
			 return null;
		 }
		 try {
			return (T) objectMapper.readValue(in, tr);
		} catch (IOException e) {
			//TODO add log
			e.printStackTrace();
		}
		 return null;
	 }
	 
}
