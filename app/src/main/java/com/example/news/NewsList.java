package com.example.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class NewsList {
    @SerializedName("articles")
    @Expose
    private ArrayList<NewsResult> mArticle;

    public ArrayList<NewsResult> getArticle() {
        return mArticle;
    }
}
