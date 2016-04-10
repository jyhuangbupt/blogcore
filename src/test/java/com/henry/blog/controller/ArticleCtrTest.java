package com.henry.blog.controller;

import com.henry.blog.model.Article;
import com.henry.blog.util.JsonUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by henry on 2016/1/2.
 */
public class ArticleCtrTest {
    private Article article;
    private Map<String, String> map;
    private String url;

    @Before
    public void init() {
        article = new Article();
        map = new LinkedHashMap<String, String>();
        url = "http://localhost:8080/blog/";
    }

    @Test
    public void testAddArticle() throws UnirestException {
        map.put("title","后台上线，数据库连接完成");
        map.put("content","");
        map.put("brief","数据库连接完成，现在所看到的博文都是取自后台数据库，虽然还很简单^_^");
        map.put("category","4");
        HttpResponse<String> response = Unirest.post(url + "addArticle")
                .field("dataJson", JsonUtils.genericObjectToJsonStr(map))
                .asString();
        System.out.println(response.getBody());
    }

    @Test
    public void testSelectByCategory() throws UnirestException {
        map.put("category","1");
        HttpResponse<String> response = Unirest.post(url + "selectByCategory")
                .field("dataJson", JsonUtils.genericObjectToJsonStr(map))
                .asString();
        System.out.println(response.getBody());
    }
}
