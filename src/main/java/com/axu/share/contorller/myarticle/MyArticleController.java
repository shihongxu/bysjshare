package com.axu.share.contorller.myarticle;

import com.axu.share.pojo.Article;
import com.axu.share.pojo.User;
import com.axu.share.service.UserService;
import com.axu.share.service.myshare.MyShareService;
import com.axu.share.service.personalcenter.PersonalCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/myarticle")
public class MyArticleController {

    @Autowired
    UserService userService;

    @Autowired
    MyShareService myShareService;

    @Autowired
    PersonalCenterService personalCenterService;

    /**
     * @Author Axu
     * @Description //TODO 返回我的分享页面
     * @Date 15:25 2019/4/6
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/myarticle")
    public String myArticle(Model model){

        //获取当前登录用户
        User tokenUser = userService.getTokenUser();
        if (tokenUser.getUserName() != null){//判断不为空
//            request.setAttribute("tokenUserName", tokenUser.getUserName());
            model.addAttribute("tokenUser", tokenUser);
        }
        model.addAttribute("pageFlag","我的分享");

        return "/myarticle/myarticle";
    }

    /**
     * @Author Axu
     * @Description //TODO 获取当前用户的文章
     * @Date 9:51 2019/4/1
     * @Param []
     * @return java.util.List<com.axu.share.pojo.article>
     **/
    @RequestMapping(value="/mypublish")
    @ResponseBody
    public List<Article> getMyPublish(User user, Model model){

        //获取当前用户
        User tokenUser = userService.getTokenUser();

        user.setUserUuid(tokenUser.getUserUuid());

        List<Article> articleList = personalCenterService.getMyPublish(user);

        return articleList;
    }

    /**
     * @Author Axu
     * @Description //TODO 根据ArticleUuid 获取该文章数据
     * @Date 16:32 2019/4/4
     * @Param [article]
     * @return java.util.List<com.axu.share.pojo.article>
     **/
    @RequestMapping("/getarticle")
    @ResponseBody
    public Article upadaArtciel(Article article){

        //获取文章数据
        Article articleList = myShareService.selectArticleUuid(article.getArticleUuid());


        return articleList;
    }

    /**
     * @Author Axu
     * @Description //TODO 修改改文章数据
     * @Date 18:34 2019/4/4
     * @Param [article]
     * @return java.lang.String
     **/
    @RequestMapping("/updataarticle")
    public String updataArticle(Article article, Model model){

        myShareService.updataArticle(article);

        model.addAttribute("msg", "修改成功!");

        //获取当前登录用户
        User tokenUser = userService.getTokenUser();
        if (tokenUser.getUserName() != null){//判断不为空
//            request.setAttribute("tokenUserName", tokenUser.getUserName());
            model.addAttribute("tokenUser", tokenUser);
        }
        model.addAttribute("updataSuccess","1");

        return "/myarticle/myarticle";
    }

    /**
     * @Author Axu
     * @Description //TODO 删除文章
     * @Date 14:32 2019/4/6
     * @Param [article]
     * @return java.lang.String
     **/
    @RequestMapping("/deletearticle")
    public String deleteArticle(Article article, Model model){

        myShareService.deleteArticle(article);

        //获取当前登录用户
        User tokenUser = userService.getTokenUser();
        if (tokenUser.getUserName() != null){//判断不为空
//            request.setAttribute("tokenUserName", tokenUser.getUserName());
            model.addAttribute("tokenUser", tokenUser);
        }
        model.addAttribute("updataSuccess","1");

        return "/myarticle/myarticle";
    }


    /**
     * @Author Axu
     * @Description //TODO 删除文章
     * @Date 14:32 2019/4/6
     * @Param [article]
     * @return java.lang.String
     **/
    @RequestMapping("/deletearticleadmin")
    public String deleteArticleAdmin(Article article, Model model){

        myShareService.deleteArticle(article);

        //获取当前登录用户
        User tokenUser = userService.getTokenUser();
        if (tokenUser.getUserName() != null){//判断不为空
//            request.setAttribute("tokenUserName", tokenUser.getUserName());
            model.addAttribute("tokenUser", tokenUser);
        }
        model.addAttribute("updataSuccess","1");

        return "/homepage";
    }
}
