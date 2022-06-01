package org.techtown.basicprojectex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListViewDeviceAdapter extends ArrayAdapter {
    public interface ListButtonClickListener {
        void onListBtnClick(int position);
    }

    // 생성자로부터 전달된 resource id 값을 저장.
    int resourceId;

    // ListViewDeviceAdapter 생성자.
    public ListViewDeviceAdapter(@NonNull Context context, int resource,
                                 ArrayList<ListviewDeviceItem> list) {
        super(context, resource, list);

        // resource id 값 복사. (super로 전달된 resource를 참조할 방법이 없음.)
        this.resourceId = resource;
    }

    // 새롭게 만든 Layout을 위한 View를 생성
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // 생성자로부터 저장된 resourceId(listview_btn_item)에 해당하는 Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resourceId/*R.layout.listview_btn_item*/, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)로부터 위젯에 대한 참조 획득
        final ImageView imageParkingLotView = (ImageView) convertView.findViewById(R.id.img_parking);
        final TextView textViewState = (TextView) convertView.findViewById(R.id.tv_spaceExist);
        final TextView textViewForRecord = (TextView) convertView.findViewById(R.id.tv_parkingPosition);
        final TextView textViewOrderOfDevice = (TextView) convertView.findViewById(R.id.tv_numOfMachine);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListviewDeviceItem listViewItem = (ListviewDeviceItem) getItem(position);

        // 아이템 내 각 위젯에 데이터 반영
        String currentSpaceState = "현재 빈자리 : " + listViewItem.getParkState();
        String currentRecording = listViewItem.getRecordPosition() + ", " + listViewItem.getMachineName();
        textViewState.setText(currentSpaceState);
        textViewForRecord.setText(currentRecording);
        textViewOrderOfDevice.setText("기기 " + (pos + 1));


        Button buttonShowCamera = (Button) convertView.findViewById(R.id.button_showCamera);
        buttonShowCamera.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                // buttonShowCamera 클릭 시 버튼 숨김 처리 및 주차 화면 표시하기
                buttonShowCamera.setVisibility(View.INVISIBLE);
                buttonShowCamera.setEnabled(false);
                imageParkingLotView.setImageDrawable(listViewItem.getParkingVideo());
            }
        });

        return convertView;
    }
}
