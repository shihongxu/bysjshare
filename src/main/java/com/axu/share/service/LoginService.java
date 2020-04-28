package com.axu.share.service;

import com.axu.share.pojo.User;

import java.util.List;

public interface LoginService {

    /**
     * @Author Axu
     * @Description //TODO 多条件 查询用户
     * @Date 10:29 2018/12/27
     * @Param [user]
     * @return java.util.List<com.axu.share.pojo.User>
     **/
    public List<User> selectUser(User user);
}
