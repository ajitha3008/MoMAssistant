package com.braingalore.momassistant.fragments;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.braingalore.momassistant.R;
import com.skyfishjy.library.RippleBackground;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by s92 on 11/27/2017.
 */

public class RecordFragment extends Fragment {

    private boolean isRecordStarted = false;

    private TextView textView;

    private LinearLayout linearLayout;

    private ImageButton stopButton;

    private RippleBackground rippleBackground;

    private MediaRecorder recorder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        View v = inflater.inflate(R.layout.record_fragment, null);
        final ImageView imgView = (ImageView) v.findViewById(R.id.mom_image);
        rippleBackground = (RippleBackground) v.findViewById(R.id.content);
        textView = v.findViewById(R.id.textView);
        stopButton = v.findViewById(R.id.stop_button);
        linearLayout = v.findViewById(R.id.button_layout);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopRecording();
            }
        });
        imgView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        if (!isRecordStarted) {
                            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imgView.getLayoutParams();
                            layoutParams.setMargins(110, 110, 110, 40);
                            imgView.setLayoutParams(layoutParams);
                            isRecordStarted = true;
                            startRecording();
                            textView.setVisibility(View.INVISIBLE);
                            linearLayout.setVisibility(View.VISIBLE);
                            rippleBackground.startRippleAnimation();
                        }
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imgView.getLayoutParams();
                        layoutParams.setMargins(100, 100, 100, 30);
                        imgView.setLayoutParams(layoutParams);
                        break;
                    }
                }
                return true;
            }
        });
        return v;
    }

    private void startRecording() {
        SimpleDateFormat timeStampFormat = new SimpleDateFormat(
                "yyyy-MM-dd-HH.mm.ss");
        String fileName = "audio_" + timeStampFormat.format(new Date())
                + ".mp4";
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/" + getResources().getString(R.string.app_name) + "/" + fileName);
        try {
            recorder.prepare();
            recorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopRecording() {
        if (recorder != null) {
            recorder.stop();     // stop recording
            recorder.reset();    // set state to idle
            recorder.release();  // release resources back to the system
            recorder = null;
        }
        isRecordStarted = false;
        textView.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.INVISIBLE);
        rippleBackground.stopRippleAnimation();
    }
}
