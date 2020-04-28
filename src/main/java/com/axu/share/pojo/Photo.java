package com.axu.share.pojo;

/**
 * @Author Axu
 * @Description //TODO 图片模型
 * @Date 9:44 2019/3/26
 * @Param
 * @return
 **/
public class Photo {

    private String userUuid;//用户useruuid

    private String articleUuid;//文章uuid

    private String photoUrl;//图片地址

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getArticleUuid() {
        return articleUuid;
    }

    public void setArticleUuid(String articleUuid) {
        this.articleUuid = articleUuid;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
