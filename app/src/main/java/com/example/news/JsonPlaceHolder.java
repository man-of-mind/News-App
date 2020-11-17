package com.example.news;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolder {
    String BASE_API = "https://newsapi.org/v2/";
    String API_KEY = "ed61cc66f42d4fc484e66103d605ad62";

    @GET("{endpoint}")
    Call<NewsList> getSportNews(@Path("endpoint") String endpoint,
                                       @Query("q") String query,
                                       @Query("country") String country,
                                       @Query("category") String category,
                                       @Query("apikey") String apiKey);

    @GET("{endpoint}")
    Call<NewsList> getBusinessNews(@Path("endpoint") String endpoint,
                                           @Query("q") String query,
                                           @Query("country") String country,
                                           @Query("category") String category,
                                           @Query("apikey") String apiKey);

    @GET("{endpoint}")
    Call<NewsList> getEntertainmentNews(@Path("endpoint") String endpoint,
                                           @Query("q") String query,
                                           @Query("country") String country,
                                           @Query("category") String category,
                                           @Query("apikey") String apiKey);

    @GET("{endpoint}")
    Call<NewsList> getTopNews(@Path("endpoint") String endpoint,
                                           @Query("q") String query,
                                           @Query("country") String country,
                                           @Query("apikey") String apiKey);

}
