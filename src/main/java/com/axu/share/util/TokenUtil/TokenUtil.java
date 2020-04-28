package com.axu.share.util.TokenUtil;

import com.axu.share.pojo.User;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;


@Component
public class TokenUtil {

    /**
     * 获取当前登录的用户User对象
     *
     * @return
     */
    public static User getUser() {
        User user = new User();

        String userName = (String)SecurityUtils.getSubject().getPrincipal();

         if(userName != null){
            user.setUserFlag("1");
            user.setUserName(userName);
        }else{
             user = null;
         }

        return user;
    }
}
