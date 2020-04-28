package com.axu.share.contorller.login.registerafter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Axu
 * @Description 注册后的Contorller//TODO
 * @Date 10:55 2018/12/26
 * @Param
 * @return
 **/
@Controller
@RequestMapping(value = "/login/registerafter")
public class RegisterAfterContorller {

    /**
     * @Author Axu
     * @Description 返回注册成功页面//TODO
     * @Date 16:13 2018/12/26
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping(value = "/successfully")
    public String successfully(){
        return "login/registerafter/successfully";
    }

    /**
     * @Author Axu
     * @Description 返回注册失败页面//TODO
     * @Date 16:13 2018/12/26
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping(value = "/failure")
    public String failure(){
        return "/login/registerafter/failure";
    }
}
