package com.axu.share.pojo;

import java.util.Date;

public class User {

    private String userUuid;//user的uuid

    private String userName;//用户名

    private String userPassword;//用户密码

    private String userEmail;//用户邮箱

    private Date userBeginTime;//注册时间，系统生成

    private String userTel;//用户电话

    private String userSex;//性别

    private String userDescription;//个人说明

    private String userShiro;//权限等级

    private String hpName;//用户头像

    private String hpUuid;//头像的uuid

    private String userFlag;//用户信息状态标识

    private String userRole;//用户权限

    private String newUserPassword;//新密码

    private String date;//格式化后的时间

    private String articleCount;//该用户的文章总数

    private String commentCount;//该用户的评论总数

    public String getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(String articleCount) {
        this.articleCount = articleCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getNewUserPassword() {
        return newUserPassword;
    }

    public void setNewUserPassword(String newUserPassword) {
        this.newUserPassword = newUserPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserFlag() {
        return userFlag;
    }

    public void setUserFlag(String userFlag) {
        this.userFlag = userFlag;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Date getUserBeginTime() {
        return userBeginTime;
    }

    public String getUserTel() {
        return userTel;
    }

    public String getUserSex() {
        return userSex;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public String getUserShiro() {
        return userShiro;
    }

    public String getHpName() {
        return hpName;
    }

    public String getHpUuid() {
        return hpUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserBeginTime(Date userBeginTime) {
        this.userBeginTime = userBeginTime;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public void setUserShiro(String userShiro) {
        this.userShiro = userShiro;
    }

    public void setHpName(String hpName) {
        this.hpName = hpName;
    }

    public void setHpUuid(String hpUuid) {
        this.hpUuid = hpUuid;
    }
}
