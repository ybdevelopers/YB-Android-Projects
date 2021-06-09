package com.ybdevelopers.mobilefeature.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.squareup.okhttp.Callback
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import com.ybdevelopers.mobilefeature.R
import com.ybdevelopers.mobilefeature.databinding.FragmentListingBinding
import com.ybdevelopers.mobilefeature.model.MobileFeature
import com.ybdevelopers.mobilefeature.retrofit.ApiInterface
import com.ybdevelopers.mobilefeature.retrofit.RetrofitClass
import retrofit2.Call
import java.io.IOException


class ListingFragment : Fragment(), Callback {
    private lateinit var binding: FragmentListingBinding
    var listingCall: Call<*>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_listing, container, false)
        getListingCall()
        return binding.root
    }

    private fun getListingCall() {
        val retrofitInterface: ApiInterface =
            RetrofitClass.getClient()!!.create(ApiInterface::class.java)
        listingCall= retrofitInterface.getListing()!!
        listingCall.enqueue(object : Callback<*>, retrofit2.Callback<MobileFeature?> {
            override fun onResponse(call: Call<MobileFeature>?, response: Response<MobileFeature>?) {

                if(response?.body() != null)
                    recyclerAdapter.setMovieListItems(response.body()!!)
            }

            override fun onFailure(call: Call<MobileFeature>?, t: Throwable?) {

            }

            override fun onResponse(
                call: Call<MobileFeature?>,
                response: retrofit2.Response<MobileFeature?>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<MobileFeature?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onFailure(request: Request?, e: IOException?) {
        TODO("Not yet implemented")
    }

    override fun onResponse(response: Response?) {
        TODO("Not yet implemented")
    }
}