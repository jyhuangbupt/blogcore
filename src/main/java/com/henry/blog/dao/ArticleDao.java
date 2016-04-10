package com.henry.blog.dao;

import com.henry.blog.model.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by henry on 2016/1/2.
 */
public interface ArticleDao {
    int addArticle(Article article);
    Integer getLastedId();

    /**
     * 通过类别查找博客
     * @param category
     * @return
     */
    List<Article> selectByCategory(@Param("category") Integer category);

}
