package com.axu.share.service.personalcenter;

import com.axu.share.pojo.Article;
import com.axu.share.pojo.User;

import java.util.List;

public interface PersonalCenterService {


    /**
     * @Author Axu
     * @Description //TODO修改用户
     * @Date 15:30 2019/3/31
     * @Param [user]
     * @return void
     **/
    void updateUser(User user);

    /**
     * @Author Axu
     * @Description //TODO 获取当前用户的所有文章
     * @Date 10:01 2019/4/1
     * @Param [article]
     * @return java.util.List<com.axu.share.pojo.Article>
     **/
    List<Article> getMyPublish(User user);
}
