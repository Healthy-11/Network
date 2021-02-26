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
import com.example.networking.models.Furniture;

import butterknife.ButterKnife;

public class AddFurnitureDialog extends Dialog {
    private Context context;


    public AddFurnitureDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_furniture);
        ButterKnife.bind(this);
        Furniture furniture = null;

        furniture = new Furniture();
        EditText id = (EditText) findViewById(R.id.editTextFId);
        EditText uid = (EditText) findViewById(R.id.editTextFUId);
        EditText name = (EditText) findViewById(R.id.editTextFName);
        EditText category_id = (EditText) findViewById(R.id.editTextFCategoryid);
        EditText category = (EditText) findViewById(R.id.editTextFCategory);
        EditText owner = (EditText) findViewById(R.id.editTextFOwner);
        Button create_btn = (Button) findViewById(R.id.buttonFCreate);

        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!id.getText().toString().isEmpty() && !uid.getText().toString().isEmpty() && !name.getText().toString().isEmpty() && !category_id.getText().toString().isEmpty() && !category.getText().toString().isEmpty() && !owner.getText().toString().isEmpty())
                {
                    String FSid = id.getText().toString();
                    String FSuid = uid.getText().toString();
                    String FSname = name.getText().toString();
                    String FScategory_id = category_id.getText().toString();
                    String FScategory = category.getText().toString();
                    String FSowner = owner.getText().toString();

                    Toast.makeText(context, "Created", Toast.LENGTH_SHORT).show();
                    Furniture item = new Furniture(FSid, FSuid, FSname, FScategory_id,FScategory, FSowner);
                    DatabaseManager.getInstance(context).furnitureDao().insert(item);

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
