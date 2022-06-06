package org.techtown.basicprojectex;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainDevicesActivity extends AppCompatActivity {
    MainDevicesFragment mainDevicesFragment;
    TemporaryImageFragment temporaryImageFragment;
    //MapFragment mapFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maindevices);

        mainDevicesFragment = new MainDevicesFragment();
        temporaryImageFragment = new TemporaryImageFragment();
        //mapFragment = new MapFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mainDevicesFragment).commit();

        NavigationBarView navigationBarView = findViewById(R.id.bottomNavigationView);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               switch (item.getItemId()){
                   case R.id.page_camera:
                       getSupportFragmentManager().beginTransaction().replace(R.id.container, mainDevicesFragment).commit();
                       return true;
                   case R.id.page_map:
                       getSupportFragmentManager().beginTransaction().replace(R.id.container, temporaryImageFragment).commit();
                       return true;
               }

                return false;
            }
        });
    }
}
