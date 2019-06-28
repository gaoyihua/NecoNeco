package com.gary.neconeco.activity.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gary.neconeco.R;

public class UserSettingActivity extends AppCompatActivity {
    private String mEmail;
    private String name;
    private int id;
    private String description;
    private int sex;
    private TextView emailText;
    private TextView nameText;
    private TextView infoText;
    private TextView sexText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);
        View ret = findViewById(R.id.ret);
        View lineName = findViewById(R.id.linename);
        View linesex = findViewById(R.id.linesex);
        View lineinfo = findViewById(R.id.lineinfo);
        emailText = findViewById(R.id.email);
        nameText = findViewById(R.id.name);
        infoText = findViewById(R.id.info);
        sexText = findViewById(R.id.sex);

        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        refreshData();

        linesex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserSettingActivity.this, "点击了性别！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UserSettingActivity.this, UserSettingSexActivity.class);
                startActivity(intent);
            }
        });

        lineinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserSettingActivity.this, "点击了描述！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UserSettingActivity.this, UserSettingDescActivity.class);
                startActivity(intent);
            }
        });

        lineName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserSettingActivity.this, "点击了用户名！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UserSettingActivity.this, UserSettingNameActivity.class);
                startActivity(intent);
            }
        });
    }

    private void refreshData() {
        SharedPreferences sp = getSharedPreferences("myShare", MODE_PRIVATE);
        id = sp.getInt( "id", -1);
        mEmail = sp.getString("email", "xxx@qq.com");
        name = sp.getString("name", "XX");
        description = sp.getString("description", "暂无");
        sex = sp.getInt("sex", 0);

        emailText.setText(mEmail);
        nameText.setText(name);
        infoText.setText(description);
        sexText.setText(sex == 0 ? "男" : "女");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        refreshData();
    }
}
