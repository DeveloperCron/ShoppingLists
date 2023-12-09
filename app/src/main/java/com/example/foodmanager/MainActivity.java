package com.example.foodmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class MainActivity extends AppCompatActivity {
    HomeScreen homeScreen;
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

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, homeScreen).commit();
        extendedFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddItemActivity.class);
                startForResult.launch(intent);
            }
        });
    }
}
