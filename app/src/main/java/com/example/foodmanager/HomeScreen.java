package com.example.foodmanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class HomeScreen extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Item> items;

    public HomeScreen() {
        super(R.layout.fragment_home_screen);
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize your data here
        items = new ArrayList<>();
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), items);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

//        MaterialDividerItemDecoration divider = new MaterialDividerItemDecoration(getContext(), linearLayoutManager.getOrientation());
//        divider.setDividerInsetStart(30);
//        divider.setDividerInsetEnd(30);
//        recyclerView.addItemDecoration(divider);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                recyclerViewAdapter.removeItem(position);
                recyclerViewAdapter.notifyItemRemoved(position);


                Snackbar.make(recyclerView.getRootView(), "Removed item from list", Snackbar.LENGTH_SHORT)
                        .show();
            }
        }).attachToRecyclerView(recyclerView);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_screen, container, false);
    }

    public void updateData(String title, String description, String price) {
        recyclerViewAdapter.updateData(new Item(title, description, price+"$"));
    }
}