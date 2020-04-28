package com.axu.share.service.impl;

import com.axu.share.dao.RegisterDao;
import com.axu.share.dao.RoleDao;
import com.axu.share.pojo.Role;
import com.axu.share.pojo.User;
import com.axu.share.service.RegisterService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    RegisterDao registerDao;
    @Autowired
    RoleDao roleDao;

    @Override
    public Integer addUser(User user){

        Integer i = 0;//标记是否保存成功

        //产生uuid
        UUID uuid = UUID.randomUUID();
        user.setUserUuid(uuid.toString());

        user.setUserFlag("1");//用户信息状态代码

//        //获取当前系统时间方式一
//        Date date = new Date();
//        System.out.println("date" +date);
//        SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
//        System.out.println("时间：" + df.format(date));
//        user.setUserBeginTime(df);

        /*//方式二
        Calendar c = Calendar.getInstance();
        Date date1 = c.getTime();
        System.out.println("date1:" + date1);*/


        try{
            //对密码进行MD5加密
            String hashAlogorithmName = "MD5";
            Object credentials = user.getUserPassword();//获取密码
            Object salt = user.getUserName();//设置盐值,默认使用用户名作为盐值
            int hashIterations = 1024;//加密的次数
            Object passwordResult = new SimpleHash(hashAlogorithmName , credentials , salt , hashIterations );
            System.out.println("result:" + passwordResult);

            //将密码替换成加密后的字符串
            user.setUserPassword(passwordResult.toString());

            //设置用户的 角色
            user.setUserRole("normal");

            //查询角色id
            Role role = roleDao.selectRole(user);
            System.out.println("-===================" + user.getUserUuid());

            role.setUserUuid(user.getUserUuid());
            //添加用户拥有的角色
            roleDao.addUserRole(role);

            registerDao.addUser(user);//将用户信息添加到user表
            i = 1;
        }catch (Exception e){
            System.out.println(e.getMessage());
            i = 0;
        }

        return i;
    }

}
