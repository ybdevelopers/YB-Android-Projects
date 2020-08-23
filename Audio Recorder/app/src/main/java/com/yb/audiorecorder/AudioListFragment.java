package com.yb.audiorecorder;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.File;
import java.io.IOException;

/**
 * Create by YB Develoers on 19-08-2020
 */
public class AudioListFragment extends Fragment implements AudioListAdapter.onItemClick {

    private LinearLayout mPlayerSheet;
    private BottomSheetBehavior bottomSheetBehavior;
    private RecyclerView mAudioList;
    private File[] allFiles;
    private AudioListAdapter mAudioListAdapter;
    private MediaPlayer mediaPlayer = null;
    private boolean isPlaying = false;
    private File fileForPlay = null;
    private View emptyState;

    //UI Components
    private ImageButton ivPlay, ivBackward, ivForward;
    private TextView tvHeader, playerFileName;
    private SeekBar seekBarPlayer;
    private Handler seekBarHandler;
    private Runnable updateSeekBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_audio_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPlayerSheet = view.findViewById(R.id.player_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(mPlayerSheet);
        mAudioList = view.findViewById(R.id.recycler_audio_list);
        emptyState = view.findViewById(R.id.empty_state);

        ivPlay = view.findViewById(R.id.iv_play);
        ivBackward = view.findViewById(R.id.iv_backward);
        ivForward = view.findViewById(R.id.iv_forward);
        tvHeader = view.findViewById(R.id.tv_player_header_title);
        playerFileName = view.findViewById(R.id.tv_file_name);

        seekBarPlayer = view.findViewById(R.id.sb_player);
        seekBarPlayer.setVisibility(View.GONE);
        String path = getActivity().getExternalFilesDir("/").getAbsolutePath();
        File directory = new File(path);
        allFiles = directory.listFiles();

        if (allFiles.length>0){
            emptyState.setVisibility(View.GONE);
            mAudioList.setVisibility(View.VISIBLE);
        }else {
            emptyState.setVisibility(View.VISIBLE);
            mAudioList.setVisibility(View.GONE);
        }
        mAudioListAdapter = new AudioListAdapter(allFiles, this);
        mAudioList.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mAudioList.setLayoutManager(mLayoutManager);
        mAudioList.setAdapter(mAudioListAdapter);
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        ivPlay.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if (isPlaying) {
                    pauseAudio();
                } else {
                    if (fileForPlay != null)
                        resumeAudio();
                }
            }
        });

        ivBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fileForPlay != null)
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 2000);
            }
        });

        ivForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fileForPlay != null)
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 2000);
            }
        });

        seekBarPlayer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                pauseAudio();
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                mediaPlayer.seekTo(progress);
                resumeAudio();
            }
        });
    }

    @Override
    public void onClickListener(File file, int position) {
        fileForPlay = file;
        if (isPlaying) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                stopAudio();
                playAudio(fileForPlay);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                playAudio(fileForPlay);
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void playAudio(File fileForPlay) {
        //Play Audio
        mediaPlayer = new MediaPlayer();
        seekBarPlayer.setVisibility(View.VISIBLE);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        try {
            mediaPlayer.setDataSource(fileForPlay.getAbsolutePath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ivPlay.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_pause, null));
        }
        playerFileName.setText(fileForPlay.getName());
        tvHeader.setText(getResources().getString(R.string.playing));
        isPlaying = true;
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stopAudio();
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                tvHeader.setText(getResources().getString(R.string.finished));
            }
        });
        seekBarPlayer.setMax(mediaPlayer.getDuration());
        seekBarHandler = new Handler();
        updateRunnable();
        seekBarHandler.postDelayed(updateSeekBar, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void pauseAudio() {
        mediaPlayer.pause();
        ivPlay.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_play, null));
        isPlaying = false;
        seekBarHandler.removeCallbacks(updateSeekBar);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void resumeAudio() {
        mediaPlayer.start();
        ivPlay.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_pause, null));
        isPlaying = true;
        updateRunnable();
        seekBarHandler.postDelayed(updateSeekBar, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void stopAudio() {
        //Stop Audio
        ivPlay.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_play, null));
        tvHeader.setText(getResources().getString(R.string.stopped));
        isPlaying = false;
        mediaPlayer.stop();
        seekBarHandler.removeCallbacks(updateSeekBar);
    }

    private void updateRunnable() {
        updateSeekBar = new Runnable() {
            @Override
            public void run() {
                seekBarPlayer.setProgress(mediaPlayer.getCurrentPosition());
                seekBarHandler.postDelayed(this, 500);
            }
        };
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onStop() {
        super.onStop();
        if (isPlaying)
            stopAudio();
    }
}