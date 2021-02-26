package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.networking.models.Element;
import com.example.networking.models.Furniture;
import com.example.networking.models.Room;
import com.example.networking.sql.DatabaseManager;
import com.jacksonandroidnetworking.JacksonParserFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import okhttp3.Credentials;

public class LoginActivity extends AppCompatActivity {

    JSONArray elementsArray = null;
    JSONArray roomsArray = null;
    JSONArray furnituresArray = null;

    SharedPreferences prefs;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.setParserFactory(new JacksonParserFactory());
        ButterKnife.bind(this);

        prefs = getApplicationContext().getSharedPreferences("PrefsFile", MODE_PRIVATE);
        editor = prefs.edit();

        Button login_btn = (Button) findViewById(R.id.buttonLogin);
        EditText edit_username = (EditText) findViewById(R.id.editTextTextUsername);
        EditText edit_password = (EditText) findViewById(R.id.editTextTextPassword);

        edit_username.setText(prefs.getString("Username", ""));
        edit_password.setText(prefs.getString("Password", ""));

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edit_username.getText().toString();
                String password = edit_password.getText().toString();

                if (!edit_password.getText().toString().isEmpty() && !edit_username.getText().toString().isEmpty())
                {

                    if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(LoginActivity.this, new String[]{
                                Manifest.permission.ACCESS_NETWORK_STATE
                        }, 100);
                        AndroidNetworking.get("https://portail.fokus.immo/api/login")
                                .addHeaders("Content-Type", "application/json")
                                .addHeaders("Authorization", Credentials.basic(username, password)) //002A8080 cacaca

                                .setPriority(Priority.LOW)
                                .build()
                                .getAsJSONObject(new JSONObjectRequestListener() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        parserJson(response);
                                        editor.putString("Username", username);
                                        editor.putString("Password", password);
                                        editor.apply();
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        Toast.makeText(LoginActivity.this, "Login accepted", Toast.LENGTH_SHORT).show();
                                        startActivity(intent);
                                    }
                                    @Override
                                    public void onError(ANError error) {
                                        // handle error
                                        Toast.makeText(LoginActivity.this, "Username or password incorrects", Toast.LENGTH_SHORT).show();
                                        System.out.println("ERROR");
                                        System.out.println(error.getErrorBody());
                                        System.out.println(error.getErrorCode());
                                        System.out.println(error.getErrorDetail());
                                        System.out.println(error.getResponse());
                                    }
                                });
                    }

                }
                else{
                    Toast.makeText(LoginActivity.this, "Please fill informations", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void parserJson(JSONObject response) {

        DatabaseManager.getInstance(this).clearAllTables();

        try {

            elementsArray = response.getJSONArray("elements");
            roomsArray = response.getJSONArray("rooms");
            furnituresArray = response.getJSONArray("furnitures");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 0; i < elementsArray.length(); i++) {

                Element element = new Element(
                        elementsArray.getJSONObject(i).getString("id"),
                        elementsArray.getJSONObject(i).getString("uid"),
                        elementsArray.getJSONObject(i).getString("name"),
                        elementsArray.getJSONObject(i).getString("category_id"),
                        elementsArray.getJSONObject(i).getString("owner")
                );
                DatabaseManager.getInstance(this).elementDao().insert(element);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }



        try {
            for (int i = 0; i < roomsArray.length(); i++) {
                Room room = new Room(
                        roomsArray.getJSONObject(i).getString("id"),
                        roomsArray.getJSONObject(i).getString("uid"),
                        roomsArray.getJSONObject(i).getString("name"),
                        roomsArray.getJSONObject(i).getString("owner")
                );
                DatabaseManager.getInstance(this).roomDao().insert(room);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            for (int i = 0; i < furnituresArray.length(); i++) {
                Furniture furniture = new Furniture(
                        furnituresArray.getJSONObject(i).getString("id"),
                        furnituresArray.getJSONObject(i).getString("uid"),
                        furnituresArray.getJSONObject(i).getString("name"),
                        furnituresArray.getJSONObject(i).getString("category_id"),
                        furnituresArray.getJSONObject(i).getString("category"),
                        furnituresArray.getJSONObject(i).getString("owner")
                );
                DatabaseManager.getInstance(this).furnitureDao().insert(furniture);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        lecterJSON();
    }

    private void lecterJSON() {
        for (Room r : DatabaseManager.getInstance(this).roomDao().getAll());

    }
}