package com.axu.share.pojo;

import java.util.Date;

/**
 * @Author Axu
 * @Description //TODO 评论表pojo
 * @Date 23:02 2019/3/13
 * @Param
 * @return
 **/
public class Comment {

    private String commentUuid;//评论表uuid

    private String articleUuid;//评论文章uuid

    private String articleUserName;//文章作者name

    private String articleUserUuid;//文章作者uuid

    private String articleName;//评论文章name

    private String userUuid;//评论者uuid

    private String userName;//评论人name

    private String commentUserUuid;//被评论的人uuid

    private String commentUserName;//被评论人name

    private String commentContents;//评论内容

    private Date commentDate;//评论时间

    private String scbs;//删除标识

    public String getScbs() {
        return scbs;
    }

    public void setScbs(String scbs) {
        this.scbs = scbs;
    }

    public String getArticleUserName() {
        return articleUserName;
    }

    public void setArticleUserName(String articleUserName) {
        this.articleUserName = articleUserName;
    }

    public String getArticleUserUuid() {
        return articleUserUuid;
    }

    public void setArticleUserUuid(String articleUserUuid) {
        this.articleUserUuid = articleUserUuid;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentUserName() {
        return commentUserName;
    }

    public void setCommentUserName(String commentUserName) {
        this.commentUserName = commentUserName;
    }

    public String getCommentUserUuid() {
        return commentUserUuid;
    }

    public void setCommentUserUuid(String commentUserUuid) {
        this.commentUserUuid = commentUserUuid;
    }

    public String getCommentUuid() {
        return commentUuid;
    }

    public void setCommentUuid(String commentUuid) {
        this.commentUuid = commentUuid;
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

    public String getCommentContents() {
        return commentContents;
    }

    public void setCommentContents(String commentContents) {
        this.commentContents = commentContents;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
}
