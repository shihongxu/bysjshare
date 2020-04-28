package com.axu.share.contorller.myshare;

import com.axu.share.pojo.Article;
import com.axu.share.pojo.Lable;
import com.axu.share.pojo.Photo;
import com.axu.share.pojo.User;
import com.axu.share.service.PhotoService;
import com.axu.share.service.UserService;
import com.axu.share.service.myshare.MyShareService;
import com.axu.share.util.QiniuUtil.ConstanQiniu;
import com.axu.share.util.QiniuUtil.QiniuUtil;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author Axu
 * @Description //TODO 我要分享Controller
 * @Date 16:00 2019/3/13
 * @Param 
 * @return 
 **/
@Controller
@RequestMapping(value="/myshare")
public class MyShareController {

    @Autowired
    UserService userService;

    @Autowired
    MyShareService myShareService;

    @Autowired
    PhotoService photoService;

    /**
     * @Author Axu
     * @Description //TODO 返回发表信息页面
     * @Date 15:45 2019/3/13
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping(value="/mypublish")
    public String myPublish(Model model){

        //获取当前登录用户
        User tokenUser = userService.getTokenUser();
        if (tokenUser.getUserName() != null){//判断不为空
            model.addAttribute("tokenUser", tokenUser);
        }

        model.addAttribute("pageFlag","我要分享");

        return "/myshare/mypublish";
    }

    /**
     * @Author Axu
     * @Description //TODO 添加文章数据
     * @Date 9:30 2019/3/14
     * @Param [artcile, model]
     * @return void
     **/
    @RequestMapping(value = "/addarticledata")
    public String addArticleData(Article article, Model model){

        Lable lable = new Lable();//文章标签

        Photo photo = new Photo();

        //获取当前的登录用户
        User tokenUser = userService.getTokenUser();

        //设置文章创建用户
        article.setUserUuid(tokenUser.getUserUuid());
        article.setUserName(tokenUser.getUserName());
        String artcileUuid = UUID.randomUUID().toString();//文章uuid
        article.setArticleUuid(artcileUuid);
        article.setArticleFlag("1");

        //判断是否为空


        photo.setArticleUuid(article.getArticleUuid());
        if (article.getPhotoUrl() != null){
            if (article.getPhotoUrl().size() > 0){
                int urllength = article.getPhotoUrl().size();
                while(urllength > 0){
                    photo.setPhotoUrl(article.getPhotoUrl().get(urllength-1));
                    photoService.addPhoto(photo);//保存图片url

                    urllength--;
                }
            }
        }

        myShareService.addArtcileData(article, lable);//保存文章数据

        if(!article.getArticlePublish().equals("") || article.getArticlePublish() != null){
            if(article.getArticlePublish().equals("发布")){
                model.addAttribute("msg","发布成功!");
            }if(article.getArticlePublish().equals("草稿")){
                model.addAttribute("msg","保存草稿成功!");
            }
        }else{
            model.addAttribute("msg","请选择是否保存!");
        }


        model.addAttribute("tokenUser", tokenUser);
        model.addAttribute("pageFlag","我要分享");
        return "/myshare/mypublish";
    }

    /**
     * 上传文件到七牛云存储 返回七牛云图片的储存地址
     * @param multipartFile
     * @return
     * @throws IOException
     */

    @PostMapping("/uploadImgQiniu")
    @ResponseBody
    public Map<String, String > uploadImgQiniu(@RequestParam("file") MultipartFile multipartFile, Model model) throws IOException {

        Map<String, String > result = new HashMap<>();

        FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
        String imgageath = "http://" + uploadQNImg(inputStream, UUID.randomUUID().toString()); //UUID.randomUUID()生成图片的随机名

        if("".equals(imgageath)){
            model.addAttribute("msg", "图片上传失败!");
        }

        result.put("url", imgageath);
        return result;
    }

    /**
     * 将图片上传到七牛云
     */
    private String uploadQNImg(FileInputStream file, String key) {

        ConstanQiniu constanQiniu = new ConstanQiniu();
//        constanQiniu.setAccessKey("ns2DeUmaI1V9r_rH0QfM2cDkjDZe78fRBJ1TZgmg");
//        constanQiniu.setSecretKey("v1l4tukOyWDfDoMlBh-BXfaMwj44VdtzZ-Qs-Q1J");
//        constanQiniu.setBucket("image");
//        constanQiniu.setPath("pouye26j3.bkt.clouddn.com");

        constanQiniu.setAccessKey("ns2DeUmaI1V9r_rH0QfM2cDkjDZe78fRBJ1TZgmg");
        constanQiniu.setSecretKey("v1l4tukOyWDfDoMlBh-BXfaMwj44VdtzZ-Qs-Q1J");
        constanQiniu.setBucket("image4");
        constanQiniu.setPath("pr2afkgy7.bkt.clouddn.com");

        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        // 其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // 生成上传凭证，然后准备上传

        try {
            Auth auth = Auth.create(constanQiniu.getAccessKey(), constanQiniu.getSecretKey());
            String upToken = auth.uploadToken(constanQiniu.getBucket());
            try {
                Response response = uploadManager.put(file, key, upToken, null, null);
                // 解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

                String returnPath = constanQiniu.getPath() + "/" + putRet.key;
                return returnPath;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


}
