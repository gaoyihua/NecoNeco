package com.gary.neconeco.pojo;

public class Essay {
    private int id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章评论数
     */
    private String comment;
    /**
     * 文章图片
     */
    private String pic;
    /**
     * 文章url
     */
    private String url;

    public Essay(String title, String comment, String url) {
        this.title = title;
        this.comment = comment;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
