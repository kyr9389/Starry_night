package com.example.tcp.starrynight;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.tcp.starry_night.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    String myJSON;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_NICKNAME = "nickname";
    private static final String TAG_TITLE = "title";
    private static final String TAG_CONTENT = "context";

    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    ListView list;

    public static MyListAdapter myListAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        personList = new ArrayList<HashMap<String, String>>();
        getData("http://kyr9389.kuvh.kr/Data_receive.php");
        list = (ListView) findViewById(R.id.my_listView);
        list.setAdapter(myListAdapter);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF333366));
        getSupportActionBar().setTitle("어딘가의 별");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            list.setAdapter(null);
            getData("http://kyr9389.kuvh.kr/Data_receive.php");
            Toast.makeText(this, "새로고침", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_add) {
            Intent intent = new Intent(MainActivity.this, WriteActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            personList.clear();
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);
                String nickname = c.getString(TAG_NICKNAME);
                String title = c.getString(TAG_TITLE);
                String context = c.getString(TAG_CONTENT);

                HashMap<String, String> persons = new HashMap<String, String>();

                persons.put(TAG_NICKNAME, nickname);
                persons.put(TAG_TITLE, title);
                persons.put(TAG_CONTENT, context);

                personList.add(persons);
            }

            Collections.reverse(personList);

            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, personList, R.layout.item,
                    new String[]{TAG_NICKNAME, TAG_TITLE, TAG_CONTENT},
                    new int[]{R.id.nickname_textview, R.id.title_textview, R.id.content_textview}
            );
            list.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();

                } catch (Exception e) {
                    Log.e("Exception", "Json Get Error");
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
}

