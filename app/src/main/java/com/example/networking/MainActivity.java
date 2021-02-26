package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.networking.dialogs.AddElementDialog;
import com.example.networking.dialogs.AddFurnitureDialog;
import com.example.networking.dialogs.AddRoomDialog;
import com.example.networking.models.Element;
import com.example.networking.models.Furniture;
import com.example.networking.models.Room;
import com.example.networking.sql.DatabaseManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.jacksonandroidnetworking.JacksonParserFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Credentials;

public class MainActivity extends AppCompatActivity{




    @BindView(R.id.tabLayout) TabLayout tabLayout;
    FloatingActionButton fab_button;

    int tabPos = 0;

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    ViewPager viewPager;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fab_button = findViewById(R.id.fab_button);

        prefs = getApplicationContext().getSharedPreferences("PrefsFile", MODE_PRIVATE);
        editor = prefs.edit();


        fab_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (tabPos == 0)
                {

                AddElementDialog dialog = new AddElementDialog(MainActivity.this);
                dialog.show();
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        int currentPos = tabPos;
                        adapter = new MyAdapter(MainActivity.this ,getSupportFragmentManager(), tabLayout.getTabCount());

                        viewPager.setAdapter(adapter);
                        viewPager.setCurrentItem(currentPos);
                        viewPager.getAdapter().notifyDataSetChanged();
                    }
                });

                }

                if (tabPos == 1)
                {

                    AddRoomDialog dialog = new AddRoomDialog(MainActivity.this);
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            int currentPos = tabPos;
                            adapter = new MyAdapter(MainActivity.this ,getSupportFragmentManager(), tabLayout.getTabCount());

                            viewPager.setAdapter(adapter);
                            viewPager.setCurrentItem(currentPos);
                            viewPager.getAdapter().notifyDataSetChanged();
                        }
                    });

                }

                if (tabPos == 2)
                {

                    AddFurnitureDialog dialog = new AddFurnitureDialog(MainActivity.this);
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            int currentPos = tabPos;
                            adapter = new MyAdapter(MainActivity.this ,getSupportFragmentManager(), tabLayout.getTabCount());

                            viewPager.setAdapter(adapter);
                            viewPager.setCurrentItem(currentPos);
                            viewPager.getAdapter().notifyDataSetChanged();
                        }
                    });

                }

            }

        });


        viewPager = findViewById(R.id.viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#00251a"));
        tabLayout.setSelectedTabIndicatorHeight((int) (2 * getResources().getDisplayMetrics().density));
        tabLayout.addTab(tabLayout.newTab().setText("Element"));
        tabLayout.addTab(tabLayout.newTab().setText("Room"));
        tabLayout.addTab(tabLayout.newTab().setText("Furniture"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        adapter = new MyAdapter(this,getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tabPos = tab.getPosition();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_refresh, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if (id == R.id.menu_refresh) {
            editor.clear();
            editor.apply();
            Toast.makeText(getApplicationContext(), "Preferences cleared", Toast.LENGTH_LONG).show();
        }
        return true;
    }

    private void dialogElement(int tabPos){

    }





}
