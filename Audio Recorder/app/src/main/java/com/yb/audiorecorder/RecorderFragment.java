package com.yb.audiorecorder;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by YB Developers on 19-08-2020
 */
public class RecorderFragment extends Fragment implements View.OnClickListener {

    private NavController navController;
    private ImageButton mList;
    private ImageButton mRecordBtn;
    private boolean isRecording = false;
    private String recordPermission = Manifest.permission.RECORD_AUDIO;
    private int PERMISSION_CODE = 26;
    private MediaRecorder mediaRecorder;
    private String mRecordFile;
    private Chronometer mTimer;
    private TextView mFileName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recorder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        mList = view.findViewById(R.id.iv_list);
        mRecordBtn = view.findViewById(R.id.iv_recording);
        mTimer = view.findViewById(R.id.recorder_btn);
        mFileName = view.findViewById(R.id.tvTitle);
        mList.setOnClickListener(this);
        mRecordBtn.setOnClickListener(this);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_list:
                if (isRecording) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            navController.navigate(R.id.action_recorderFragment_to_audioListFragment);
                            isRecording = false;
                        }
                    });
                    alertDialog.setNegativeButton("NO", null);
                    alertDialog.setTitle("Audio is still recording");
                    alertDialog.setMessage("Are you sure, you want to stop the recording?");
                    alertDialog.create().show();
                } else
                    navController.navigate(R.id.action_recorderFragment_to_audioListFragment);
                break;
            case R.id.iv_recording:
                if (isRecording) {
                    // TODO Stop Recording Called
                    stopRecording();
                    mRecordBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_microphone, null));
                    isRecording = false;
                } else {
                    // TODO Start Recording Called
                    if (checkPermissions()) {
                        startRecording();
                        mRecordBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_red_microphone, null));
                        isRecording = true;
                    }

                }
        }
    }

    private void startRecording() {
        mTimer.setBase(SystemClock.elapsedRealtime());
        mTimer.start();
        String recordPath = getActivity().getExternalFilesDir("/").getAbsolutePath();
        SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss", Locale.CANADA);
        Date date = new Date();
        mRecordFile = "YbAudio(" + format.format(date) +")"+ ".3gp";

        mFileName.setText(getResources().getString(R.string.recording_file_name_is) + mRecordFile);

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(recordPath + "/" + mRecordFile);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mediaRecorder.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mediaRecorder.start();
    }

    private void stopRecording() {
        mTimer.stop();
        mFileName.setText(getResources().getString(R.string.file_saved));
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
    }

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{recordPermission}, PERMISSION_CODE);
            return false;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (isRecording)
            stopRecording();
    }
}