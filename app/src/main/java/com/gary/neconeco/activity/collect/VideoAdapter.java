package com.gary.neconeco.activity.collect;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gary.neconeco.R;
import com.gary.neconeco.pojo.Video;

import java.util.List;

public class VideoAdapter extends ArrayAdapter {
    private final int resourceId;

    public VideoAdapter(@NonNull Context context, int resource, List<Video> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Video video = (Video)getItem(position);
        //使用Inflater对象来将布局文件解析成一个View
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        //获取该布局内的图片视图
        ImageView videoImage = view.findViewById(R.id.video_image);
        //获取该布局内的文本视图
        TextView videoName = view.findViewById(R.id.video_name);
        //为图片视图设置图片资源
        videoImage.setImageResource(video.getImageId());
        //为文本视图设置文本内容
        videoName.setText(video.getName());
        return view;
    }
}
