package com.axu.shiro.realm;

import com.axu.share.pojo.User;
import com.axu.share.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    /**
     * @Author Axu
     * @Description //TODO 验证的方法
     * @Date 22:37 2019/3/6
     * @Param [authenticationToken]
     * @return org.apache.shiro.authc.AuthenticationInfo
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        User user = new User();
        String username = "";
        String userPassword = "";

        //1、把AuthenticationToken转换为UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        user.setUserName(usernamePasswordToken.getUsername());
        user.setUserFlag("1");


        List<User> userList = userService.getUser(user);//从数据库获取用户信息
        if (userList.size() > 0 ){//用户存在的情况
            user = userList.get(0);
            //2、从UsernamePasswordToken中来获取Username
            username = user.getUserName();//从数据库获取用户名
            userPassword = user.getUserPassword();//获取用户 密码
        }else{//用户不存在

        }



        //3、调用数据库的方法，从数据库中查询username对应的用户记录
        System.out.println("从数据库中获取username" + username + "所对应的用户信息");

//         ***************以下信息是从数据库中获取的
//          1)、principal: 认证的实体信息，可以是username，也可以是数据库对应的实体类对象
        Object principal = username;

        //2)、credentials :密码
        Object credentials = userPassword;

        //3)、realmName : 当前realm对象的name，调用父类的getName()方法即可；
        String realmName = getName();

        //4)、盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);//盐值

        SimpleAuthenticationInfo simpleAuthenticationInfo = null;
        simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);//带上盐值，以便密码比对
//        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, credentials, realmName);

        return simpleAuthenticationInfo;
    }

    public static void main(String[] args){
        String hashAlogorithmName = "MD5";
        Object credentials = "123456";
        Object salt = "余文丽";//设置盐值
        int hashIterations = 1024;//加密的次数
        Object result = new SimpleHash(hashAlogorithmName , credentials , salt , hashIterations );
        System.out.println("result:" + result);
    }

    /**
     * @Author Axu
     * @Description //TODO 授权的方法
     * @Date 22:37 2019/3/6
     * @Param [principalCollection]
     * @return org.apache.shiro.authz.AuthorizationInfo
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("===授权的方法");

        //1、从PrincipalCollection 中来获取当前登录用户的信息getPrimaryPrincipal
        Object principal = principalCollection.getPrimaryPrincipal();

        //2、利用登录的用户信息来获取当前用户的角色或权限(可能需要查询数据库)
        Set<String> roles = new HashSet<>();
        roles.add("user");
        if ("admin".equals(principal)){
            roles.add("admin");
        }

        //3、创建SimpleAuthenticationInfo, 并返回reles 属性
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);

        //4、返回SimpleAuthorizationInfo对象

        return simpleAuthorizationInfo;
    }


}
