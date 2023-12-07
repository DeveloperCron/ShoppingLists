package com.example.foodmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    HomeScreen homeScreen;
    PopularScreen popularScreen;
    ExtendedFloatingActionButton extendedFloatingActionButton;

    // Googles recommended way to handle result listening
    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    // Shouldn't be null
                    assert data != null; // Something you guys don't know yet
                    String itemName = data.getStringExtra("ItemName");
                    String description = data.getStringExtra("Description");
                    String price = data.getStringExtra("Price");

                    homeScreen.updateData(itemName, description, price);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        extendedFloatingActionButton = findViewById(R.id.FAB);

        homeScreen = new HomeScreen();
        popularScreen = new PopularScreen();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.popular) {
                    extendedFloatingActionButton.hide();
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, popularScreen).commit();
                }
                if (item.getItemId() == R.id.home) {
                    extendedFloatingActionButton.show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, homeScreen).commit();
                }

                return true;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.home);

        extendedFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddItemActivity.class);
                startForResult.launch(intent);
            }
        });
    }
}
