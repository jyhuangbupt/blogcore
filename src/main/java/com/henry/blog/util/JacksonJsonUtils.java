package com.henry.blog.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;


/**
 * Json工具类.
 *
 * @author Laughing_Vzr
 * @since 2014-11-14
 */
public class JacksonJsonUtils {

    /**
     * 过滤JSON字符串中值为NULL字段和空字符串的字段.
     *
     * @return objectMapper
     */
    public static ObjectMapper toJsonNotNullAndEmpty() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return objectMapper;
    }

    public static ObjectMapper toObjectIgnoreUnknowColumn() {
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    /**
     * 过滤JSON字符串中值为NULL的字段.
     *
     * @return objectMapper JSON映射对象
     */
    public static ObjectMapper toJsonNotNull() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }

    /**
     * 过滤JSON字符串中值为空字符串的字段.
     *
     * @return objectMapper JSON映射对象
     */
    public static ObjectMapper toJsonNotEmpty() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return objectMapper;
    }

    /**
     * 获得一般映射对象，不对字符串进行任何过滤.
     */
    public static ObjectMapper toNormalJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper;
    }

    /**
     * 生成最外层JSON.
     *
     * @param key   键名
     * @param value 值
     * @return jsonStr
     * @throws JsonGenerationException Json产生异常
     * @throws JsonMappingException    Json映射异常
     * @throws IOException             I/O异常
     */
    public static String gernerateSimpleJsonNode(String key, String value)
            throws JsonGenerationException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.put(key, value);
        String jsonStr = objectMapper.writeValueAsString(rootNode);
        return jsonStr;

    }


    /**
     * 获取某个节点的JsonNode对象(只支持第一层级即最外层级,NULL字段将不会被解析).
     *
     * @param jsonStr  要解析的JSON串
     * @param nodeName 所要获取节点的名称
     * @return jsonNode
     * @throws JsonProcessingException JSON异常
     * @throws IOException             I/O异常
     */
    public static JsonNode getNodeByName(String jsonStr, String nodeName)
            throws JsonProcessingException, IOException {
        return toJsonNotNull().readTree(jsonStr).get(nodeName);
    }

    /**
     * 判断Json串中是否有某个节点(只针对JSON串下的第一层级即最外层级).
     *
     * @param jsonStr 所要解析的JSON串
     * @param key     节点名称
     * @return boolean
     * @throws IOException             I/O异常
     * @throws JsonProcessingException JSON异常
     */
    public static Boolean contain(String jsonStr, String key)
            throws JsonProcessingException, IOException {
        return toJsonNotNull().readTree(jsonStr).has(key);
    }

    /**
     * 获取某个数组节点的长度(只针对所要解析JSON串下的第一层级即最外层级).
     *
     * @param jsonStr 所要解析的JSON串
     * @param key     数组节点名称
     * @return size 数组节点长度
     * @throws IOException             I/O异常
     * @throws JsonProcessingException JSON异常
     */
    public static Integer getNodeSize(String jsonStr, String key) throws JsonProcessingException, IOException {
        Integer size = 0;
        // 判断是否有该节点
        if (contain(jsonStr, key)) {
            size = toJsonNotNull().readTree(jsonStr).get(key).size();
        } else {
            size = -1;
        }
        return size;
    }

    /**
     * 更改某节点的值(只支持第一层级,不过滤NULL和空字符串).
     *
     * @param jsonStr 所要解析的JSON串
     * @param key     所要更改的键名
     * @param value   所要更改的值
     * @return result 更改后的JSON结果
     * @throws IOException I/O异常
     */
    public static String setValueByKey(String jsonStr, String key, String value) throws IOException {
        String result;
        ObjectNode objectNode = (ObjectNode) toNormalJson().readTree(jsonStr);
        objectNode.put(key, value);
        result = toNormalJson().writeValueAsString(objectNode);
        return result;
    }

    /**
     * 忽略未知字段反序列化.
     *
     * @param json  String
     * @param clazz Class
     * @return T
     * @throws IOException
     */
    public static <T> T DeserializationObjectIgnoreUnkown(String json, Class clazz) throws IOException {
        return (T) JacksonJsonUtils.toObjectIgnoreUnknowColumn().
                setSerializationInclusion(JsonInclude.Include.NON_NULL).readValue(json, clazz);
    }
}