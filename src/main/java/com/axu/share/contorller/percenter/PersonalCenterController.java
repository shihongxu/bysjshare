package com.axu.share.contorller.percenter;

import com.axu.share.pojo.Article;
import com.axu.share.pojo.User;
import com.axu.share.service.UserService;
import com.axu.share.service.myshare.MyShareService;
import com.axu.share.service.personalcenter.PersonalCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Axu
 * @Description //TODO 个人中心
 * @Date 15:52 2019/3/13
 * @Param 
 * @return 
 **/
@Controller
@RequestMapping(value = "/percenter")
public class PersonalCenterController {

    @Autowired
    UserService userService;

    @Autowired
    PersonalCenterService personalCenterService;

    @Autowired
    MyShareService myShareService;
    
    /**
     * @Author Axu
     * @Description //TODO 返回个人中心页面
     * @Date 15:54 2019/3/13
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping(value="/percenterpage")
    public String perCenterPage(Model model) {

        //获取当前登录用户
        User tokenUser = userService.getTokenUser();
        if (tokenUser.getUserName() != null){//判断不为空
//            request.setAttribute("tokenUserName", tokenUser.getUserName());
            model.addAttribute("tokenUser", tokenUser);
        }
        model.addAttribute("pageFlag","个人中心");

        //判断用户权限
        String userRole = userService.getUserRole(tokenUser);
        if("1".equals(userRole)){
            model.addAttribute("flag", 1);
            return "/admin/admin";//管理员用户 返回后台管理页面
        }else{
            return "/percenter/percenterpage";//普通用户返回个人数据修改页面
        }
    }

    /**
     * @Author Axu
     * @Description //TODO 修改个人资料页面
     * @Date 11:31 2019/3/23
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping(value="/updatauser", method = {RequestMethod.POST, RequestMethod.GET})
    public String updateUser(User user, Model model){

        String resyltUrl = "";//返回地址

        if(user.getUserPassword() == null || user.getUserPassword().equals("")){
            model.addAttribute("msg","请输入当前密码！");
        }if(user.getUserName() != null || user.getNewUserPassword() != null || !user.getUserName().equals("") || !user.getNewUserPassword().equals("")){
            model.addAttribute("pageFlag","首页");
            resyltUrl = "/homepage";
        }else{
            //获取当前用户
            User tokenUser = userService.getTokenUser();
            model.addAttribute("tokenUser", tokenUser);
            model.addAttribute("pageFlag","个人中心");

            resyltUrl = "/percenter/percenterpage";
        }
        personalCenterService.updateUser(user);


        return resyltUrl;
    }

//    /**
//     * @Author Axu
//     * @Description //TODO 获取当前用户的文章
//     * @Date 9:51 2019/4/1
//     * @Param []
//     * @return java.util.List<com.axu.share.pojo.Article>
//     **/
//    @RequestMapping(value="/mypublish")
//    @ResponseBody
//    public List<Article> getMyPublish(User user, Model model){
//
//        //获取当前用户
//        User tokenUser = userService.getTokenUser();
//
//        user.setUserUuid(tokenUser.getUserUuid());
//
//        List<Article> articleList = personalCenterService.getMyPublish(user);
//
//        return articleList;
//    }
//
//    /**
//     * @Author Axu
//     * @Description //TODO 根据ArticleUuid 获取该文章数据
//     * @Date 16:32 2019/4/4
//     * @Param [article]
//     * @return java.util.List<com.axu.share.pojo.Article>
//     **/
//    @RequestMapping("/getarticle")
//    @ResponseBody
//    public Article upadaArtciel(Article article){
//
//        //获取文章数据
//        Article articleList = myShareService.selectArticleUuid(article.getArticleUuid());
//
//
//        return articleList;
//    }
//
//    /**
//     * @Author Axu
//     * @Description //TODO 修改改文章数据
//     * @Date 18:34 2019/4/4
//     * @Param [article]
//     * @return java.lang.String
//     **/
//    @RequestMapping("/updataarticle")
//    public String updataArticle(Article article, Model model){
//
//        myShareService.updataArticle(article);
//
//        model.addAttribute("msg", "修改成功!");
//
//        //获取当前登录用户
//        User tokenUser = userService.getTokenUser();
//        if (tokenUser.getUserName() != null){//判断不为空
////            request.setAttribute("tokenUserName", tokenUser.getUserName());
//            model.addAttribute("tokenUser", tokenUser);
//        }
//        model.addAttribute("updataSuccess","1");
//
//        return "/percenter/percenterpage";
//    }
//
//    /**
//     * @Author Axu
//     * @Description //TODO 删除文章
//     * @Date 14:32 2019/4/6
//     * @Param [article]
//     * @return java.lang.String
//     **/
//    @RequestMapping("/deletearticle")
//    public String deleteArticle(Article article, Model model){
//
//        myShareService.deleteArticle(article);
//
//        //获取当前登录用户
//        User tokenUser = userService.getTokenUser();
//        if (tokenUser.getUserName() != null){//判断不为空
////            request.setAttribute("tokenUserName", tokenUser.getUserName());
//            model.addAttribute("tokenUser", tokenUser);
//        }
//        model.addAttribute("updataSuccess","1");
//
//        return "/percenter/percenterpage";
//    }

}
