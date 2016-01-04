package com.henry.blog.controller;

import com.henry.blog.util.JsonUtils;

import java.util.LinkedHashMap;


public abstract class AbstractController {

    /**
     * 生成响应内容。
     *
     * @param data    返回数据。
     * @param message 状态说明。
     * @param status  状态。
     * @return 响应内容。
     */
    protected String generateResponseContent(
            String data,
            String message,
            int status) {
        LinkedHashMap<String, String> content = new LinkedHashMap<String, String>();
        content.put("data", data);
        content.put("message", message);
        content.put("status", String.valueOf(status));
        return JsonUtils.genericObjectToJsonStr(content);
    }

    protected String generateResponseContentByObject(Object data, String msg, Integer status) {
        LinkedHashMap<String, Object> content = new LinkedHashMap<String, Object>();
        content.put("data", data);
        content.put("message", msg);
        content.put("status", String.valueOf(status));
        return JsonUtils.genericObjectToJsonStr(content);
    }

    protected String generateSignRespStr(Object data, String msg, Integer status, Object sign) {
        LinkedHashMap<String, Object> content = new LinkedHashMap<String, Object>();
        content.put("data", data);
        content.put("sign", sign);
        content.put("message", msg);
        content.put("status", String.valueOf(status));
        return JsonUtils.genericObjectToJsonStr(content);
    }
}


