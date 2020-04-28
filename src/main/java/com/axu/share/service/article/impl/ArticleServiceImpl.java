package com.axu.share.service.article.impl;

import com.axu.share.dao.CommentDao;
import com.axu.share.pojo.Comment;
import com.axu.share.pojo.User;
import com.axu.share.service.UserService;
import com.axu.share.service.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Author Axu
 * @Description //TODO 文章service
 * @Date 11:50 2019/4/17
 * @Param 
 * @return 
 **/
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    CommentDao commentDao;

    @Autowired
    UserService userService;

    /**
     * @Author Axu
     * @Description //TODO 添加评论数据
     * @Date 10:27 2019/4/18
     * @Param [comment]
     * @return void
     **/
    @Override
    public void insertComment(Comment comment) {

        User userToken = userService.getTokenUser();//获取当前登录用户
        comment.setUserName(userToken.getUserName());
        comment.setUserUuid(userToken.getUserUuid());
        comment.setCommentUuid(UUID.randomUUID().toString());//添加commentUuid
        comment.setScbs("0");//添加删除标识为0 0标识不删除 1表示删除


        commentDao.insertComment(comment);
    }


}
