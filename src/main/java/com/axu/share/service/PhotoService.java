package com.axu.share.service;

import com.axu.share.pojo.Photo;

import java.util.List;

public interface PhotoService {

    /**
     * @Author Axu
     * @Description //TODO 保存图片
     * @Date 10:36 2019/3/26
     * @Param [photo]
     * @return void
     **/
    void addPhoto(Photo photo);

    /**
     * @Author Axu
     * @Description //TODO 搜索文章图片
     * @Date 22:02 2019/3/26
     * @Param []
     * @return java.util.List<com.axu.share.pojo.Photo>
     **/
    List<Photo> selectImg();
}
