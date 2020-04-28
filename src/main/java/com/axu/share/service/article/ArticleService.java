package com.axu.share.service.article;

import com.axu.share.pojo.Comment;

public interface ArticleService {

    /**
     * @Author Axu
     * @Description //TODO 添加评论
     * @Date 10:27 2019/4/18
     * @Param [comment]
     * @return void
     **/
    void insertComment(Comment comment);
}
