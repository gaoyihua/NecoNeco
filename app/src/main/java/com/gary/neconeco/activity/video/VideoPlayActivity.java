package com.gary.neconeco.activity.video;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;
import android.widget.MediaController;

import com.gary.neconeco.R;

public class VideoPlayActivity extends AppCompatActivity {
    VideoView mVideoNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        mVideoNet = findViewById(R.id.videoTest);
        initNetVideo();
    }

    private void initNetVideo() {
        mVideoNet.setMediaController(new MediaController(this));
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        mVideoNet.setVideoPath(url);
        mVideoNet.requestFocus();
        mVideoNet.start();
    }
}
