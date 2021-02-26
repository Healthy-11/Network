package com.example.networking.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.networking.adapters.MyNotifier;
import com.example.networking.R;
import com.example.networking.adapters.RoomAdapter;

public class RoomFrag extends Fragment implements MyNotifier {

    RecyclerView roomList;
    RoomAdapter adapter;

    public RoomFrag() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room, container, false);

        roomList = view.findViewById(R.id.room_list);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        roomList.setLayoutManager(llm);

        adapter = new RoomAdapter(this.getContext());
        roomList.setAdapter(adapter);
        return view;
    }

    @Override
    public void notifyAdapter() {
        if (adapter != null)
            adapter.notifyDataSetChanged();
    }
}
