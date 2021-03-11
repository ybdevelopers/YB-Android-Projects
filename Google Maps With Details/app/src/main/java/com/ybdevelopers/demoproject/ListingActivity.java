package com.ybdevelopers.demoproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ybdevelopers.adapter.ListingAdapter;
import com.ybdevelopers.model.GetLocationData;
import com.ybdevelopers.model.Success;

public class ListingActivity extends AppCompatActivity {

    GetLocationData getLocationData;
    ListingAdapter listingAdapter;
    RecyclerView rvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        rvData = findViewById(R.id.rvData);
        getLocationData = getIntent().getExtras().getParcelable(getString(R.string.come_from));
        rvData.setLayoutManager(new LinearLayoutManager(this));
        listingAdapter = new ListingAdapter(this, R.layout.single_data_item, getLocationData.getSuccess());
        rvData.setAdapter(listingAdapter);
        rvData.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Success successData = listingAdapter.getData().get(position);
                createCustomAlert(successData);
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.mainLayout) {
                    Success successData = listingAdapter.getData().get(position);
                    createCustomAlert(successData);
                }
            }
        });
    }

    private void createCustomAlert(Success successData) {
        if (!((Activity) this).isFinishing()) {
            try {
                AlertDialog.Builder ab = new AlertDialog.Builder(this, R.style.CustomAlertDialogStyle);
                LayoutInflater inflater = ((Activity) this).getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.single_data_item, null);
                ab.setView(dialogView);
                ab.setCancelable(false);
                TextView tvId = dialogView.findViewById(R.id.tvId);
                TextView tvName = dialogView.findViewById(R.id.tvName);
                TextView tvContact = dialogView.findViewById(R.id.tvContact);
                TextView tvCat = dialogView.findViewById(R.id.tvCat);
                TextView tvDesc = dialogView.findViewById(R.id.tvDesc);
                TextView tvAddress = dialogView.findViewById(R.id.tvAddress);
                TextView tvEmpCode = dialogView.findViewById(R.id.tvEmpCode);
                TextView tvSelected = dialogView.findViewById(R.id.tvSelected);
                ImageView ivCross = dialogView.findViewById(R.id.ivCross);
                ivCross.setVisibility(View.VISIBLE);
                tvSelected.setVisibility(View.VISIBLE);
                tvSelected.setText(getString(R.string.you_had_clicked,successData.getName()));
                tvId.setText(successData.getId().toString());
                tvName.setText(successData.getName());
                tvContact.setText(successData.getContact());
                tvCat.setText(successData.getCategoryid().toString());
                tvDesc.setText(successData.getDescription());
                tvAddress.setText(successData.getAddress());
                tvEmpCode.setText(successData.getEmpcode());
                AlertDialog dialog = ab.create();
                ivCross.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();

            } catch (Exception e) {
                Log.e("Error:",e.getMessage());
                e.printStackTrace();
            }
        }
    }
}