package com.ybdevelopers.demoproject;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    //public static final String API_BASE_URL = "https://api.my-watchman.com/api/";
    public static final String API_BASE_URL = "http://aryu.co.in/";

    private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    private static Interceptor logging = interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }
}
