package com.axu.share.contorller.admin;

import com.axu.share.pojo.Article;
import com.axu.share.pojo.User;
import com.axu.share.service.UserService;
import com.axu.share.service.admin.AdminService;
import com.axu.share.service.myshare.MyShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Axu
 * @Description //TODO 系统管理员控制层
 * @Date 14:47 2019/5/4
 * @Param 
 * @return 
 **/
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @Autowired
    MyShareService myShareService;

    /**
     * @Author Axu
     * @Description //TODO 获取系统注册用户数据，并加载表格
     * @Date 14:51 2019/5/4
     * @Param []
     * @return java.util.List<com.axu.share.pojo.User>
     **/
    @RequestMapping("geruserdata")
    @ResponseBody
    public Object getUserData(HttpServletRequest request, HttpServletResponse response){

        Map<String, Object> resultMap = new HashMap<>();
        List<User> userList = new ArrayList<>();

        int page =Integer.parseInt( request.getParameter("page"));//获取页码
        int size = Integer.parseInt(request.getParameter("limit"));//获取每页的长度
        User user = new User();
        user.setPage(page);
        user.setSize(size);

        userList = adminService.getUserData(user);//获取用户数据
        int count = userService.getUserCount();//获取用户总数

        resultMap.put("code","0");
        resultMap.put("msg","");
        resultMap.put("count", count);
        resultMap.put("data", userList);

        return resultMap;

    }

    /**
     * @Author Axu
     * @Description //TODO 返回文章统计页面
     * @Date 19:06 2019/5/4
     * @Param []
     * @return java.lang.Strings
     **/
    @RequestMapping(value = "/articlestatistics")
    public String articleStatistics(Model model){

        //获取当前登录用户
        User tokenUser = userService.getTokenUser();
        if (tokenUser.getUserName() != null){//判断不为空
//            request.setAttribute("tokenUserName", tokenUser.getUserName());
            model.addAttribute("tokenUser", tokenUser);
        }
        model.addAttribute("flag", 2);

        model.addAttribute("pageFlag","个人中心");

        return "/admin/articlestatistics";
    }
    
    /**
     * @Author Axu
     * @Description //TODO 获得文章基本信息
     * @Date 19:19 2019/5/4
     * @Param [model]
     * @return java.lang.Object
     **/
    @RequestMapping(value = "getarticles")
    @ResponseBody
    public Object getArticles(Model model, HttpServletRequest request, HttpServletResponse response){

        Map<String, Object> resultMap = new HashMap<>();
        int page =Integer.parseInt( request.getParameter("page"));//获取页码
        int size = Integer.parseInt(request.getParameter("limit"));//获取每页的长度
        Article article = new Article();
        article.setPage(page);
        article.setSize(size);

        List<Article> articleList = adminService.getArticles(article);//获取文章基本数据
        int count = myShareService.getArticleCount();//获取文章总数

        resultMap.put("code","0");
        resultMap.put("msg","");
        resultMap.put("count", count);
        resultMap.put("data", articleList);

        return resultMap;
    }


}
