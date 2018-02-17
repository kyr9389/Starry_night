package com.example.tcp.starrynight;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tcp.starry_night.R;

import java.net.MalformedURLException;

/**
 * Created by USER-PC on 2017-12-23.
 */

public class ViewActivity extends AppCompatActivity {

    private String noText = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF333366));
        getSupportActionBar().setTitle("누군가의 기록");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        Intent intent = getIntent();

        TextView nickname = (TextView) findViewById(R.id.nickname);
        TextView title = (TextView) findViewById(R.id.title);
        TextView content = (TextView) findViewById(R.id.content);

        nickname.setText(intent.getStringExtra("nickname"));
        title.setText(intent.getStringExtra("title"));
        content.setText(intent.getStringExtra("content"));
        noText = intent.getStringExtra("no");

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        if (id == R.id.action_delete) {
            HttpTask task = new HttpTask();
            task.execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class HttpTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            try {
                PHPRequest request = new PHPRequest("http://kyr9389.kuvh.kr:80/Data_delete.php");
                String result = request.PhPtest2(String.valueOf(noText));
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("1")) {
                Toast.makeText(getApplication(), "글이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplication(), "실패", Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }
}
