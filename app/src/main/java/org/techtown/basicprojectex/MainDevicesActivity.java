package org.techtown.basicprojectex;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainDevicesActivity extends AppCompatActivity {
    static int deviceListSize = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maindevices);

        ListView listView;
        ListViewDeviceAdapter adapter;
        ArrayList<ListviewDeviceItem> items = new ArrayList<>();

        //item loading...
        loadItems(items);

        //adapter 생성
        adapter = new ListViewDeviceAdapter(this, R.layout.listview_for_devices, items);

        // 리스트뷰 참조 및 Adapter 달기
        listView = (ListView) findViewById(R.id.listview_devices);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        /* TODO: 리스트뷰 생성 후 화면 이미지에 대해 모델을 돌림
        -> item.setParkState("있음") 또는 item.setParkState("없음") */
    }

    public boolean loadItems(ArrayList<ListviewDeviceItem> list) {
        ListviewDeviceItem item;

        if (list == null) {
            list = new ArrayList<ListviewDeviceItem>();
        }

        // 아이템 생성.
        item = new ListviewDeviceItem();
        item.setMachineName("Galaxy A7");
        item.setParkingVideo(ContextCompat.getDrawable(this, R.drawable.parkinglot_0));

        item.setParkState("분석 중");
        item.setRecordPosition("108동 주차장 앞");
        item.setMachineNumber(++deviceListSize);

        list.add(item);
        return true;
    }
}
