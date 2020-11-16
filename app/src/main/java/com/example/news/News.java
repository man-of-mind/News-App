package com.example.news;

public class News {
    private String mTitle;
    private String mImageUrl;
    private String mContent;
    private String mSource;
    private String mPublishedDate;

    public News(String title, String imageUrl, String source, String content, String publishedDate){
        this.mTitle = title;
        this.mImageUrl = imageUrl;
        this.mSource = source;
        this.mContent= content;
        this.mPublishedDate = publishedDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getImageUrl(){
        return mImageUrl;
    }

    public String getContent() {
        return mContent;
    }

    public String getPublishedDate() {
        return mPublishedDate;
    }

    public String getSource() {
        return mSource;
    }
}
