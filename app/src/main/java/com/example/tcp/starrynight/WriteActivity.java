package com.example.tcp.starrynight;

import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tcp.starry_night.R;
import java.net.MalformedURLException;

/**
 * Created by USER-PC on 2017-09-09.
 */

public class WriteActivity extends AppCompatActivity {

    public static String nicknamebody = new String();
    public static String contentbody = new String();
    public static String titlebody = new String();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF333366));
        getSupportActionBar().setTitle("별에 기록하기");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        final EditText nicknameText = (EditText) findViewById(R.id.nicknameText);
        final EditText titleText = (EditText) findViewById(R.id.titleText);
        final EditText contentText = (EditText) findViewById(R.id.contentText);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.write_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        final EditText nicknameText = (EditText) findViewById(R.id.nicknameText);
        final EditText titleText = (EditText) findViewById(R.id.titleText);
        final EditText contentText = (EditText) findViewById(R.id.contentText);

        if (id == android.R.id.home) {
            finish();
            return true;
        }
        if (id == R.id.action_write) {
            if (nicknameText.getText().toString().length() != 0 && titleText.getText().toString().length() != 0 && contentText.getText().toString().length() != 0) {
                HttpTask task = new HttpTask();
                task.execute();
            }else {
                Toast.makeText(getApplicationContext(), "빈칸이 없는지 확인해주세요", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class HttpTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            final EditText nicknameText = (EditText) findViewById(R.id.nicknameText);
            final EditText titleText = (EditText) findViewById(R.id.titleText);
            final EditText contentText = (EditText) findViewById(R.id.contentText);

                    try {
                        PHPRequest request = new PHPRequest("http://kyr9389.kuvh.kr:80/Data_insert.php");
                        String result = request.PhPtest(String.valueOf(nicknameText.getText()), String.valueOf(titleText.getText()), String.valueOf(contentText.getText()));
                        return result;

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                        return null;
                    }
        }

        @Override
        protected void onPostExecute(String result) {
            if(result.equals("1")){
                Toast.makeText(getApplication(),"글이 추가되었습니다.",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplication(),"실패",Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }
}
