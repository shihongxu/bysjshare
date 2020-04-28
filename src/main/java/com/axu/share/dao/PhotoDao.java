package com.axu.share.dao;

import com.axu.share.pojo.Photo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Axu
 * @Description //TODO
 * @Date 10:25 2019/3/26
 * @Param
 * @return
 **/
@Repository
public interface PhotoDao {

    /**
     * @Author Axu
     * @Description //TODO 保存图片地址
     * @Date 10:26 2019/3/26
     * @Param [photo]
     * @return void
     **/
    void addPhoto(Photo photo);

    /**
     * @Author Axu
     * @Description //TODO 搜索文章图片 随机获取5条数据
     * @Date 22:04 2019/3/26
     * @Param []
     * @return java.util.List<com.axu.share.pojo.Photo>
     **/
    List<Photo> selectImg();
}
