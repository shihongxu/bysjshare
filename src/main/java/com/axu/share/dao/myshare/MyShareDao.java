package com.axu.share.dao.myshare;

import com.axu.share.pojo.Article;
import com.axu.share.pojo.Lable;
import com.axu.share.pojo.Photo;
import com.axu.share.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyShareDao {

    /**
     * @Author Axu
     * @Description //TODO 添加文章数据
     * @Date 9:57 2019/3/14
     * @Param [article]
     * @return void
     **/
    void addArticleData(Article article);
    
    /**
     * @Author Axu
     * @Description //TODO 添加文章标签
     * @Date 10:44 2019/3/14
     * @Param [lable]
     * @return void
     **/
    void addArticleLable(Lable lable);
    
    /**
     * @Author Axu
     * @Description //TODO 首页文章数据初始化
     * @Date 11:20 2019/3/27
     * @Param []
     * @return java.util.List<com.axu.share.pojo.article>
     **/
    List<Article> selectArticleData(Article article);

    /**
     * @Author Axu
     * @Description //TODO 根据文章uuid查询url
     * @Date 16:49 2019/3/27
     * @Param [articleUuid]
     * @return java.util.List<com.axu.share.pojo.article>
     **/
    List<Photo> selectPhotoUrl(String articleUuid);
    
    /**
     * @Author Axu
     * @Description //TODO 查询文章总数
     * @Date 17:04 2019/3/28
     * @Param []
     * @return java.lang.Integer
     **/
    Integer articleTotal();
    
    /**
     * @Author Axu
     * @Description //TODO 根据文章名搜索文章
     * @Date 14:26 2019/3/29
     * @Param [article]
     * @return java.util.List<com.axu.share.pojo.article>
     **/
    List<Article> selectArticleNameData(Article article);

    /**
     * @Author Axu
     * @Description //TODO 按条件查询文章总数
     * @Date 17:26 2019/3/29
     * @Param [article]
     * @return java.lang.Integer
     **/
    Integer selectArticleTotal(Article article);

    /**
     * @Author Axu
     * @Description //TODO 根据articleUuid 搜索文章
     * @Date 17:13 2019/3/30
     * @Param [articleUuid]
     * @return com.axu.share.pojo.article
     **/
    Article selectArticleUuid(String articleUuid);

    /**
     * @Author Axu
     * @Description //TODO 根据文章作者名称获取该作者发表的文章name
     * @Date 18:19 2019/3/30
     * @Param [article]
     * @return java.util.List<java.lang.String>
     **/
    List<Article> selectUserArticleName(Article article);
    
    /**
     * @Author Axu
     * @Description //TODO 获取最新的10条文章名字，uuid， 时间， 类型
     * @Date 11:14 2019/3/31
     * @Param []
     * @return java.util.List<com.axu.share.pojo.article>
     **/
    List<Article> selectNewArticle();
    
    
    /**
     * @Author Axu
     * @Description //TODO 根据用户uuid搜索该用户的所有文章（发布和草稿）
     * @Date 21:31 2019/4/1
     * @Param [user]
     * @return java.util.List<com.axu.share.pojo.article>
     **/
    List<Article> selectArticleByUserUuid(User user);
    
    /**
     * @Author Axu
     * @Description //TODO 根据用户uuid获取该用户的所有文章总数（发布和草稿）
     * @Date 22:18 2019/4/1
     * @Param [user]
     * @return java.lang.Integer
     **/
    Integer getUserArticleCount(User user);
    
    /**
     * @Author Axu
     * @Description //TODO 修改文章内容
     * @Date 19:05 2019/4/4
     * @Param [article]
     * @return void
     **/
    void updataArticle(Article article);
    
    /**
     * @Author Axu
     * @Description //TODO 删除文章 就是将articleFlag 置为 1
     * @Date 14:37 2019/4/6
     * @Param [article]
     * @return void
     **/
    void deleteArticle(Article article);

    /**
     * @Author Axu
     * @Description //TODO 获取全部文章的基本数据
     * @Date 19:41 2019/5/4
     * @Param [article]
     * @return java.util.List<com.axu.share.pojo.Article>
     **/
    List<Article> getArticles(Article article);

    /**
     * @Author Axu
     * @Description //TODO 获取全部文章总数
     * @Date 19:57 2019/5/4
     * @Param []
     * @return int
     **/
    int getArticleCount();

    /**
     * @Author Axu
     * @Description //TODO 获取用户的文章总数
     * @Date 17:32 2019/5/5
     * @Param [userUuid]
     * @return int
     **/
    int getUserArticleNumber(String userUuid);
}
