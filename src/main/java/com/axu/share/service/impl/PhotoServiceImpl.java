package com.axu.share.service.impl;

import com.axu.share.dao.PhotoDao;
import com.axu.share.pojo.Photo;
import com.axu.share.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    PhotoDao photoDao;

    /**
     * @Author Axu
     * @Description //TODO 保存图片
     * @Date 10:37 2019/3/26
     * @Param [photo]
     * @return void
     **/
    @Override
    public void addPhoto(Photo photo) {
        photoDao.addPhoto(photo);
    }

    @Override
    public List<Photo> selectImg() {

        return photoDao.selectImg();
    }
}
