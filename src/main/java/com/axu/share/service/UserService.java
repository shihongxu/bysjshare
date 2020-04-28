package com.axu.share.service;

import com.axu.share.pojo.User;

import java.util.List;

public interface UserService {

    /**
     * @Author Axu
     * @Description //TODO 获取User
     * @Date 21:30 2019/3/12
     * @Param [user]
     * @return java.util.List<com.axu.share.pojo.User>
     **/
    List<User> getUser(User user);

    /**
     * @Author Axu
     * @Description //TODO 获取当前登录用户
     * @Date 14:27 2019/3/13
     * @Param [user]
     * @return com.axu.share.pojo.User
     **/
    User getTokenUser();
    
    /**
     * @Author Axu
     * @Description //TODO 获取用户角色
     * @Date 17:46 2019/4/6
     * @Param [user]
     * @return java.lang.String
     **/
    String getUserRole(User user);
    
    /**
     * @Author Axu
     * @Description //TODO 获取用户总数
     * @Date 17:49 2019/5/4
     * @Param []
     * @return java.lang.String
     **/
    int getUserCount();
}
