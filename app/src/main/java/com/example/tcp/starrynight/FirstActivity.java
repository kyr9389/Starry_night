package com.example.tcp.starrynight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.tcp.starry_night.R;

/**
 * Created by USER-PC on 2018-01-21.
 */

public class FirstActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_first);

        ImageButton imagebtn1 = (ImageButton) findViewById(R.id.imageButton);

                imagebtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Intent i = new Intent
                        (FirstActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
