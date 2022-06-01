package org.techtown.basicprojectex;

import android.graphics.drawable.Drawable;

import android.provider.ContactsContract;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;
import org.jetbrains.annotations.NotNull;

public class ListviewDeviceItem extends AppCompatActivity {
    // Constant values
    private final static String STATE_FREE = "있음";
    private final static String STATE_FULL = "없음";

    // 인스턴스 필드
    private int machineNumber; // 기기 번호
    private String parkState = STATE_FREE; // 모델이 판단한 주차장 상태
    private String recordPosition = "";
    private String machineName = "";
    private Drawable parkingVideo; // 주차장 영상(사진) 표시

    ListviewDeviceItem() {}

    //getter / setter
    public int getMachineNumber() {
        return this.machineNumber;
    }

    public void setMachineNumber(int machineNumber) {
        this.machineNumber = machineNumber;
    }

    public String getParkState() {
        return this.parkState;
    }

    public void setParkState(String parkState) {
        this.parkState = parkState;
    }

    public Drawable getParkingVideo() {
        return this.parkingVideo;
    }

    public void setParkingVideo(Drawable parkingVideo) {
        this.parkingVideo = parkingVideo;
    }

    public String getRecordPosition() {
        return this.recordPosition;
    }

    public void setRecordPosition(String recordPosition) {
        this.recordPosition = recordPosition;
    }

    public String getMachineName() {
        return this.machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }
}
