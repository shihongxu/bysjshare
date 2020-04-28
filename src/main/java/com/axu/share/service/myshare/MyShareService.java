package com.axu.share.service.myshare;

import com.axu.share.pojo.Article;
import com.axu.share.pojo.Lable;
import com.axu.share.pojo.Photo;
import com.axu.share.pojo.User;

import java.util.List;

/**
 * @Author Axu
 * @Description //TODO
 * @Date 9:32 2019/3/14
 * @Param
 * @return
 **/
public interface MyShareService {

    /**
     * @Author Axu
     * @Description //TODO添加文章数据
     * @Date 9:32 2019/3/14
     * @Param [artcile]
     * @return void
     **/
    void addArtcileData(Article artcile, Lable lable);

    /**
     * @Author Axu
     * @Description //TODO 首页文章数据初始化
     * @Date 11:18 2019/3/27
     * @Param []
     * @return java.util.List<com.axu.share.pojo.article>
     **/
    List<Article> selectArticleData(Article article);
    
    /**
     * @Author Axu
     * @Description //TODO 查询文章总数
     * @Date 16:59 2019/3/28
     * @Param []
     * @return int
     **/
    Integer articleTotal();
    
    /**
     * @Author Axu
     * @Description //TODO 根据articleUuidd 搜索文章
     * @Date 17:18 2019/3/30
     * @Param [articleUuid]
     * @return com.axu.share.pojo.article
     **/
    Article selectArticleUuid(String articleUuid);
    
    /**
     * @Author Axu
     * @Description //TODO 根据文章作者名称获取该作者发表的文章name
     * @Date 18:20 2019/3/30
     * @Param [article]
     * @return java.util.List<java.lang.String>
     **/
    List<Article> selectUserArticleName(Article article);
    
    /**
     * @Author Axu
     * @Description //TODO 获取文章图片地址
     * @Date 22:03 2019/3/30
     * @Param [article]
     * @return java.util.List<com.axu.share.pojo.Photo>
     **/
    List<Photo> selectArtcielUrl(Article article);
    
    /**
     * @Author Axu
     * @Description //TODO 获取最新的10条文章数据 文章名 时间 类型 uuid
     * @Date 11:23 2019/3/31
     * @Param []
     * @return java.util.List<com.axu.share.pojo.article>
     **/
    List<Article> selectNewArticle();
    
    
    /**
     * @Author Axu
     * @Description //TODO 根据用户uuid搜索该用户的所有文章
     * @Date 21:32 2019/4/1
     * @Param [user]
     * @return java.util.List<com.axu.share.pojo.article>
     **/
    List<Article> selectArticleByUserUuid(User user);
    
    /**
     * @Author Axu
     * @Description //TODO 根据用户获取该用户的所有文章总数
     * @Date 22:19 2019/4/1
     * @Param [user]
     * @return java.lang.Integer
     **/
    Integer getUserArticleCount(User user);
    
    /**
     * @Author Axu
     * @Description //TODO 修改文章内容
     * @Date 19:06 2019/4/4
     * @Param [article]
     * @return void
     **/
    void updataArticle(Article article);
    
    /**
     * @Author Axu
     * @Description //TODO 删除文章  就是将articleFlag 置为 0
     * @Date 14:38 2019/4/6
     * @Param [article]
     * @return void
     **/
    void deleteArticle(Article article);
    
    /**
     * @Author Axu
     * @Description //TODO 获取全部 
     * @Date 20:41 2019/5/4
     * @Param []
     * @return int
     **/
    int getArticleCount();
    
    /**
     * @Author Axu
     * @Description //TODO 获取用户的文章总数
     * @Date 17:35 2019/5/5
     * @Param [userUuid]
     * @return int
     **/
    int getUserArticleNumber(String userUuid);
}
