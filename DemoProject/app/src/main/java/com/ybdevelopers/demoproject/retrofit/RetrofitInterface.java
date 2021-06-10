package com.ybdevelopers.demoproject.retrofit;



import com.ybdevelopers.demoproject.models.MobileListing;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    @GET("db")
    Call<MobileListing> getListing();

}