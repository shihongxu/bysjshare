package com.axu.share.contorller.login;

import com.axu.share.pojo.User;
import com.axu.share.service.LoginService;
import com.axu.share.service.RegisterService;
import com.axu.share.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Author Axu
 * @Description 注册//TODO
 * @Date 9:45 2018/12/25
 **/
@Controller
@RequestMapping(value = "/login/register")
public class RegisterContorller {

    @Autowired
    RegisterService registerService;

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    /**
     * @Author Axu
     * @Description 返回注册成功页面//TODO
     * @Date 9:45 2018/12/25
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping(value = "/registerPage")
    public String registerPage(){
        
        return "/login/register";
    }

    /**
     * @Author Axu
     * @Description 注册新用户//TODO
     * @Date 10:37 2018/12/25
     * @Param [user]
     * @return java.lang.String
     **/
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(User user, Model model){

        String url = "";//返回地址
        User userTemp = new User();
        userTemp.setUserFlag("1");
        userTemp.setUserName(user.getUserName());

        List<User> userList = loginService.selectUser(userTemp);

        if(userList.size() != 0){
            model.addAttribute("msg", "用户名已存在,请重新输入!");
            return "/login/register";
        }else{
            if(user.getUserName() == ""){
                model.addAttribute("msg","请填写用户名!");
                return "/login/register";
            }if(user.getUserPassword() == ""){
                model.addAttribute("msg","请填写密码!");
                return "/login/register";
            }

            Integer add = registerService.addUser(user);
            if(1 == add){
                url = "/login/registerafter/successfully";//注册成功页面
            }else{
                url = "/login/registerafter/failure";//插入异常的注册失败页面
            }
        }

        return url;
    }


}
