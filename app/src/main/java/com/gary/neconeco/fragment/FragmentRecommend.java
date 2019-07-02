package com.gary.neconeco.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.gary.neconeco.R;
import com.gary.neconeco.activity.video.VideoPlayActivity;
import com.gary.neconeco.pojo.Video;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class FragmentRecommend extends Fragment {
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM1 = "param1";

    private String mParam2;
    private String mParam1;

    private ListView listview;
    private Handler handler;

    public FragmentRecommend() {
    }

    public static FragmentRecommend newInstance(String param1, String param2) {
        FragmentRecommend fragment = new FragmentRecommend();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @SuppressLint("HandlerLeak")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);

        listview = view.findViewById(R.id.recommend);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String jsonStr = (String)msg.obj;

                Gson gson = new Gson();
                if (!jsonStr.equals("null")) {
                    List<Map<String,Object>> list = gson.fromJson(jsonStr, new TypeToken<List<Map<String,Object>>>(){}.getType());
                    //List<NecoVideo> videoList = gson.fromJson(jsonStr, new TypeToken<List<NecoVideo>>(){}.getType());
                    final RecommendAdapter myAdapter = new RecommendAdapter(getActivity(), list);
                    listview.setAdapter(myAdapter);
                    setListViewHeightBasedOnChildren(listview);
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener () {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Map<String,Object> item = (Map)myAdapter.getItem(position);

                            Intent intent = new Intent(getActivity(), VideoPlayActivity.class);
                            Video video = new Video(item.get("name").toString(), item.get("url").toString(), Integer.valueOf(item.get("imageId").toString()));
                            intent.putExtra("url", video.getUrl());
                            startActivity(intent);
                        }
                    });
                }
            }
        };

        putData();

        return view;
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        totalHeight += 500;

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount()));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    /**
     * 模拟数据
     * @return
     */
    public void putData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://10.0.2.2:8080/video/all");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    StringBuffer stringBuffer =new StringBuffer();
                    byte [] buff =new byte[1024];
                    int len;
                    while((len=inputStream.read(buff))!= -1){
                        stringBuffer.append(new String(buff,0,len,"utf-8"));
                    }
                    System.out.println("获取" + stringBuffer.toString());

                    Message msg = new Message();
                    msg.obj = stringBuffer.toString();
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

//        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//        Map<String,Object> map1 = new HashMap<String,Object>();
//        map1.put("name", "张三张三张三张三张三张三张三王五王五王五王五王五王五王五王五王五王五王五王五王五王五王五王五王五");
//        map1.put("comment", "11次");
//        map1.put("imageId", R.drawable.q_play);
//        map1.put("url", "http://flv3.bn.netease.com/tvmrepo/2016/10/0/R/EC3AF1J0R/SD/EC3AF1J0R-mobile.mp4");
//        Map<String,Object> map2 = new HashMap<String,Object>();
//        map2.put("name", "李四李四李四李四李四李四李四");
//        map2.put("comment", "22次");
//        map2.put("imageId", R.drawable.q_play);
//        map2.put("url", "http://flv3.bn.netease.com/tvmrepo/2016/10/0/R/EC3AF1J0R/SD/EC3AF1J0R-mobile.mp4");
//        Map<String,Object> map3 = new HashMap<String,Object>();
//        map3.put("name", "王五王五王五王五王五王五王五");
//        map3.put("comment", "33次");
//        map3.put("imageId", R.drawable.q_play);
//        map3.put("url", "http://flv3.bn.netease.com/tvmrepo/2016/10/0/R/EC3AF1J0R/SD/EC3AF1J0R-mobile.mp4");
//        list.add(map1);
//        list.add(map2);
//        list.add(map3);
//        return list;
    }

}
