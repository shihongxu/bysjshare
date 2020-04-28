package com.axu.share.dao;

import com.axu.share.pojo.Article;
import com.axu.share.pojo.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Axu
 * @Description //TODO 评论表的dao
 * @Date 10:03 2019/4/18
 * @Param 
 * @return 
 **/
@Repository
public interface CommentDao {

    /**
     * @Author Axu
     * @Description //TODO 添加评论
     * @Date 10:05 2019/4/18
     * @Param [comment]
     * @return void
     **/
    void insertComment(Comment comment);
    
    /**
     * @Author Axu
     * @Description //TODO 获取文章评论数据
     * @Date 17:18 2019/4/23
     * @Param [article]
     * @return com.axu.share.pojo.Comment
     **/
    List<Comment> getComment(Article article);


    /**
     * @Author Axu
     * @Description //TODO 删除文章评论
     * @Date 18:18 2019/4/24
     * @Param [comment]
     * @return void
     **/
    void deleteComment(Comment comment);

    /**
     * @Author Axu
     * @Description //TODO 获取用户的评论总数
     * @Date 17:32 2019/5/5
     * @Param [userUuid]
     * @return int
     **/
    int getUserCommentCount(String userUuid);
    
}
