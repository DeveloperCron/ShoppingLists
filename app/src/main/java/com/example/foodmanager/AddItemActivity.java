package com.example.foodmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class AddItemActivity extends AppCompatActivity {
    MaterialToolbar materialToolbar;
    TextInputLayout itemInputLayout, descriptionInputLayout, priceInputLayout;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        itemInputLayout = findViewById(R.id.itemInputLayout);
        descriptionInputLayout = findViewById(R.id.descriptionInputLayout);
        priceInputLayout = findViewById(R.id.priceInputLayout);

        button = findViewById(R.id.addItemBtn);
        materialToolbar = findViewById(R.id.topAppBar);
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateTextInputs()) {
                    Log.d("TextInputs validated", "Validation");
                    Intent intent = new Intent();
                    intent.putExtra("ItemName", itemInputLayout.getEditText().getText().toString());
                    intent.putExtra("Description", descriptionInputLayout.getEditText().getText().toString());
                    intent.putExtra("Price", priceInputLayout.getEditText().getText().toString());

                    setResult(RESULT_OK, intent);
//                    For Performance we will add it
                    finish();
                }
            }
        });
    }

    public boolean validateTextInputs() {
        boolean areValid;

        areValid = validateInput((TextInputEditText) priceInputLayout.getEditText(), priceInputLayout, "Invalid price");
        areValid = validateInput((TextInputEditText) itemInputLayout.getEditText(), itemInputLayout, "Invalid item name") && areValid;

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
}