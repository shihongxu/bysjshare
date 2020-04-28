package com.axu.share.service;

import com.axu.share.pojo.Article;
import com.axu.share.pojo.Comment;

import java.util.List;

public interface CommentService {

    //获取评论数据
    List<Comment> getComent(Article article);
    
    /**
     * @Author Axu
     * @Description //TODO 删除文章评论
     * @Date 18:20 2019/4/24
     * @Param [comment]
     * @return void
     **/
    void deleteComment(Comment comment);
    
    /**
     * @Author Axu
     * @Description //TODO 获取用的评论总数
     * @Date 17:36 2019/5/5
     * @Param [userUuid]
     * @return int
     **/
    int getUserCommentCount(String userUuid);
}
