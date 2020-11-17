package com.example.news;

import com.google.gson.annotations.SerializedName;

public class NewsResult {
    private String title;
    private Source source;
    private String content;
    @SerializedName("urlToImage")
    private String image;
    private String publishedAt;
    private String url;

    public NewsResult(String title, Source source, String content, String image, String publishedAt, String url) {
        this.title = title;
        this.source = source;
        this.content = content;
        this.image = image;
        this.publishedAt = publishedAt;
        this.url = url;
    }

    public String getContent() {
        return content;
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
