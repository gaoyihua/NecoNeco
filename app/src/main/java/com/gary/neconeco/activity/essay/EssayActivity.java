package com.gary.neconeco.activity.essay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gary.neconeco.R;

public class EssayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.the_essay1);
        Button ret = findViewById(R.id.back_btn);
        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EssayActivity.this.finish();
            }
        });
    }



}
