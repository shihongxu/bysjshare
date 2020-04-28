package com.axu.share.service.myshare.impl;

import com.axu.share.dao.myshare.MyShareDao;
import com.axu.share.pojo.Article;
import com.axu.share.pojo.Lable;
import com.axu.share.pojo.Photo;
import com.axu.share.pojo.User;
import com.axu.share.service.UserService;
import com.axu.share.service.myshare.MyShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.UUID;

/**
 * @Author Axu
 * @Description //TODO 
 * @Date 9:33 2019/3/14
 * @Param 
 * @return 
 **/
@Service
public class MyShareServiceImpl implements MyShareService
{

    @Autowired
    UserService userService;

    @Autowired
    MyShareDao myShareDao;

    
    /**
     * @Author Axu
     * @Description //TODO 添加文章数据
     * @Date 9:57 2019/3/14
     * @Param [artcile:文章内容, Lable:文章标签]
     * @return void
     **/
    @Override
    public void addArtcileData(Article article, Lable lable) {

        article.setViewsNumber("1");
        article.setCommentNumber("0");

        myShareDao.addArticleData(article);//添加文章数据
        if(lable.getArticleUuid() != null){
            myShareDao.addArticleLable(lable);//添加文章标签数据
        }
    }

    /**
     * @Author Axu
     * @Description //TODO 首页文章数据初始化
     * @Date 11:19 2019/3/27
     * @Param []
     * @return java.util.List<com.axu.share.pojo.article>
     **/
    @Override
    public List<Article> selectArticleData(Article article) {

        if (article.getArticleName().equals("")){
            article.setArticleName(null);
        }

        List<Article> articleList = myShareDao.selectArticleData(article);

        if(articleList.size() > 0){

            if(article.getArticleName() == null && article.getArticleType() == null){
                //设置文章总数
                articleList.get(0).setTotal(myShareDao.articleTotal());
            }if(article.getArticleType() != null || article.getArticleName() != null){
                    articleList.get(0).setTotal(myShareDao.selectArticleTotal(article));
            }


            for(int i = 0; i < articleList.size(); i ++){
                //设置文章图片url
                String articleUuid = articleList.get(i).getArticleUuid();
                List<Photo> photoList = myShareDao.selectPhotoUrl(articleUuid);
                if(photoList.size() > 0){
                    String[] photoUrl = new String[photoList.size()];
                    for(int j = 0; j < photoList.size(); j ++){
                        photoUrl[j] = photoList.get(j).getPhotoUrl();
                    }
                    articleList.get(i).setPhotoUrl2(photoUrl);
                    System.out.println("===============:" + photoList);
                }

                //将阅读数加1，回写到数据库
                int viewsNumber = Integer.parseInt(articleList.get(i).getViewsNumber());
                viewsNumber = viewsNumber + 1;
                articleList.get(i).setViewsNumber(String.valueOf(viewsNumber));
                updataArticle(articleList.get(i));

                //设置文章长度
                String articleContents = articleList.get(i).getArticleContents();
                int articleLength = articleContents.length();
                if (articleLength > 250){
                    articleList.get(i).setArticleContents(articleContents.substring(0,250)+"...");
                }
            }
        }

        return articleList;
    }



    /**
     * @Author Axu
     * @Description //TODO 查询文章总数
     * @Date 17:00 2019/3/28
     * @Param []
     * @return int
     **/
    @Override
    public Integer articleTotal() {
        return myShareDao.articleTotal();
    }

    /**
     * @Author Axu
     * @Description //TODO 根据articleUuid 搜索文章内容
     * @Date 17:19 2019/3/30
     * @Param [articleUuid]
     * @return com.axu.share.pojo.article
     **/
    @Override
    public Article selectArticleUuid(String articleUuid) {

        Article  article = myShareDao.selectArticleUuid(articleUuid);

        //获取图片url
        List<Photo> photoList = myShareDao.selectPhotoUrl(articleUuid);
        if(photoList.size() > 0){
            String[] photoUrl = new String[photoList.size()];
            for(int j = 0; j < photoList.size(); j ++){
                photoUrl[j] = photoList.get(j).getPhotoUrl();
            }
            article.setPhotoUrl2(photoUrl);
        }

        return article;
    }

    /**
     * @Author Axu
     * @Description //TODO 根据文章作者名称获取该作者发表的所有文章name
     * @Date 18:21 2019/3/30
     * @Param [article]
     * @return java.util.List<java.lang.String>
     **/
    @Override
    public List<Article> selectUserArticleName(Article article) {
        return myShareDao.selectUserArticleName(article);
    }

    @Override
    public List<Photo> selectArtcielUrl(Article article) {
        return myShareDao.selectPhotoUrl(article.getArticleUuid());
    }

    /**
     * @Author Axu
     * @Description //TODO 获取最新的10条文章数据
     * @Date 11:24 2019/3/31
     * @Param []
     * @return java.util.List<com.axu.share.pojo.article>
     **/
    @Override
    public List<Article> selectNewArticle() {
        return myShareDao.selectNewArticle();
    }

    /**
     * @Author Axu
     * @Description //TODO 根据用户uuid搜索该用户的所有文章
     * @Date 21:33 2019/4/1
     * @Param [user]
     * @return java.util.List<com.axu.share.pojo.article>
     **/
    @Override
    public List<Article> selectArticleByUserUuid(User user) {

        List<Article> articleList = myShareDao.selectArticleByUserUuid(user);
        
        return articleList;
    }

    /**
     * @Author Axu
     * @Description //TODO 根据用户获取该用户的所有文章总数（发布和草稿）
     * @Date 22:21 2019/4/1
     * @Param [user]
     * @return java.lang.Integer
     **/
    @Override
    public Integer getUserArticleCount(User user) {
        return myShareDao.getUserArticleCount(user);
    }

    /**
     * @Author Axu
     * @Description //TODO 修改文章内容
     * @Date 19:07 2019/4/4
     * @Param [article]
     * @return void
     **/
    @Override
    public void updataArticle(Article article) {

        myShareDao.updataArticle(article);
    }

    /**
     * @Author Axu
     * @Description //TODO 删除文章 将articleFlag 置为 0
     * @Date 14:41 2019/4/6
     * @Param [article]
     * @return void
     **/
    @Override
    public void deleteArticle(Article article) {
        if(article.getArticleUuid() != null || !article.getArticleUuid().equals("")){
            article.setArticleFlag("0");
        }

        myShareDao.deleteArticle(article);
    }

    /**
     * @Author Axu
     * @Description //TODO 获取文章总数
     * @Date 15:21 2019/5/5
     * @Param []
     * @return int
     **/
    @Override
    public int getArticleCount() {
        return myShareDao.getArticleCount();
    }

    /**
     * @Author Axu
     * @Description //TODO 获取用户的文章总数
     * @Date 17:35 2019/5/5
     * @Param [userUuid]
     * @return int
     **/
    @Override
    public int getUserArticleNumber(String userUuid) {
        return myShareDao.getUserArticleNumber(userUuid);
    }


}
