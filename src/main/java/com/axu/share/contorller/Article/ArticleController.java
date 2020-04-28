package com.axu.share.contorller.Article;

import com.axu.share.pojo.Article;
import com.axu.share.pojo.Comment;
import com.axu.share.pojo.Photo;
import com.axu.share.pojo.User;
import com.axu.share.service.CommentService;
import com.axu.share.service.UserService;
import com.axu.share.service.article.ArticleService;
import com.axu.share.service.myshare.MyShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Axu
 * @Description //TODO 文章内容
 * @Date 14:25 2019/3/30
 * @Param
 * @return
 **/
@Controller
@RequestMapping(value="/article")
public class ArticleController {

    @Autowired
    UserService userService;

    @Autowired
    MyShareService myShareService;

    @Autowired
    ArticleService articleService;

    @Autowired
    CommentService commentService;

    /**
     * @Author Axu
     * @Description //TODO 加载文章
     * @Date 18:10 2019/3/30
     * @Param [articleUuid, model]
     * @return java.lang.String
     **/
    @RequestMapping(value = "/articletitle")
    public String articlePage(@RequestParam("articleUuid")String articleUuid, Model model){

        //获取文章数据
        Article article = myShareService.selectArticleUuid(articleUuid);


        model.addAttribute("article", article);


        //获取当前登录用户
        User tokenUser = userService.getTokenUser();
        if (tokenUser.getUserName() != null){//判断不为空
//            request.setAttribute("tokenUserName", tokenUser.getUserName());
            model.addAttribute("tokenUser", tokenUser);
        }

        //获取用户角色
        String userRole = userService.getUserRole(tokenUser);
        if ("1".equals(userRole)){//管理员权限

            return "/article/articleadmin";
        }

        return "/article/articleone";
    }

    /**
     * @Author Axu
     * @Description //TODO 根据作者名称搜索该作者的所有发表的文章
     * @Date 21:37 2019/3/30
     * @Param [article]
     * @return java.util.List<com.axu.share.pojo.article>
     **/
    @RequestMapping(value="/selectUserArticleName")
    @ResponseBody
    public Map<String, Object> selectUserArticleName(Article article){

        Map<String, Object> map = new HashMap<>();

        //获取文章名
        List<Article> listName = myShareService.selectUserArticleName(article);

        //获取文章图片地址
        List<Photo> photoUrl = myShareService.selectArtcielUrl(article);

        //获取该文章评论
        List<Comment> commentList = commentService.getComent(article);

        map.put("listName", listName);
        map.put("photoUrl", photoUrl);
        map.put("commentList", commentList);

        return map;

    }

    /**
     * @Author Axu
     * @Description //TODO 添加评论信息
     * @Date 11:32 2019/4/17
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping(value="/comment", method = RequestMethod.POST)
    public String insertComment(Model model, Comment comment){


        //添加评论数据
        articleService.insertComment(comment);

        Article article = myShareService.selectArticleUuid(comment.getArticleUuid());
        int commentNumber = Integer.parseInt(article.getCommentNumber());
        commentNumber ++;
        article.setCommentNumber(String.valueOf(commentNumber));
        myShareService.updataArticle(article);

        model.addAttribute("article", article);

        //获取当前登录用户
        User tokenUser = userService.getTokenUser();
        if (tokenUser.getUserName() != null){//判断不为空
            model.addAttribute("tokenUser", tokenUser);
        }

        //获取用户角色
        String userRole = userService.getUserRole(tokenUser);
        if ("1".equals(userRole)){//管理员权限

            return "/article/articleadmin";
        }

        return "/article/articleone";
    }

    /**
     * @Author Axu
     * @Description //TODO 删除文章评论
     * @Date 18:21 2019/4/24
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping(value = "/deletecomment",  method = RequestMethod.GET)
    @ResponseBody
    public String deleteComment(Model model, Comment comment){

        comment.setScbs("1");
        commentService.deleteComment(comment);
        System.out.println();

        //总
        Article article = myShareService.selectArticleUuid(comment.getArticleUuid());
        int commentNumber = Integer.parseInt(article.getCommentNumber());
        commentNumber --;
        article.setCommentNumber(String.valueOf(commentNumber));
        myShareService.updataArticle(article);
        model.addAttribute("article", article);

        //获取当前登录用户
        User tokenUser = userService.getTokenUser();
        if (tokenUser.getUserName() != null){//判断不为空
            model.addAttribute("tokenUser", tokenUser);
        }

        //获取用户角色
        String userRole = userService.getUserRole(tokenUser);
        if ("1".equals(userRole)){//管理员权限

            return "管理员";
        }

        return "普通";
    }

}
