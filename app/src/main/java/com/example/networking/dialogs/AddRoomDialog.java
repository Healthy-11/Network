package com.example.networking.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.networking.sql.DatabaseManager;
import com.example.networking.R;
import com.example.networking.models.Room;

import butterknife.ButterKnife;

public class AddRoomDialog extends Dialog {
    private Context context;


    public AddRoomDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_room);
        ButterKnife.bind(this);
        Room room = null;

        room = new Room();
        EditText id = (EditText) findViewById(R.id.editTextRId);
        EditText uid = (EditText) findViewById(R.id.editTextRUId);
        EditText name = (EditText) findViewById(R.id.editTextRName);
        EditText owner = (EditText) findViewById(R.id.editTextROwner);
        Button create_btn = (Button) findViewById(R.id.buttonRCreate);

        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!id.getText().toString().isEmpty() && !uid.getText().toString().isEmpty() && !name.getText().toString().isEmpty() && !owner.getText().toString().isEmpty())
                {
                    String RSid = id.getText().toString();
                    String RSuid = uid.getText().toString();
                    String RSname = name.getText().toString();
                    String RSowner = owner.getText().toString();

                    Toast.makeText(context, "Created", Toast.LENGTH_SHORT).show();
                    Room item = new Room(RSid, RSuid, RSname, RSowner);
                    DatabaseManager.getInstance(context).roomDao().insert(item);

                    dismiss();
                }else{
                    Toast.makeText(context, "Please fill informations", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = this;
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.90);
        dialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
