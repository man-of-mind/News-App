package com.example.news;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolder {
    String BASE_API = "https://newsapi.org/v2/";

    @GET("{endpoint}")
    Call<NewsList> getSportNews(@Path("endpoint") String endpoint,
                                       @Query("q") String query,
                                       @Query("country") String country,
                                       @Query("category") String category,
                                       @Query("apikey") String apiKey);

    @GET("{endpoint}")
    Call<Post> getBusinessNews(@Path("endpoint") String endpoint,
                                           @Query("q") String query,
                                           @Query("country") String country,
                                           @Query("category") String category,
                                           @Query("apikey") String apiKey);

    @GET("{endpoint}")
    Call<Post> getEntertainmentNews(@Path("endpoint") String endpoint,
                                           @Query("q") String query,
                                           @Query("country") String country,
                                           @Query("category") String category,
                                           @Query("apikey") String apiKey);

    @GET("{endpoint}")
    Call<Post> getTopNews(@Path("endpoint") String endpoint,
                                           @Query("q") String query,
                                           @Query("country") String country,
                                           @Query("apikey") String apiKey);

}
