package com.gary.neconeco.activity.user;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gary.neconeco.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final AutoCompleteTextView email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        final Button register = findViewById(R.id.register);
        final Button signIn = findViewById(R.id.signin);
        final View ret = findViewById(R.id.ret);

        final SQLiteDatabase db = SQLiteDatabase
                .openOrCreateDatabase(this.getFilesDir().toString() + "/user.db3", null);

        db.execSQL("create table IF NOT EXISTS users" +
                "(id integer PRIMARY KEY AUTOINCREMENT, name varchar(50), sex integer, email varchar(50), password varchar(50), fans integer, care integer, description varchar(200))");

        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = email.getText().toString();
                String p = password.getText().toString();
                db.execSQL("insert into users values(?, ?, ?, ?, ?, ?, ?, ?)", new Object[] {null, null, 0, e, p, 0, 0, null});
                Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
            }
        });

    }



}
