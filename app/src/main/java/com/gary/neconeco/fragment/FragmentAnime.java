package com.gary.neconeco.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.gary.neconeco.R;
import com.gary.neconeco.activity.essay.EssayActivity;
import com.gary.neconeco.pojo.Essay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentAnime extends Fragment {
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM1 = "param1";

    private String mParam2;
    private String mParam1;

    private ListView listview;

    public FragmentAnime() {
    }

    public static FragmentAnime newInstance(String param1, String param2) {
        FragmentAnime fragment = new FragmentAnime();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anime, container, false);
        listview = view.findViewById(R.id.anime);

        final AnimeAdapter myAdapter = new AnimeAdapter(getActivity(),putData());
        listview.setAdapter(myAdapter);

        setListViewHeightBasedOnChildren(listview);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,Object> item = (Map)myAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), EssayActivity.class);
                Essay eassy = new Essay(item.get("title").toString(), item.get("comment").toString(), item.get("url").toString());
                intent.putExtra("url", eassy.getUrl());
                startActivity(intent);
            }
        });
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
            listItem.measure(0, 10);
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
    public List<Map<String,Object>> putData(){
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map1 = new HashMap<>();
        map1.put("title", "张三张王五王五王五王五王五王五王五王五");
        map1.put("comment", "11");
        map1.put("url", "https://www.bilibili.com/read/cv2343512?from=search");
        Map<String,Object> map2 = new HashMap<String,Object>();
        map2.put("title", "李四李四李四李四李四李四李四");
        map2.put("comment", "22");
        map2.put("url", "https://www.bilibili.com/read/cv2343512?from=search");
        Map<String,Object> map3 = new HashMap<String,Object>();
        map3.put("title", "1五王五王21五王五王五王五王五");
        map3.put("comment", "33");
        map3.put("url", "https://www.bilibili.com/read/cv2343512?from=search");
        Map<String,Object> map4 = new HashMap<String,Object>();
        map4.put("title", "2王五王五王11五王五王五王五王五");
        map4.put("comment", "33");
        map4.put("url", "https://www.bilibili.com/read/cv2343512?from=search");
        Map<String,Object> map5 = new HashMap<String,Object>();
        map5.put("title", "3王五王五王33五王五王五王五王五");
        map5.put("comment", "33");
        map5.put("url", "https://www.bilibili.com/read/cv2343512?from=search");
        Map<String,Object> map6 = new HashMap<String,Object>();
        map6.put("title", "3王五王五王33五王五王五王五王五");
        map6.put("comment", "33");
        map6.put("url", "https://www.bilibili.com/read/cv2343512?from=search");
        Map<String,Object> map7 = new HashMap<String,Object>();
        map7.put("title", "3王五王五王33五王五王五王五王五");
        map7.put("comment", "33");
        map7.put("url", "https://www.bilibili.com/read/cv2343512?from=search");
        Map<String,Object> map8 = new HashMap<String,Object>();
        map8.put("title", "3王五王五王33五王五王五王五王五");
        map8.put("comment", "33");
        map8.put("url", "https://www.bilibili.com/read/cv2343512?from=search");
        Map<String,Object> map9 = new HashMap<String,Object>();
        map9.put("title", "3王五王五王33五王五王五王五王五");
        map9.put("comment", "33");
        map9.put("url", "https://www.bilibili.com/read/cv2343512?from=search");
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);
        list.add(map7);
        list.add(map8);
        list.add(map9);
        return list;
    }

}
