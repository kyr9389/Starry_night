package com.example.tcp.starry_night;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    MyListAdapter myListAdapter;
    ArrayList<list_item> list_itemArrayList;
    Button addButton;
    int checked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.my_listView);
        addButton = (Button) findViewById(R.id.addButton);

        list_itemArrayList = new ArrayList<list_item>();

        list_itemArrayList.add(new list_item(R.drawable.startree, "Admin", "업데이트 중입니다.", new Date(System.currentTimeMillis()), "계속해서 기능들을 구현해 나갈 예정입니다."));
        list_itemArrayList.add(new list_item(R.drawable.startree, "Admin", "최적화가 완료되었습니다.", new Date(System.currentTimeMillis()), "ViewHolder를 통해 ListView 최적화를 완료하였습니다. 많은 양의 리스트를 불러올때 지연되는 시간이 줄어듭니다."));
        list_itemArrayList.add(new list_item(R.drawable.startree, "Admin", "오프라인 테스트입니다.", new Date(System.currentTimeMillis()), "별 헤는 밤 오프라인 테스트입니다. 지금은 개발자 실력이 안되서 못하지만 언젠간 개발이 되겠죠."));

        myListAdapter = new MyListAdapter(MainActivity.this, list_itemArrayList);
        listView.setAdapter(myListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
                //checked = listView.getCheckedItemPosition();
                list_itemArrayList.remove(position);
                myListAdapter.notifyDataSetChanged();
            }
        });

        addButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                list_itemArrayList.add(new list_item(R.mipmap.ic_launcher, "User1", "띠용", new Date(System.currentTimeMillis()), "오 이게 뭐징"));
                myListAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "글이 추가되었습니다.", Toast.LENGTH_LONG).show();
            }
        });
    }




}
