package com.axu.share.service.impl;

import com.axu.share.dao.UserDao;
import com.axu.share.pojo.User;
import com.axu.share.service.UserService;
import com.axu.share.util.TokenUtil.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    UserDao userDao;
    
    /**
     * @Author Axu
     * @Description //TODO 获取用户
     * @Date 21:30 2019/3/12
     * @Param [user]
     * @return java.util.List<com.axu.share.pojo.User>
     **/
    @Override
    public List<User> getUser(User user) {
        List<User> userList = userDao.getUser(user);
        
        return userList;
    }

    /**
     * @Author Axu
     * @Description //TODO 获取当前登录用户
     * @Date 14:31 2019/3/13
     * @Param [user]
     * @return com.axu.share.pojo.User
     **/
    @Override
    public User getTokenUser() {
        User tokenUser = new User();
        
        List<User> userList = getUser(TokenUtil.getUser());
        
        if(userList.size() > 0){
            tokenUser = userList.get(0);
        }
        
        return tokenUser;
    }

    /**
     * @Author Axu
     * @Description //TODO 获取用户角色
     * @Date 20:43 2019/5/5
     * @Param [user]
     * @return java.lang.String
     **/
    @Override
    public String getUserRole(User user) {
        return userDao.getUserRole(user);
    }

    /**
     * @Author Axu
     * @Description //TODO 获取用户总数
     * @Date 21:16 2019/5/5
     * @Param []
     * @return int
     **/
    @Override
    public int getUserCount() {
        return userDao.getUserCount();
    }
}
