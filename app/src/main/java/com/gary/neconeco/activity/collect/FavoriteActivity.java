package com.gary.neconeco.activity.collect;

import android.app.TabActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TabHost;

import com.gary.neconeco.R;

public class FavoriteActivity extends TabActivity {
    private TabHost tabHost;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_tab);
        tabHost = getTabHost();

        Intent videoIntent1 = new Intent(this, VideoCarTabActivity.class);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("汽车").setContent(videoIntent1));
        Intent videoIntent2 = new Intent(this, VideoMovieTabActivity.class);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("电影").setContent(videoIntent2));

        tabHost.setCurrentTabByTag("tab1");

        final SQLiteDatabase db = SQLiteDatabase
                .openOrCreateDatabase(this.getFilesDir().toString() + "/user.db3", null);

        View ret = findViewById(R.id.ret);
        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        db.execSQL("create table IF NOT EXISTS video" +
                "(id integer PRIMARY KEY AUTOINCREMENT, " +
                "name varchar(50), " +
                "url varchar(250), " +
                "description varchar(50), " +
                "imageId integer, " +
                "category varchar(50))");

//        db.execSQL("insert into video values(?, ?, ?, ?, ?, ?)",
//                new Object[] {
//                        null,
//                        "汽车1",
//                        "https://flv2.bn.netease.com/videolib1/1811/26/OqJAZ893T/HD/OqJAZ893T-mobile.mp4",
//                        "性价比高", R.mipmap.logo3, "汽车"});
//        db.execSQL("insert into video values(?, ?, ?, ?, ?, ?)",
//                new Object[] {
//                        null,
//                        "汽车2",
//                        "https://flv2.bn.netease.com/videolib1/1811/26/OqJAZ893T/HD/OqJAZ893T-mobile.mp4",
//                        "性价比高", R.mipmap.logo3, "汽车"});
//        db.execSQL("insert into video values(?, ?, ?, ?, ?, ?)",
//                new Object[] {
//                        null,
//                        "汽车2",
//                        "https://flv2.bn.netease.com/videolib1/1811/26/OqJAZ893T/HD/OqJAZ893T-mobile.mp4",
//                        "性价比高", R.mipmap.logo3, "电影"});
//        db.execSQL("insert into video values(?, ?, ?, ?, ?, ?)",
//                new Object[] {
//                        null,
//                        "汽车2",
//                        "https://flv2.bn.netease.com/videolib1/1811/26/OqJAZ893T/HD/OqJAZ893T-mobile.mp4",
//                        "性价比高", R.mipmap.logo3, "电影"});


    }
}
