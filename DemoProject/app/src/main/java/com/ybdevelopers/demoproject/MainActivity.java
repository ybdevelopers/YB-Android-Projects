package com.ybdevelopers.demoproject;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ybdevelopers.demoproject.adapter.FeatureAdapter;
import com.ybdevelopers.demoproject.adapter.OtherFeatureAdapter;
import com.ybdevelopers.demoproject.adapter.StorageOptionAdapter;
import com.ybdevelopers.demoproject.databinding.ActivityMainBinding;
import com.ybdevelopers.demoproject.models.Exclusion;
import com.ybdevelopers.demoproject.models.Feature;
import com.ybdevelopers.demoproject.models.MobileListing;
import com.ybdevelopers.demoproject.retrofit.RetrofitClass;
import com.ybdevelopers.demoproject.retrofit.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements Callback {
    private ActivityMainBinding binding;
    Call getListing;
    public static Retrofit retrofitClient = RetrofitClass.getClient();
    public static RetrofitInterface retrofitInterface = retrofitClient.create(RetrofitInterface.class);
    private FeatureAdapter featureAdapter;
    private StorageOptionAdapter storageOptionAdapter;
    private OtherFeatureAdapter otherFeatureAdapter;
    private List<Feature> featureArrayList;
    private List<List<Exclusion>> exclusionArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getListing = retrofitInterface.getListing();
        getListing.enqueue(MainActivity.this);

    }

    @Override
    public void onResponse(Call call, Response response) {
        if (response.code() == 200) {
            MobileListing mobileListing = (MobileListing) response.body();
            featureArrayList = mobileListing.getFeatures();
            exclusionArrayList = mobileListing.getExclusions();
            binding.tvFirstName.setText(featureArrayList.get(0).getName());
            binding.tvSecondName.setText(featureArrayList.get(1).getName());
            binding.tvThirdName.setText(featureArrayList.get(2).getName());

            // for first recycler view
            featureAdapter = new FeatureAdapter(featureArrayList, exclusionArrayList,MainActivity.this);
            binding.rvFirstData.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
            binding.rvFirstData.setItemAnimator(new DefaultItemAnimator());
            binding.rvFirstData.setAdapter(featureAdapter);
            featureAdapter.setMobileClickListener(position -> Log.e("Position Data is:", featureArrayList.get(0).getOptions().get(position).getName()));

            //for second recycler view
            storageOptionAdapter = new StorageOptionAdapter(featureArrayList, MainActivity.this);
            binding.rvSecondData.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
            binding.rvSecondData.setItemAnimator(new DefaultItemAnimator());
            binding.rvSecondData.setAdapter(storageOptionAdapter);
            storageOptionAdapter.setMobileClickListener(position -> Log.e("Position Data is:", featureArrayList.get(1).getOptions().get(position).getName()));

            //for third recycler view
            otherFeatureAdapter = new OtherFeatureAdapter(featureArrayList, MainActivity.this);
            binding.rvThirdData.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
            binding.rvThirdData.setItemAnimator(new DefaultItemAnimator());
            binding.rvThirdData.setAdapter(otherFeatureAdapter);
            otherFeatureAdapter.setMobileClickListener(position -> Log.e("Position Data is:", featureArrayList.get(2).getOptions().get(position).getName()));
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        Log.e("Failed:", t.getMessage());
    }
}