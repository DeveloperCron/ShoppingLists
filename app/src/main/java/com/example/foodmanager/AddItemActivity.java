package com.example.foodmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class AddItemActivity extends AppCompatActivity {
    MaterialToolbar materialToolbar;
    TextInputLayout itemInputLayout, priceInputLayout;
    ChipGroup chipGroup;
    Button button;
    String chosenCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        itemInputLayout = findViewById(R.id.itemInputLayout);
        priceInputLayout = findViewById(R.id.priceInputLayout);
        chipGroup = findViewById(R.id.chipGroup);
        button = findViewById(R.id.addItemBtn);
        materialToolbar = findViewById(R.id.topAppBar);

        chipGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {
            for (Integer id : checkedIds) {
                Chip chip = group.findViewById(id);
                chosenCategory = chip.getText().toString();
            }
        });

        materialToolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(AddItemActivity.this, MainActivity.class);
            startActivity(intent);
        });

        button.setOnClickListener(v -> {
            if (validateTextInputs()) {
                System.out.println("Bad what the fuck?");
                Log.d("TextInputs validated", "Validation");
                Intent intent = new Intent();
                intent.putExtra("ItemName", itemInputLayout.getEditText().getText().toString());
                intent.putExtra("Description", chosenCategory);
                intent.putExtra("Price", priceInputLayout.getEditText().getText().toString());

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    public boolean validateTextInputs() {
        boolean areValid;

        areValid = validateInput((TextInputEditText) priceInputLayout.getEditText(), priceInputLayout, "Invalid price");
        areValid = validateInput((TextInputEditText) itemInputLayout.getEditText(), itemInputLayout, "Invalid item name") && areValid;

        // The chosenCategory first time is null!
        if (chosenCategory == null) {
            areValid = false;
            new MaterialAlertDialogBuilder(AddItemActivity.this)
                    .setTitle("Empty category")
                    .setMessage("Please choose the item category")
                    .setPositiveButton("Accept", (dialog, which) -> {

                    })
                    .show();
        }

        return areValid;
    }

    private boolean validateInput(TextInputEditText editText, TextInputLayout inputLayout, String errorMessage) {
        String inputValue = Objects.requireNonNull(editText.getText()).toString().trim();

        if (inputValue.isEmpty()) {
            inputLayout.setErrorEnabled(true);
            inputLayout.setError(errorMessage);
            return false;
        } else {
            inputLayout.setErrorEnabled(false);
            return true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}