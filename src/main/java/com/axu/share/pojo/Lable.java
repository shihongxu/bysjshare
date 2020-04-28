package com.axu.share.pojo;

/**
 * @Author Axu
 * @Description //TODO 文章标签
 * @Date 10:32 2019/3/14
 * @Param 
 * @return 
 **/
public class Lable {

    private String articleUuid;//文章uuid

    private String lableName;//文章标签

    public String getLableName() {
        return lableName;
    }

    public void setLableName(String lableName) {
        this.lableName = lableName;
    }

    public String getArticleUuid() {
        return articleUuid;
    }

    public void setArticleUuid(String articleUuid) {
        this.articleUuid = articleUuid;
    }
}
