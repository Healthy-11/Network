package com.example.networking.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.networking.adapters.ElementAdapter;
import com.example.networking.adapters.MyNotifier;
import com.example.networking.R;

public class ElementFrag extends Fragment implements MyNotifier {

    RecyclerView elementList;
    ElementAdapter adapter;

    public ElementFrag() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_element, container, false);

        elementList = view.findViewById(R.id.element_list);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        elementList.setLayoutManager(llm);

        adapter = new ElementAdapter(this.getContext());
        elementList.setAdapter(adapter);
        return view;
    }

    @Override
    public void notifyAdapter() {
        adapter.notifyDataSetChanged();
    }
}
