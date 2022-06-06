package org.techtown.basicprojectex;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainDevicesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainDevicesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // field
    static int deviceListSize = 0;

    public MainDevicesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainDevicesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainDevicesFragment newInstance(String param1, String param2) {
        MainDevicesFragment fragment = new MainDevicesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_devices, container, false);

        ListView listView;
        ListViewDeviceAdapter adapter;
        TextView tvConnectedDevice = view.findViewById(R.id.tv_connectedDevice);
        ArrayList<ListviewDeviceItem> items = new ArrayList<>();

        //item loading...
        loadItems(items);
        tvConnectedDevice.setText("현재 " + items.size() + " 개 구역");

        //adapter 생성
        adapter = new ListViewDeviceAdapter(requireContext(), R.layout.listview_for_devices, items);

        // 리스트뷰 참조 및 Adapter 달기
        listView = view.findViewById(R.id.listview_devices);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        /* TODO: 리스트뷰 생성 후 화면 이미지에 대해 모델을 돌림
        -> item.setParkState("있음") 또는 item.setParkState("없음") */

        return view;
    }

    public boolean loadItems(ArrayList<ListviewDeviceItem> list) {
        ListviewDeviceItem item;

        if (list == null) {
            list = new ArrayList<ListviewDeviceItem>();
        }

        // 아이템 생성.
        item = new ListviewDeviceItem();
        item.setParkingVideo(ContextCompat.getDrawable(getContext(), R.drawable.parkinglot_0));

        item.setParkState("여유");
        item.setRecordPosition("108동 주차장 앞");
        item.setMachineNumber(list.size() + 1);
        list.add(item);
        return true;
    }
}