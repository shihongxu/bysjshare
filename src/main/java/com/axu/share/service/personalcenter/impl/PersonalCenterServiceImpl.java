package com.axu.share.service.personalcenter.impl;

import com.axu.share.dao.UserDao;
import com.axu.share.pojo.Article;
import com.axu.share.pojo.Photo;
import com.axu.share.pojo.User;
import com.axu.share.service.UserService;
import com.axu.share.service.myshare.MyShareService;
import com.axu.share.service.personalcenter.PersonalCenterService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalCenterServiceImpl implements PersonalCenterService {

    @Autowired
    UserService userService;

    @Autowired
    MyShareService myShareService;

    @Autowired
    UserDao userDao;

    
    /**
     * @Author Axu
     * @Description //TODO 修改用户
     * @Date 15:30 2019/3/31
     * @Param [user]
     * @return void
     **/
    @Override
    public void updateUser(User user) {

        //获取当前登录用户
        User tokenUser = userService.getTokenUser();

        //设置当前登录用户的uuid
        user.setUserUuid(tokenUser.getUserUuid());

        //设置加密后的密码
        if(user.getNewUserPassword() != null || !user.getNewUserPassword().equals("") || user.getUserName() != null || "".equals(user.getUserName())){

            //对密码进行MD5加密
            String hashAlogorithmName = "MD5";
            Object credentials = "";//获取密码
            if(user.getNewUserPassword() != null || !user.getNewUserPassword().equals("")){
                credentials = user.getNewUserPassword();
            }else{
                credentials = user.getUserPassword();
            }
            Object salt = "";//设置盐值
            if (user.getUserName() != null || !"".equals(user.getUserName())){
                salt = user.getUserName();//设置盐值,默认使用用户名作为盐值
            }else{
                salt = tokenUser.getUserName();
            }
            int hashIterations = 1024;//加密的次数
            Object passwordResult = new SimpleHash(hashAlogorithmName , credentials , salt , hashIterations );
            System.out.println("result:" + passwordResult);

            //将密码替换成加密后的字符串
            user.setUserPassword(passwordResult.toString());
        }

        userDao.updateUser(user);

    }

    /**
     * @Author Axu
     * @Description //TODO 根据用户uuid搜索该用户的所有文章
     * @Date 21:35 2019/4/1
     * @Param [user]
     * @return java.util.List<com.axu.share.pojo.article>
     **/
    @Override
    public List<Article> getMyPublish(User user) {

        //获取文章数据
        List<Article> articleList = myShareService.selectArticleByUserUuid(user);

        if (articleList != null || !articleList.isEmpty()){
            for(int i = 0; i < articleList.size(); i++){
                Article article = new Article();
                article.setArticleUuid(articleList.get(i).getArticleUuid());

                //获取图片url
                List<Photo> photoList = myShareService.selectArtcielUrl(article);
                if(photoList.size() > 0){
                    String[] photoUrl = new String[photoList.size()];
                    for(int j = 0; j < photoList.size(); j ++){
                        photoUrl[j] = photoList.get(j).getPhotoUrl();
                    }
                    articleList.get(i).setPhotoUrl2(photoUrl);
                }
            }
        }


        //设置文章总数
        articleList.get(0).setTotal(myShareService.getUserArticleCount(user));

        return articleList;
    }
}
