package com.shajib.imageuploadtoserver.model;

import com.google.gson.annotations.SerializedName;

public class ImageUpload {

    //model class

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("desc")
    private String description;

    @SerializedName("image_url")
    private String imageUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
