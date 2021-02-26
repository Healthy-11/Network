package com.example.networking.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.networking.adapters.FurnitureAdapter;
import com.example.networking.adapters.MyNotifier;
import com.example.networking.R;

public class FurnitureFrag extends Fragment implements MyNotifier {

    RecyclerView furnitureList;
    FurnitureAdapter adapter;

    public FurnitureFrag() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_furniture, container, false);

        furnitureList = view.findViewById(R.id.furniture_list);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        furnitureList.setLayoutManager(llm);

        adapter =new FurnitureAdapter(this.getContext());
        furnitureList.setAdapter(adapter);
        return view;
    }

    @Override
    public void notifyAdapter() {
        adapter.notifyDataSetChanged();
    }
}
