package com.axu.share.dao;

import com.axu.share.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterDao {

    /**
     * @Author Axu
     * @Description 添加新用户//TODO
     * @Date 10:53 2018/12/25
     * @Param [user]
     * @return int
     **/
    public void addUser(User user);

    /**
     * @Author Axu
     * @Description //TODO 添加用户角色信息  userUuid:用户的uuid; roleId:角色id
     * @Date 21:15 2019/3/11
     * @Param [userUuid, roleId]
     * @return void
     **/
    public void addUserRole(String userUuid, String roleId);

}
