package com.ybdevelopers.demoproject;


import com.ybdevelopers.model.GetLocationData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("tracking/viewreport")
    Call<GetLocationData> getAllLocationAPI();

}
