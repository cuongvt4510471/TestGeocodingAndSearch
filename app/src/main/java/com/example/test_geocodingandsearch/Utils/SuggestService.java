package com.example.test_geocodingandsearch.Utils;

import com.example.test_geocodingandsearch.Model.Data;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SuggestService {

    Gson gson = new GsonBuilder().create();

    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient okHttp = new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .writeTimeout(1, TimeUnit.SECONDS)
            .build();
    SuggestService suggestService = new Retrofit.Builder()
            .baseUrl("https://autosuggest.search.hereapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttp)
            .build()
            .create(SuggestService.class);

    @GET("autosuggest")
    Call<Data> getList(@Query("q") String q,
                             @Query("at") String at,
                             @Query("apiKey") String apiKey);
}
