package com.ybdevelopers.demoproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ybdevelopers.demoproject.R;
import com.ybdevelopers.demoproject.models.Feature;

import java.util.List;

public class OtherFeatureAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<Feature> featureList;
    private OtherFeatureClickListener otherFeatureClickListener;

    public OtherFeatureAdapter(List<Feature> featureLists, Context context,OtherFeatureClickListener otherFeatureClickListener) {
        this.otherFeatureClickListener = otherFeatureClickListener;
        this.featureList = featureLists;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_listing, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        viewHolder.ivPhoto.setOnClickListener(this);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.ivPhoto.setTag(holder);
        holder.tvName.setText(featureList.get(2).getOptions().get(position).getName());
        Glide.with(context).load(featureList.get(2).getOptions().get(position).getIcon()).centerCrop().into(holder.ivPhoto);
    }

    @Override
    public int getItemCount() {
        return featureList.get(2).getOptions().size();
    }

    @Override
    public void onClick(View v) {
        try {
            int viewId = v.getId();
            ViewHolder viewHolder = (ViewHolder) v.getTag();
            if (viewId == R.id.iv_photo) {
                if (otherFeatureClickListener != null) {
                    otherFeatureClickListener.onOtherFeatureClickListener(viewHolder.getAdapterPosition());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;
        TextView tvName;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            ivPhoto = itemView.findViewById(R.id.iv_photo);
        }
    }

    public interface OtherFeatureClickListener {
        void onOtherFeatureClickListener(int position);
    }

//    public void setMobileClickListener(OtherFeatureClickListener otherFeatureClickListener) {
//        this.otherFeatureClickListener = otherFeatureClickListener;
//    }

}
