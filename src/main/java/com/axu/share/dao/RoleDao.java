package com.axu.share.dao;

import com.axu.share.pojo.Role;
import com.axu.share.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
    
    /**
     * @Author Axu
     * @Description //TODO 添加用户角色
     * @Date 21:35 2019/3/11
     * @Param [role]
     * @return void
     **/
    public void addUserRole(Role role);

    /**
     * @Author Axu
     * @Description //TODO 根据用户uuid查询改用户拥有的角色
     * @Date 21:38 2019/3/11
     * @Param [user]
     * @return com.axu.share.pojo.Role
     **/
    public String selectUserRole(User user);
    
    /**
     * @Author Axu
     * @Description //TODO 根据用户角色查询角色id
     * @Date 21:45 2019/3/11
     * @Param [role]
     * @return com.axu.share.pojo.Role
     **/
    public Role selectRole(User user);
}
