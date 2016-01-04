package com.henry.blog.model;

/**
 * Created by henry on 2016/1/2.
 */
public class Article {
    private Integer id;
    private String uuid;
    private long createTime;
    private Integer comments;
    private Integer category;
    private String title;
    private String content;
    private String abstracts;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", createTime=" + createTime +
                ", comments=" + comments +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", abstracts='" + abstracts + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }
}
