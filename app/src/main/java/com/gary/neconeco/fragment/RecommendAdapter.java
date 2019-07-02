package com.gary.neconeco.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gary.neconeco.R;
import com.gary.neconeco.activity.video.VideoPlayActivity;
import com.gary.neconeco.pojo.NecoVideo;

import java.util.List;
import java.util.Map;

public class RecommendAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Map<String,Object>> list;
    private List<NecoVideo> videoList;

    public RecommendAdapter(Context context , List<Map<String,Object>> list){
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
    }

    public RecommendAdapter(Context context , List<Map<String,Object>> list, List<NecoVideo> videoList){
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
        this.videoList = videoList;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_recommend_item, null);
            holder.name = convertView.findViewById(R.id.title);
            holder.comment = convertView.findViewById(R.id.comment);
            holder.imageId = convertView.findViewById(R.id.iv1);
            holder.collectVideo = convertView.findViewById(R.id.collectVideo);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        final String nameStr = (String) list.get(position).get("name");
//        final String commentStr = (String) list.get(position).get("comment");
        final String commentStr = String.valueOf(list.get(position).get("comment"));
//        final Integer imageIdNum = (Integer) list.get(position).get("imageId");
        final Integer imageIdNum = Double.valueOf(list.get(position).get("imageId").toString()).intValue();
        System.out.println("imageIdNum : " + imageIdNum);


        holder.name.setText(nameStr);
        holder.comment.setText(commentStr);
        holder.imageId.setImageResource(R.drawable.q_play);

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), VideoPlayActivity.class);
                intent.putExtra("url", list.get(position).get("url").toString());
                v.getContext().startActivity(intent);
            }
        });

        holder.imageId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), VideoPlayActivity.class);
                intent.putExtra("url", list.get(position).get("url").toString());
                v.getContext().startActivity(intent);
            }
        });

        holder.collectVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 收藏视频 视频url name imageid 添加到数据库中
                Toast.makeText(v.getContext(), "收藏视频动作待完成", Toast.LENGTH_LONG).show();
            }
        });

        return convertView;
    }




    public final class ViewHolder{
        private TextView name;
        private TextView comment;
        private ImageView imageId;
        private Button collectVideo;
    }

}
