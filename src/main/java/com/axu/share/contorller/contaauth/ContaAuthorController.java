package com.axu.share.contorller.contaauth;

import com.axu.share.pojo.User;
import com.axu.share.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contaauthor")
public class ContaAuthorController {

    @Autowired
    UserService userService;

    /**
     * @Author Axu
     * @Description //TODO 联系作者页面
     * @Date 16:59 2019/4/6
     * @Param [model]
     * @return java.lang.String
     **/
    @RequestMapping("/contaauthorwechat")
    public String contaAuthor(Model model){

        //获取当前登录用户
        User tokenUser = userService.getTokenUser();
        if (tokenUser.getUserName() != null){//判断不为空
//            request.setAttribute("tokenUserName", tokenUser.getUserName());
            model.addAttribute("tokenUser", tokenUser);
        }

        return "/contaauthor/contaauthorwechat";

    }

}
