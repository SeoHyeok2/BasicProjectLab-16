package org.techtown.basicprojectex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 임시로 MainDevicesActivity 로 이동 처리
                Intent intent = new Intent(getApplicationContext(), MainDevicesActivity.class);
                startActivity(intent);    //intent 에 명시된 액티비티로 이동
            }
        });
    }
}
