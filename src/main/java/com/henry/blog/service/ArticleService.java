package com.henry.blog.service;

/**
 * Created by henry on 2016/1/2.
 */
public interface ArticleService {
    String addArticle(String dataJson);
    String selectByCategory(String dataJson);
}
