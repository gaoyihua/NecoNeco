package com.gary.neconeco.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gary.neconeco.R;
import com.gary.neconeco.activity.video.VideoPlayActivity;
import com.gary.neconeco.pojo.Video;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentRecommend extends Fragment {
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM1 = "param1";

    private String mParam2;
    private String mParam1;

    private OnFragmentInteractionListener mListener;
    private ListView listview;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        listview = view.findViewById(R.id.recommend);

        final RecommendAdapter myAdapter = new RecommendAdapter(getActivity(),putData());
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

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    /**
     * 模拟数据
     * @return
     */
    public List<Map<String,Object>> putData(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("name", "张三张三张三张三张三张三张三王五王五王五王五王五王五王五王五王五王五王五王五王五王五王五王五王五");
        map1.put("comment", "11次");
        map1.put("imageId", R.drawable.q_play);
        map1.put("url", "http://flv3.bn.netease.com/tvmrepo/2016/10/0/R/EC3AF1J0R/SD/EC3AF1J0R-mobile.mp4");
        Map<String,Object> map2 = new HashMap<String,Object>();
        map2.put("name", "李四李四李四李四李四李四李四");
        map2.put("comment", "22次");
        map2.put("imageId", R.drawable.q_play);
        map2.put("url", "http://flv3.bn.netease.com/tvmrepo/2016/10/0/R/EC3AF1J0R/SD/EC3AF1J0R-mobile.mp4");
        Map<String,Object> map3 = new HashMap<String,Object>();
        map3.put("name", "王五王五王五王五王五王五王五");
        map3.put("comment", "33次");
        map3.put("imageId", R.drawable.q_play);
        map3.put("url", "http://flv3.bn.netease.com/tvmrepo/2016/10/0/R/EC3AF1J0R/SD/EC3AF1J0R-mobile.mp4");
        list.add(map1);
        list.add(map2);
        list.add(map3);
        return list;
    }

}
