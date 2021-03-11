package com.ybdevelopers.demoproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ybdevelopers.model.GetLocationData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    APIInterface apiInterface;
    Button btnClick;
    GetLocationData getLocationData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        apiInterface = ApiClient.createService(APIInterface.class);
        btnClick = findViewById(R.id.btnClick);
        btnClick.setOnClickListener(this);
        getAllLocationData();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void getAllLocationData() {
        try {
            Call<GetLocationData> call = apiInterface.getAllLocationAPI();
            call.enqueue(new Callback<GetLocationData>() {
                @Override
                public void onResponse(Call<GetLocationData> call, Response<GetLocationData> response) {
                    Log.e("Data:", response.body().toString());
                    getLocationData = new GetLocationData();
                    getLocationData.setLocation(response.body().getLocation());
                    getLocationData.setSuccess(response.body().getSuccess());

                    for (int i = 0; i < getLocationData.getLocation().size(); i++) {
                        createMarker(getLocationData.getLocation().get(i).getLat(), getLocationData.getLocation().get(i).getLongg(),
                                String.valueOf(getLocationData.getLocation().get(i).getId()),getLocationData.getLocation().get(i).getCode(),
                                        getLocationData.getLocation().get(i).getCreatedAt());
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(23.7311, 76.8893), 4.0f));
                    btnClick.setVisibility(View.VISIBLE);
                }

                @Override
                public void onFailure(Call<GetLocationData> call, Throwable t) {
                    Toast.makeText(MapsActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.e("TAG", e.getMessage());
        }

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    protected Marker createMarker(double latitude, double longitude, String id,String code,String createdAt) {
        return mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .title(latitude+", "+longitude)
                .anchor(0.5f, 0.5f));

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnClick){
            Intent intent = new Intent(this, ListingActivity.class);
            intent.putExtra(getString(R.string.come_from),getLocationData);
            startActivity(intent);
        }
    }
}