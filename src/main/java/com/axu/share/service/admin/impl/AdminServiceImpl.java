package com.axu.share.service.admin.impl;

import com.axu.share.dao.RoleDao;
import com.axu.share.dao.UserDao;
import com.axu.share.dao.myshare.MyShareDao;
import com.axu.share.pojo.Article;
import com.axu.share.pojo.User;
import com.axu.share.service.CommentService;
import com.axu.share.service.admin.AdminService;
import com.axu.share.service.myshare.MyShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    MyShareDao myShareDao;

    @Autowired
    CommentService commentService;

    @Override
    public List<User> getUserData(User user) {

        List<User> userList = userDao.getUserData(user);
        if(userList.size() > 0){
            for(int i = 0; i <userList.size(); i ++){
                userList.get(i).setDate(DateFormat.getDateInstance().format(userList.get(i).getUserBeginTime()));//格式化时间
                userList.get(i).setUserRole(roleDao.selectUserRole(userList.get(i)));//获取用户权限名称


                userList.get(i).setArticleCount(String.valueOf(myShareDao.getUserArticleNumber(userList.get(i).getUserUuid())));//获取该用户的文章总数
                userList.get(i).setCommentCount(String.valueOf(commentService.getUserCommentCount(userList.get(i).getUserUuid())));//获取该用户的评论总数
            }
        }


        return userList;
    }

    @Override
    public List<Article> getArticles(Article article) {

        List<Article> articleList = new ArrayList<>();

        articleList = myShareDao.getArticles(article);

        for(int i = 0; i < articleList.size(); i ++){
            articleList.get(i).setDate(DateFormat.getDateInstance().format(articleList.get(i).getArticleBeginDate()));//格式化时间

        }

        return articleList;
    }
}
