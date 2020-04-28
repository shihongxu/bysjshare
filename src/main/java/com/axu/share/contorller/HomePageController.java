package com.axu.share.contorller;

import com.axu.share.pojo.Article;
import com.axu.share.pojo.Photo;
import com.axu.share.pojo.User;
import com.axu.share.service.LoginService;
import com.axu.share.service.PhotoService;
import com.axu.share.service.UserService;
import com.axu.share.service.myshare.MyShareService;
import com.axu.share.util.ExcelUtil.ExcelUtil;
import com.axu.share.util.TokenUtil.TokenUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.util.*;

/**
 * @author axu
 * 2018/12/24
 * 主页
 */
@Controller
@RequestMapping(value = "/")
public class HomePageController {

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    @Autowired
    PhotoService photoService;

    @Autowired
    MyShareService myShareService;

    @RequestMapping(value = "/index")
    public String indexText(){

        return "/index";
    }

    @RequestMapping(value = "/homepage")
    public String homePage(HttpServletRequest request, HttpServletResponse response, Model model){

        //获取当前登录用户
        User tokenUser = userService.getTokenUser();
        if (tokenUser.getUserName() != null){//判断不为空
//            request.setAttribute("tokenUserName", tokenUser.getUserName());
            model.addAttribute("tokenUser", tokenUser);
        }

        model.addAttribute("pageFlag","首页");

        return "/homepage";
    }

    /**
     * @Author Axu
     * @Description //TODO 文章类型选择搜索判断
     * @Date 10:22 2019/4/26
     * @Param [flagPage, request, response, model]
     * @return java.lang.String
     **/
    @RequestMapping("/slarticletp")
    public String slarticletp(@RequestParam("flagpage") String flagPage,HttpServletRequest request,
                              HttpServletResponse response, Model model){
        //获取当前登录用户
        User tokenUser = userService.getTokenUser();
        if (tokenUser.getUserName() != null){//判断不为空
//            request.setAttribute("tokenUserName", tokenUser.getUserName());
            model.addAttribute("tokenUser", tokenUser);
        }

        model.addAttribute("flagPage", flagPage);
        model.addAttribute("pageFlag","首页");

        return "/homepage";
    }


    /**
     * @Author Axu
     * @Description //TODO 加载轮播图片，随机5张图片
     * @Date 10:07 2019/3/27
     * @Param []
     * @return java.util.List<com.axu.share.pojo.Photo>
     **/
    @RequestMapping(value="homepage/selectlbimg", method= RequestMethod.GET)
    @ResponseBody
    public List<Photo> selectLbImg(){

        List<Photo> photoList = photoService.selectImg();

        return photoList;
    }

    /**
     * @Author Axu
     * @Description //TODO 初始化首页文章内容显示
     * @Date 10:10 2019/3/27
     * @Param []
     * @return java.util.List<com.axu.share.pojo.article>
     **/
    @RequestMapping(value = "/homepage/selectarticledata", method = RequestMethod.GET)
    @ResponseBody
    public List<Article> selectArticleData(Article article, Model model){

        List<Article> articleList = myShareService.selectArticleData(article);

        if(articleList.size() == 0){
            model.addAttribute("msg","搜索内容为空!");
        }

        return articleList;
    }

    /**
     * @Author Axu
     * @Description //TODO 获取最新的文章标题 和类型
     * @Date 10:59 2019/3/31
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping(value = "/homepage/newarticle")
    @ResponseBody
    public List<Article> getNewArticleName(){
        List<Article> articleList = myShareService.selectNewArticle();

        return articleList;
    }

    //导出Excel
    @RequestMapping(value = "/homePage/exportExcel")
    public String exportExcel(HttpServletRequest request, HttpServletResponse response){

        try{
            User userTest = TokenUtil.getUser();

            //导出头
            HSSFWorkbook wb = new HSSFWorkbook();//创建工作簿
            String fileName = "用户表.xls";

            User user = new User();
            user.setUserFlag("1");
            int colnum = 5;

            Map<String, Map<String, String>> headerNameMap = new HashMap<>();
            //创建标题行
            Map<String, String> headerMap = new HashMap<>();
            headerMap.put("姓名","userName");
            headerMap.put("用户邮箱","userEmail");
            headerMap.put("用户电话","userTel");
            headerMap.put("性别", "userSex");
            headerNameMap.put("用户表1", headerMap);

            Map<String, String> headerMap2 = new HashMap<>();
            headerMap2.put("姓名","userName");

            headerMap2.put("用户邮箱","userEmail");
            headerMap2.put("用户电话","userTel");

            headerNameMap.put("用户表1", headerMap);

            headerNameMap.put("用户表2", headerMap2);

            //创建Excel 数据
            Map<String, List<Map<String, String>>> sheetDataMap = new HashMap<>();
            List<Map<String, String>> listMap = new ArrayList<>();
            List<User> userList = loginService.selectUser(user);
            for(int i = 0; i < userList.size(); i ++) {
                Map<String, String> userMap = new HashMap<>();
                userMap.put("userName", userList.get(i).getUserName());
                userMap.put("userEmail", userList.get(i).getUserEmail());
                userMap.put("userTel", userList.get(i).getUserTel());
                userMap.put("userSex", userList.get(i).getUserSex());

                listMap.add(userMap);
            }
            sheetDataMap.put("用户表1", listMap);
            sheetDataMap.put("用户表2", listMap);

            ExcelUtil.fillExcelData(wb, sheetDataMap, headerNameMap);//传入数据，生成表格

            ExcelUtil.ResponesExportExcel(response, wb, fileName);//生成导出的表

        }catch(Exception e){
            e.printStackTrace();
        }finally{

        }

        return null;
    }
}
