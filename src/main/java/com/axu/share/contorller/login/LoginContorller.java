package com.axu.share.contorller.login;

import com.axu.share.pojo.User;
import com.axu.share.service.LoginService;
import com.axu.share.service.UserService;
import com.axu.share.util.TokenUtil.TokenUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/login")
public class LoginContorller {

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    //返回登录页面
    @RequestMapping(value = "/loginPage")
    public String loginPage(){
        return "/login/login";
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public String loginUser(User user, Model model){

        if(user.getUserName() == null || user.getUserName().equals("")){
            model.addAttribute("msg","请输入用户名！");
            return "/login/login";
        }if(user.getUserPassword() == null || user.getUserPassword().equals("")){
            model.addAttribute("msg","请输入密码！");
            return "/login/login";
        }
        Subject subject = SecurityUtils.getSubject();//获得主体
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getUserPassword());//获得主体提交请求
        try {
            subject.login(token);//登  录

        }catch(AuthenticationException e){
            model.addAttribute("msg", "用户名或密码错误!");
            return "/login/login";//错误则返回登录页面，并提示错误信息！
        }


        return "redirect:/homepage";
    }
}
