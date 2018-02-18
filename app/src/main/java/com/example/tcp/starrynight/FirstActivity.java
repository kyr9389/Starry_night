package com.example.tcp.starrynight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.style.TtsSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tcp.starry_night.R;

/**
 * Created by USER-PC on 2018-01-21.
 */

public class FirstActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
                */
        setContentView(R.layout.activity_first);

        TextView dailyBoardButton = (TextView) findViewById(R.id.dailyBoardButton);
        TextView worryBoardButton = (TextView) findViewById(R.id.worryBoardButton);
        TextView boastBoardButton = (TextView) findViewById(R.id.boastBoardButton);

        dailyBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(FirstActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        worryBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(FirstActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        boastBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(FirstActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
