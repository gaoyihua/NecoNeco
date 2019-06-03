package com.gary.neconeco.pojo;

public class Video {
    private int id;
    private String name;
    private String url;
    private String description;
    private int imageId;
    private String category;

    public Video(int id, String name, String url, String description, int imageId, String category) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.description = description;
        this.imageId = imageId;
        this.category = category;
    }

    public Video(String name, String url, int imageId) {
        this.name = name;
        this.url = url;
        this.imageId = imageId;
    }

    public Video(String name, String url, String description, int imageId) {
        this.name = name;
        this.url = url;
        this.description = description;
        this.imageId = imageId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int iamgeId) {
        this.imageId = iamgeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
