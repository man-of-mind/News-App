package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;


import com.example.news.databinding.ActivityNewsDetailBinding;


public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        News news = getIntent().getParcelableExtra("News");
        ActivityNewsDetailBinding activityNewsDetailBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_news_detail);
        activityNewsDetailBinding.setNews(news);

    }
}