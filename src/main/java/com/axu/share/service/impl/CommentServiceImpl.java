package com.axu.share.service.impl;

import com.axu.share.dao.CommentDao;
import com.axu.share.pojo.Article;
import com.axu.share.pojo.Comment;
import com.axu.share.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;

    @Override
    public List<Comment> getComent(Article article) {

        //获取评论数据
        List<Comment> commentsList = commentDao.getComment(article);

        return commentsList;
    }

    @Override
    public void deleteComment(Comment comment) {
        commentDao.deleteComment(comment);
    }

    /**
     * @Author Axu
     * @Description //TODO 获取用户的评论总数
     * @Date 17:37 2019/5/5
     * @Param [userUuid]
     * @return int
     **/
    @Override
    public int getUserCommentCount(String userUuid) {
        return commentDao.getUserCommentCount(userUuid);
    }
}
