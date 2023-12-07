package com.example.foodmanager;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView price, title, description;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        price = itemView.findViewById(R.id.price);
        title = itemView.findViewById(R.id.title);
        description = itemView.findViewById(R.id.description);
    }
}

