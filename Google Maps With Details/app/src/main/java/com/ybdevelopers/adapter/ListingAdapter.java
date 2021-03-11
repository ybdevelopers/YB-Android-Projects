package com.ybdevelopers.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ybdevelopers.demoproject.R;
import com.ybdevelopers.model.Success;

import java.util.List;

public class ListingAdapter extends BaseQuickAdapter<Success, BaseViewHolder> {

    private Context context;

    public ListingAdapter(Context context, int layoutResId, List<Success> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(final BaseViewHolder holder, final Success item) {
        holder.setText(R.id.tvId, isEmptyString(item.getId().toString()) ? "N/A" : item.getId().toString());
        holder.setText(R.id.tvName, isEmptyString(item.getName()) ? "N/A" : item.getName());
        holder.setText(R.id.tvContact, isEmptyString(item.getContact()) ? "N/A" : item.getContact());
        holder.setText(R.id.tvCat, isEmptyString(item.getCategoryid().toString()) ? "N/A" : item.getCategoryid().toString());
        holder.setText(R.id.tvDesc, isEmptyString(item.getDescription()) ? "N/A" : item.getDescription());
        holder.setText(R.id.tvAddress, isEmptyString(item.getAddress()) ? "N/A" : item.getAddress());
        holder.setText(R.id.tvEmpCode, isEmptyString(item.getEmpcode()) ? "N/A" : item.getEmpcode());
        holder.addOnClickListener(R.id.mainLayout);
    }

    private static boolean isEmptyString(String myString) {
        return myString == null || myString.equals("") || myString.equalsIgnoreCase("null") || myString.trim().length() <= 0;
    }
}
