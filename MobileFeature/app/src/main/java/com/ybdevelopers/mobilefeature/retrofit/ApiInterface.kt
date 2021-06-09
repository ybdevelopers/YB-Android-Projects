package com.ybdevelopers.mobilefeature.retrofit

import com.ybdevelopers.mobilefeature.model.MobileFeature
import retrofit2.Call
import retrofit2.http.GET

open interface ApiInterface {
    @GET("db")
    fun getListing(): Call<MobileFeature?>?
}