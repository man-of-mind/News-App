package com.example.news;

import com.google.gson.annotations.SerializedName;

public class NewsResult {
    private String title;
    private Source source;
    @SerializedName("urlToImage")
    private String image;
    private String publishedAt;
    private String url;

    public NewsResult(String title, Source source, String image, String publishedAt, String url) {
        this.title = title;
        this.source = source;
        this.image = image;
        this.publishedAt = publishedAt;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public Source getSource(){
        return source;
    }

    public String getImage() {
        return image;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getUrl(){
        return url;
    }
}
