package com.axu.share.pojo;

import java.util.Date;
import java.util.List;

/**
 * @Author Axu
 * @Description //TODO 文章
 * @Date 22:26 2019/3/13
 * @Param 
 * @return 
 **/
public class Article {
    
    private String articleUuid;//文章uuid

    private String userUuid;//文章作者uuid

    private String userName;//文章作者

    private String articleName;//文章名称

    private String articleContents;//文章内容

    private String lableName;//文章标签

    private String articleType;//文章类型

    private Date articleBeginDate;//文章创建时间

    private String articlePublish;//文章是否发布标记

    private String commentNumber;//文章评论次数

    private String viewsNumber;//文章略读次数

    private List<String> photoUrl;//文章图片地址

    private String articleFlag;//文章删除标识

    private String[] photoUrl2;//文章图片地址url

    private Integer total;//总数

    private String date;//文章发布时间（格式化后）

    public Integer getTotal() {
        return total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    private int page;//页码

    private int size;//每页条数

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String[] getPhotoUrl2() {
        return photoUrl2;
    }

    public void setPhotoUrl2(String[] photoUrl2) {
        this.photoUrl2 = photoUrl2;
    }

    public String getArticleFlag() {
        return articleFlag;
    }

    public void setArticleFlag(String articleFlag) {
        this.articleFlag = articleFlag;
    }

    public List<String> getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(List<String> photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getArticleUuid() {
        return articleUuid;
    }

    public void setArticleUuid(String articleUuid) {
        this.articleUuid = articleUuid;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleContents() {
        return articleContents;
    }

    public void setArticleContents(String articleContents) {
        this.articleContents = articleContents;
    }

    public String getLableName() {
        return lableName;
    }

    public void setLableName(String lableName) {
        this.lableName = lableName;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public Date getArticleBeginDate() {
        return articleBeginDate;
    }

    public void setArticleBeginDate(Date articleBeginDate) {
        this.articleBeginDate = articleBeginDate;
    }

    public String getArticlePublish() {
        return articlePublish;
    }

    public void setArticlePublish(String articlePublish) {
        this.articlePublish = articlePublish;
    }

    public String getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(String commentNumber) {
        this.commentNumber = commentNumber;
    }

    public String getViewsNumber() {
        return viewsNumber;
    }

    public void setViewsNumber(String viewsNumber) {
        this.viewsNumber = viewsNumber;
    }
}
