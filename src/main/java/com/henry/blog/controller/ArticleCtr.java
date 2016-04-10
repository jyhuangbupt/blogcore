package com.henry.blog.controller;

import com.henry.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by henry on 2016/1/4.
 */
@Controller
public class ArticleCtr {
    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/addArticle", method = RequestMethod.POST)
    public @ResponseBody
    String addArticle(@RequestParam String dataJson,HttpServletResponse resp){
        String respStr = null;
        respStr = articleService.addArticle(dataJson);
        return respStr;
    }

    @RequestMapping(value = "/selectByCategory", method = RequestMethod.POST)
    public @ResponseBody
    String selectByCategory(@RequestParam String dataJson,HttpServletResponse resp){
        String respStr = null;
        respStr = articleService.selectByCategory(dataJson);
        return respStr;
    }

}
