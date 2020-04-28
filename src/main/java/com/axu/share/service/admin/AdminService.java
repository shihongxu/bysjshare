package com.axu.share.service.admin;

import com.axu.share.pojo.Article;
import com.axu.share.pojo.User;

import java.util.List;

public interface AdminService {

    //获取用户数据
    List<User> getUserData(User user);

    //获取文章基本数据
    List<Article> getArticles(Article article);
}
