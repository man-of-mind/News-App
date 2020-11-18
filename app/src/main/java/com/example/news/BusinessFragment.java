package com.example.news;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BusinessFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusinessFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView mError;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    public BusinessFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BusinessFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BusinessFragment newInstance(String param1, String param2) {
        BusinessFragment fragment = new BusinessFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mError = rootView.findViewById(R.id.news_error);
        mRecyclerView = rootView.findViewById(R.id.news_recycler);
        mProgressBar = rootView.findViewById(R.id.news_loading);
        String category = "business";
        String country = "ng";
        String endpoint = "top-headlines";
        LinearLayoutManager newsLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager
                .VERTICAL, false);
        mRecyclerView.setLayoutManager(newsLayoutManager);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(JsonPlaceHolder.BASE_API).addConverterFactory(
                GsonConverterFactory.create()).build();
        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        Call<NewsList> call = jsonPlaceHolder.getBusinessNews(endpoint, null, country, category,
                JsonPlaceHolder.API_KEY);
        mProgressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                mProgressBar.setVisibility(View.GONE);
                if(response.isSuccessful()){
                    mError.setVisibility(View.INVISIBLE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    NewsList article = response.body();
                    List<NewsResult> newsResults = article.getArticle();
                    ArrayList<News> news = new ArrayList<>();
                    for (int i = 0; i < newsResults.size(); i++){
                        NewsResult newsResult = newsResults.get(i);
                        String title = newsResult.getTitle();
                        String image = newsResult.getImage();
                        String content = newsResult.getUrl();
                        Source source= newsResult.getSource();
                        String author = source.getName();
                        String publishedAt = newsResult.getPublishedAt();
                        News news1 = new News(title, image, author, content, publishedAt);
                        news.add(news1);
                    }
                    NewsAdapter adapter = new NewsAdapter(getContext(), news, mRecyclerView);
                    mRecyclerView.setAdapter(adapter);
                }
                else {
                    Log.e(BusinessFragment.class.getSimpleName(), String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.INVISIBLE);
                mError.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Error retrieving data from the internet", Toast.LENGTH_SHORT).show();
            }
        });

        return  rootView;
    }
}