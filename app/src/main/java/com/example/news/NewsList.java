package com.example.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsList {
    @SerializedName("articles")
    @Expose
    private ArrayList<NewsResult> mArticle;

    public NewsList(ArrayList<NewsResult> article) {
        mArticle = article;
    }

    public ArrayList<NewsResult> getArticle() {
        return mArticle;
    }
}
