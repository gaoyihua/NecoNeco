package com.gary.neconeco.activity.collect;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gary.neconeco.R;
import com.gary.neconeco.activity.video.VideoPlayActivity;
import com.gary.neconeco.pojo.Video;

import java.util.ArrayList;
import java.util.List;

public class VideoCarTabActivity extends Activity {
    private List<Video> videoList = new ArrayList<>();
    private SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_tab_video);

        db = SQLiteDatabase
                .openOrCreateDatabase(this.getFilesDir().toString() + "/user.db3", null);

        initVideos();

        VideoAdapter videoAdapter = new VideoAdapter(VideoCarTabActivity.this, R.layout.video_item, videoList);
        final ListView listView = findViewById(R.id.video_list);
        listView.setAdapter(videoAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(VideoCarTabActivity.this, VideoPlayActivity.class);
                Video video = (Video) parent.getItemAtPosition(position);
                intent.putExtra("url", video.getUrl());
                startActivity(intent);
            }
        });
    }

    // 模拟数据
    private void initVideos() {
        Cursor cursor = db.rawQuery("select * from video where category='" + "汽车" + "'", null);
        while (true) {
            if (cursor.moveToNext() == false) {
                break;
            }
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String url = cursor.getString(2);
            String description = cursor.getString(3);
            int imageId = cursor.getInt(4);
            String category = cursor.getString(5);
            Video video = new Video(id, name, url, description, imageId, category);
            videoList.add(video);
        }
    }
}
