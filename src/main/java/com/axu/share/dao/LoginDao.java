package com.axu.share.dao;

import com.axu.share.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Axu
 * @Description //TODO 登录Dao
 * @Date 17:13 2018/12/26
 * @Param
 * @return
 **/
@Repository
public interface LoginDao {

    /**
     * @Author Axu
     * @Description //TODO 根据用户名 查询用户
     * @Date 17:15 2018/12/26
     * @Param [user]
     * @return com.axu.share.pojo.User
     **/
    public List<User> selectUserName(User user);

    /**
     * @Author Axu
     * @Description //TODO 多条件 查询用户
     * @Date 10:27 2018/12/27
     * @Param [user]
     * @return java.util.List<com.axu.share.pojo.User>
     **/
    public List<User> selectUser(User user);
}
