package com.example.networking;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.networking.adapters.MyNotifier;
import com.example.networking.fragments.ElementFrag;
import com.example.networking.fragments.FurnitureFrag;
import com.example.networking.fragments.RoomFrag;

class MyAdapter extends FragmentPagerAdapter implements MyNotifier {
    Context context;
    int totalTabs;
    int currentPos = 0;

    public MyAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        currentPos = position;
        switch (position) {
            case 0:
                return new ElementFrag();
            case 1:
                return new RoomFrag();
            case 2:
                return new FurnitureFrag();
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }

    @Override
    public void notifyAdapter() {
        System.out.println("ADAPTER CHANGING");
        switch (currentPos) {
            case 0:
                ((ElementFrag) getItem(currentPos)).notifyAdapter();
                break;
            case 1:
                ((RoomFrag) getItem(currentPos)).notifyAdapter();
                break;
            case 2:
                ((FurnitureFrag) getItem(currentPos)).notifyAdapter();
                break;
        }
    }
}
