package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class NewsDetailActivity extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        final News news = getIntent().getParcelableExtra("News");
        WebView webView = findViewById(R.id.webView);
        ProgressBar progressBar = findViewById(R.id.web_loading);
        webView.setWebViewClient(new MyBrowser());
        assert news != null;
        String link = news.getContent();
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        progressBar.setVisibility(View.VISIBLE);
        webView.loadUrl(link);
        progressBar.setVisibility(View.GONE);
        FloatingActionButton fab = findViewById(R.id.bookmark);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NewsDetailActivity.this, "This news has been bookmarked",
                        Toast.LENGTH_SHORT).show();
                String title = news.getTitle();
                String image = news.getImageUrl();
                String newsUrl = news.getContent();
                String source = news.getSource();
                String publishedDate = news.getPublishedDate();
            }
        });

    }
    private static class MyBrowser extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}