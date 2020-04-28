package com.axu.share.util.QiniuUtil;

import com.qiniu.util.Auth;

import java.util.UUID;

/**
 * @Author Axu
 * @Description //TODO 七牛云的工具类
 * @Date 16:12 2019/3/24
 * @Param
 * @return
 **/
public class QiniuUtil {

    /**
     * @Author Axu
     * @Description //TODO 获取七牛云的上传凭证
     * @Date 16:11 2019/3/24
     * @Param []
     * @return java.lang.String
     **/
    public static String getQiniuToken(){
        //测试七牛云上传
        //第一步：获取上传凭证token
        String accessKey = "ns2DeUmaI1V9r_rH0QfM2cDkjDZe78fRBJ1TZgmg";
        String secretKey = "v1l4tukOyWDfDoMlBh-BXfaMwj44VdtzZ-Qs-Q1J";
        String bucket = "image4";//空间名字
//        String key = UUID.randomUUID().toString();//生成一个随机字符串，因为七牛云需要保证文件名不出重复
        Auth auth = Auth.create(accessKey, secretKey);
//        String upToken = auth.uploadToken(bucket, key);
        String upToken = auth.uploadToken(bucket);
        System.out.println(upToken);

        return upToken;
    }
}
