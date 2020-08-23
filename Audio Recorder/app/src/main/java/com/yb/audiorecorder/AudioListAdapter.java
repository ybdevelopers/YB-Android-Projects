package com.yb.audiorecorder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;

public class AudioListAdapter extends RecyclerView.Adapter<AudioListAdapter.AudioViewHolder> {

    private File[] allFiles;
    private TotalTime totalTime;
    private onItemClick onItemClick;

    public AudioListAdapter(File[] allFiles, onItemClick onItemClick) {
        this.allFiles = allFiles;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_audio_list,parent,false);
        totalTime = new TotalTime();
        return new AudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioViewHolder holder, int position) {
        holder.tvFileName.setText(allFiles[position].getName());
        holder.tvDate.setText(totalTime.getTotalTime(allFiles[position].lastModified()));
    }

    @Override
    public int getItemCount() {
        return allFiles.length;
    }

    public class AudioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView ivPlay;
        private TextView tvFileName,tvDate;

        public AudioViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPlay = itemView.findViewById(R.id.iv_play);
            tvFileName = itemView.findViewById(R.id.tv_filename);
            tvDate = itemView.findViewById(R.id.tv_date);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClick.onClickListener(allFiles[getAdapterPosition()],getAdapterPosition());
        }
    }

    public interface onItemClick{
        void onClickListener(File file,int position);
    }
}
