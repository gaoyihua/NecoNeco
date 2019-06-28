package com.gary.neconeco.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gary.neconeco.R;
import com.gary.neconeco.activity.essay.EssayActivity;

import java.util.List;
import java.util.Map;

public class AnimeAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Map<String,Object>> list;

    public AnimeAdapter(Context context , List<Map<String,Object>> list){
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
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
            convertView = mInflater.inflate(R.layout.list_anime_item, null);
            holder.title = convertView.findViewById(R.id.title);
            holder.comment = convertView.findViewById(R.id.comment);
            holder.collectArticle = convertView.findViewById(R.id.collectArticle);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.title.setText((String)list.get(position).get("title"));
        holder.comment.setText((String)list.get(position).get("comment"));

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EssayActivity.class);
                intent.putExtra("url", (String)list.get(position).get("url"));
                v.getContext().startActivity(intent);
            }
        });

        holder.collectArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 收藏文章 文章url name 添加到数据库中
                Toast.makeText(v.getContext(), "收藏文章动作待完成", Toast.LENGTH_LONG).show();
            }
        });

        return convertView;
    }


    public final class ViewHolder{
        private TextView title;
        private TextView comment;
        private Button collectArticle;
    }

}
