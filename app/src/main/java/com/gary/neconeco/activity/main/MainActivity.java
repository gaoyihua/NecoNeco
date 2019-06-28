package com.gary.neconeco.activity.main;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.gary.neconeco.R;
import com.gary.neconeco.activity.collect.FavoriteActivity;
import com.gary.neconeco.activity.user.LoginActivity;
import com.gary.neconeco.activity.user.UserSettingActivity;
import com.gary.neconeco.fragment.FragmentAnime;
import com.gary.neconeco.fragment.FragmentRecommend;

public class MainActivity extends AppCompatActivity {
    private String mEmail;
    private int fans;
    private int care;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        initData();

        initFragment();

        dealBarAction();

        dealAction();
    }

    private void dealAction() {
        RadioButton recommend = findViewById(R.id.recommend_rbt);
        recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentRecommend fragmentAnime = new FragmentRecommend();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ts = fragmentManager.beginTransaction();
                ts.replace(R.id.category_layout, fragmentAnime);
                ts.commit();
            }
        });
        RadioButton anime = findViewById(R.id.anime_rbt);
        anime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentAnime fragmentAnime = new FragmentAnime();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ts = fragmentManager.beginTransaction();
                ts.replace(R.id.category_layout, fragmentAnime);
                ts.commit();
            }
        });

        RadioButton game = findViewById(R.id.game_rbt);
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        RadioButton music = findViewById(R.id.music_rbt);
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        TextView user = findViewById(R.id.email);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, UserSettingActivity.class);
                startActivity(intent2);
            }
        });

        Button editUser = findViewById(R.id.edit_user);
        editUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserSettingActivity.class);
                startActivity(intent);
            }
        });

        Button exitUser = findViewById(R.id.exit_user);
        exitUser.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("myShare", MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.clear();
                edit.apply();
                View my = findViewById(R.id.activity_myself);
                View her = findViewById(R.id.activity_myself_exit);
                my.setVisibility(View.GONE);
                her.setVisibility(View.VISIBLE);
            }
        });

        TextView login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        ImageView collect = findViewById(R.id.collect);
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, FavoriteActivity.class);
                startActivity(intent2);
            }
        });
    }

    private void dealBarAction() {
        RadioButton self_bar = findViewById(R.id.self_bar);
        self_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View home = findViewById(R.id.home_main);
                View my = findViewById(R.id.activity_myself);
                View essay = findViewById(R.id.home_main_essay1);
                home.setVisibility(View.GONE);
                essay.setVisibility(View.GONE);
                my.setVisibility(View.VISIBLE);
            }
        });

        RadioButton dynamic_bar = findViewById(R.id.dynamic_bar);
        dynamic_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View home = findViewById(R.id.home_main);
                View my = findViewById(R.id.activity_myself);
                View essay = findViewById(R.id.home_main_essay1);

                home.setVisibility(View.GONE);
                essay.setVisibility(View.VISIBLE);
                my.setVisibility(View.GONE);
            }
        });

        RadioButton homepage_bar = findViewById(R.id.homepage_bar);
        homepage_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View home = findViewById(R.id.home_main);
                View my = findViewById(R.id.activity_myself);
                View essay = findViewById(R.id.home_main_essay1);
                home.setVisibility(View.VISIBLE);
                essay.setVisibility(View.GONE);
                my.setVisibility(View.GONE);
            }
        });
    }

    private void initData() {
        SharedPreferences sp = getSharedPreferences("myShare", MODE_PRIVATE);
        id = sp.getInt(id + "", -1);
        care = sp.getInt(care + "", 0);
        fans = sp.getInt(fans + "", 0);
        mEmail = sp.getString("email", "XXX@qq.com");

        TextView emailText = findViewById(R.id.email);
        emailText.setText(mEmail);
        final TextView fansText = findViewById(R.id.followers);
        fansText.setText(fans + "");
        TextView careText = findViewById(R.id.following);
        careText.setText(care + "");
    }

    private void initFragment() {
        FragmentRecommend fragmentRecommend = new FragmentRecommend();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ts = fragmentManager.beginTransaction();
        ts.add(R.id.category_layout, fragmentRecommend, "fragmentRecommend");
        ts.commit();
    }
}




