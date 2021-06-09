package com.ybdevelopers.mobilefeature.retrofit

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class RetrofitClass {
    companion object{
        private val BASE_URL = "http://codeoceaninfotech.us/FamousPropertyApi/"
        private var retrofit: Retrofit? = null
        open fun getClient(): Retrofit? {
            if (retrofit == null) {
                val logging = HttpLoggingInterceptor()
                // set your desired log level
                Log.e("Base Url :", BASE_URL)
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build()
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL).client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}