package org.techtown.basicprojectex;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference myRef = firebaseDatabase.getReference().child("users").child("주민1");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);

                ListviewDeviceItem listviewDeviceItem = new ListviewDeviceItem();
                listviewDeviceItem.setParkState(user.parking_state);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        moveMain(1);
    }



    private void moveMain(int sec) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //new Intent(현재 context, 이동할 activity)
                Intent intent = new Intent(getApplicationContext(), MainDevicesActivity.class);

                startActivity(intent);    //intent 에 명시된 액티비티로 이동

                finish();    //현재 액티비티 종료
            }
        }, 1000 * sec); // sec초 정도 딜레이를 준 후 시작
    }

}


