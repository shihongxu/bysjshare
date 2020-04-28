package com.axu.share.dao;

import com.axu.share.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Axu
 * @Description //TODO 获取用户对象的dao 
 * @Date 22:47 2019/3/7
 * @Param 
 * @return 
 **/
@Repository
public interface UserDao {
    
    /**
     * @Author Axu
     * @Description //TODO 根据条件获取user对象
     * @Date 22:53 2019/3/7
     * @Param [user]
     * @return com.axu.share.pojo.User
     **/
    public List<User> getUser(User user);

    /**
     * @Author Axu
     * @Description //TODO 修改用户
     * @Date 16:24 2019/3/31
     * @Param [user]
     * @return void
     **/
    void updateUser(User user);
    
    /**
     * @Author Axu
     * @Description //TODO 获取给用户的角色
     * @Date 17:22 2019/4/6
     * @Param [user]
     * @return java.lang.String
     **/
    String getUserRole(User user);

    /**
     * @Author Axu
     * @Description //TODO 获取所有用户数据
     * @Date 15:48 2019/5/4
     * @Param [user]
     * @return java.util.List<com.axu.share.pojo.User>
     **/
    List<User> getUserData(User user);

    /**
     * @Author Axu
     * @Description //TODO 获取用户总数
     * @Date 17:52 2019/5/4
     * @Param []
     * @return java.lang.String
     **/
    int getUserCount();
}
