package com.henry.blog.dao;

import com.henry.blog.model.Article;

import java.util.List;

/**
 * Created by henry on 2016/1/2.
 */
public interface ArticleDao {
    int addArticle(Article article);
    int getLastedId();
    List<Article> selectByCategory(Integer category);
}
