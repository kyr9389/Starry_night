package com.example.tcp.starrynight;

import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
        Button writeButton = (Button)findViewById(R.id.writeButton);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF333366));
        getSupportActionBar().setTitle("별에 기록하기");

        final EditText nicknameText = (EditText) findViewById(R.id.nicknameText);
        final EditText titleText = (EditText) findViewById(R.id.titleText);
        final EditText contentText = (EditText) findViewById(R.id.contentText);

        writeButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nicknameText.getText().toString().length() != 0 && titleText.getText().toString().length() != 0 && contentText.getText().toString().length() != 0) {
                    HttpTask task = new HttpTask();
                    task.execute();
                }else {
                    Toast.makeText(getApplicationContext(), "빈칸이 없는지 확인해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
