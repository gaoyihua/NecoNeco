package com.gary.neconeco.activity.video;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;
import android.widget.MediaController;

import com.gary.neconeco.R;

public class VideoPlayActivity extends AppCompatActivity {
    //VideoView mVideoLocal;
    VideoView mVideoNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        //VideoView mVideoLocal = findViewById();
        mVideoNet = findViewById(R.id.videoTest);
        //initLocalVideo();
        initNetVideo();
    }

    //播放本地视频
    private void initLocalVideo() {
        //设置有进度条可以拖动快进
//        MediaController localMediaController = new MediaController(this);
//        mVideoLocal.setMediaController(localMediaController);
//        String uri = ("android.resource://" + getPackageName() + "/" + R.raw.v1);
//        mVideoLocal.setVideoURI(Uri.parse(uri));
//        mVideoLocal.start();
    }

    //播放网络视频
    private void initNetVideo() {
        //设置有进度条可以拖动快进
        MediaController localMediaController = new MediaController(this);
        mVideoNet.setMediaController(localMediaController);
        Intent intent = getIntent();
        //String url = "https://flv2.bn.netease.com/videolib1/1811/26/OqJAZ893T/HD/OqJAZ893T-mobile.mp4";
        String url = intent.getStringExtra("url");
        mVideoNet.setVideoPath(url);
        mVideoNet.start();
    }
}
