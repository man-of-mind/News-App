package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchResultActivity extends AppCompatActivity {

    private TextView mInternetError;
    private RecyclerView mRecyclerView;
    private ProgressBar mLoadingProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        Intent intent = getIntent();
        final String query = intent.getStringExtra("query");
        final String category = intent.getStringExtra("category");
        String country = "ng";
        String endpoint = "top-headlines";
        mInternetError = findViewById(R.id.news_error);
        mLoadingProgress = findViewById(R.id.news_loading);
        mRecyclerView = findViewById(R.id.news_recycler);
        LinearLayoutManager newsLayoutManager = new LinearLayoutManager(this, LinearLayoutManager
                .VERTICAL, false);
        mRecyclerView.setLayoutManager(newsLayoutManager);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(JsonPlaceHolder.BASE_API)
                .addConverterFactory(GsonConverterFactory.create()).build();
        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        if(!query.equals("") && category.equals("")){
            Call<NewsList> call = jsonPlaceHolder.getEverything(query, "en", JsonPlaceHolder.API_KEY);
            mLoadingProgress.setVisibility(View.VISIBLE);
            call.enqueue(new Callback<NewsList>() {
                @Override
                public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                    mLoadingProgress.setVisibility(View.GONE);
                    mInternetError.setVisibility(View.INVISIBLE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    if(response.isSuccessful()){
                        NewsList newsList = response.body();
                        assert newsList != null;
                        List<NewsResult> newsResults = newsList.getArticle();
                        ArrayList<News> news = new ArrayList<>();
                        for (int i = 0; i < newsResults.size(); i++){
                            NewsResult newsResult = newsResults.get(i);
                            String title = newsResult.getTitle();
                            String image = newsResult.getImage();
                            String publishedAt = newsResult.getPublishedAt();
                            Source source = newsResult.getSource();
                            String author = source.getName();
                            String url = newsResult.getUrl();
                            News news1 = new News(title, image, author, url, publishedAt);
                            news.add(news1);
                        }
                        NewsAdapter adapter = new NewsAdapter(SearchResultActivity.this, news, mRecyclerView);
                        mRecyclerView.setAdapter(adapter);
                    }
                    else {
                        Log.e(SearchResultActivity.class.getSimpleName(), String.valueOf(response.code()));
                    }
                }

                @Override
                public void onFailure(Call<NewsList> call, Throwable t) {
                    mLoadingProgress.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.INVISIBLE);
                    mInternetError.setVisibility(View.VISIBLE);
                    Log.e(SearchResultActivity.class.getSimpleName(), Objects.requireNonNull(t.getMessage()));
                    Toast.makeText(SearchResultActivity.this, "Error retrieving data from the internet", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if (query.equals("") && !category.equals("")){
            Call<NewsList> call = jsonPlaceHolder.getEverything(category, "en", JsonPlaceHolder.API_KEY);
            mLoadingProgress.setVisibility(View.VISIBLE);
            call.enqueue(new Callback<NewsList>() {
                @Override
                public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                    mLoadingProgress.setVisibility(View.GONE);
                    mInternetError.setVisibility(View.INVISIBLE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    if(response.isSuccessful()){
                        NewsList newsList = response.body();
                        assert newsList != null;
                        List<NewsResult> newsResults = newsList.getArticle();
                        ArrayList<News> news = new ArrayList<>();
                        for (int i = 0; i < newsResults.size(); i++){
                            NewsResult newsResult = newsResults.get(i);
                            String title = newsResult.getTitle();
                            String image = newsResult.getImage();
                            String publishedAt = newsResult.getPublishedAt();
                            Source source = newsResult.getSource();
                            String author = source.getName();
                            String url = newsResult.getUrl();
                            News news1 = new News(title, image, author, url, publishedAt);
                            news.add(news1);
                        }
                        NewsAdapter adapter = new NewsAdapter(SearchResultActivity.this, news, mRecyclerView);
                        mRecyclerView.setAdapter(adapter);
                    }
                    else {
                        Log.e(SearchResultActivity.class.getSimpleName(), String.valueOf(response.code()));
                    }
                }

                @Override
                public void onFailure(Call<NewsList> call, Throwable t) {
                    mLoadingProgress.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.INVISIBLE);
                    mInternetError.setVisibility(View.VISIBLE);
                    Log.e(SearchResultActivity.class.getSimpleName(), Objects.requireNonNull(t.getMessage()));
                    Toast.makeText(SearchResultActivity.this, "Error retrieving data from the internet", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            Call<NewsList> call = jsonPlaceHolder.getCategoryNews("top-headlines", query, country, category, JsonPlaceHolder.API_KEY);
            mLoadingProgress.setVisibility(View.VISIBLE);
            call.enqueue(new Callback<NewsList>() {
                @Override
                public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                    mLoadingProgress.setVisibility(View.GONE);
                    mInternetError.setVisibility(View.INVISIBLE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    if(response.isSuccessful()){
                        NewsList newsList = response.body();
                        assert newsList != null;
                        List<NewsResult> newsResults = newsList.getArticle();
                        ArrayList<News> news = new ArrayList<>();
                        if(newsResults.size() < 1){
                            Toast.makeText(SearchResultActivity.this, "Enter a valid category", Toast.LENGTH_LONG).show();
                        }
                        else {
                            for (int i = 0; i < newsResults.size(); i++) {
                                NewsResult newsResult = newsResults.get(i);
                                String title = newsResult.getTitle();
                                String image = newsResult.getImage();
                                String publishedAt = newsResult.getPublishedAt();
                                Source source = newsResult.getSource();
                                String author = source.getName();
                                String url = newsResult.getUrl();
                                News news1 = new News(title, image, author, url, publishedAt);
                                news.add(news1);
                            }
                            NewsAdapter adapter = new NewsAdapter(SearchResultActivity.this, news, mRecyclerView);
                            mRecyclerView.setAdapter(adapter);
                        }
                    }
                    else {
                        Log.e(SearchResultActivity.class.getSimpleName(), String.valueOf(response.code()));
                    }
                }

                @Override
                public void onFailure(Call<NewsList> call, Throwable t) {
                    mLoadingProgress.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.INVISIBLE);
                    mInternetError.setVisibility(View.VISIBLE);
                    Log.e(SearchResultActivity.class.getSimpleName(), Objects.requireNonNull(t.getMessage()));
                    Toast.makeText(SearchResultActivity.this, "Error retrieving data from the internet", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}