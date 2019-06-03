package com.gary.neconeco.activity.collect;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.gary.neconeco.R;
import com.gary.neconeco.activity.video.VideoPlayActivity;

import java.util.ArrayList;

public class CollectActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
     private ViewPager viewpager;
     private PagerTabStrip tabstrip;
     private String[] title = {"视频","追番","追剧","话题"};
     private String[] data = {"汽车视频", "汽车视频"};
     private ArrayList<View> views = new ArrayList<View>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        viewpager=findViewById(R.id.viewpager);
        tabstrip=findViewById(R.id.tabstrip);
        initial();
        viewpager.setAdapter(new MyPagerAdapter());
        View ret = findViewById(R.id.ret);
        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        ListView listView = findViewById(R.id.video_list);
//        listView.setAdapter(new ArrayAdapter<>(
//                CollectActivity.this, android.R.layout.simple_list_item_1, data));
    }

    public void startVideo(View v) {
        Intent intent = new Intent(CollectActivity.this, VideoPlayActivity.class);
        startActivity(intent);
    }

    private void initial() {
        views.add(getLayoutInflater().inflate(R.layout.layout_video,null));
        views.add(getLayoutInflater().inflate(R.layout.layout_video,null));
        views.add(getLayoutInflater().inflate(R.layout.layout_video,null));
        views.add(getLayoutInflater().inflate(R.layout.layout_video,null));
        tabstrip.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
        tabstrip.setTabIndicatorColor(getResources().getColor(android.R.color.holo_blue_bright));
        tabstrip.setTextColor(Color.WHITE);
        viewpager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View v = views.get(position);
            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(views.get(position));
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }
}
