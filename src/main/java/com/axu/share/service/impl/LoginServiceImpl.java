package com.axu.share.service.impl;

import com.axu.share.dao.LoginDao;
import com.axu.share.pojo.User;
import com.axu.share.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Axu
 * @Description 登录Service//TODO
 * @Date 17:09 2018/12/26
 * @Param
 * @return
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginDao loginDao;
    
    /**
     * @Author Axu
     * @Description //TODO 多条件 查询用户
     * @Date 10:35 2018/12/27
     * @Param [user]
     * @return java.util.List<com.axu.share.pojo.User>
     **/
    @Override
    public List<User> selectUser(User user){
        return loginDao.selectUser(user);
    }
}
