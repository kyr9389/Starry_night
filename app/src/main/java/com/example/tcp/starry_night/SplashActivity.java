package com.example.tcp.starry_night;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by tcp on 2017-09-08.
 */

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            Thread.sleep(3000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
