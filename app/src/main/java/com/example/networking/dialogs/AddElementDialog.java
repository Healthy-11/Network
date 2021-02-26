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
import com.example.networking.models.Element;

import butterknife.ButterKnife;

public class AddElementDialog extends Dialog {
    private Context context;


    public AddElementDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_element);
        ButterKnife.bind(this);
        Element element = null;

        element = new Element();
        EditText id = (EditText) findViewById(R.id.editTextEId);
        EditText uid = (EditText) findViewById(R.id.editTextEUId);
        EditText name = (EditText) findViewById(R.id.editTextEName);
        EditText category_id = (EditText) findViewById(R.id.editTextECategoryid);
        EditText owner = (EditText) findViewById(R.id.editTextEOwner);
        Button create_btn = (Button) findViewById(R.id.buttonECreate);

        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!id.getText().toString().isEmpty() && !uid.getText().toString().isEmpty() && !name.getText().toString().isEmpty() && !category_id.getText().toString().isEmpty() && !owner.getText().toString().isEmpty())
                {
                    String ESid = id.getText().toString();
                    String ESuid = uid.getText().toString();
                    String ESname = name.getText().toString();
                    String EScategory_id = category_id.getText().toString();
                    String ESowner = owner.getText().toString();

                    Toast.makeText(context, "Created", Toast.LENGTH_SHORT).show();
                    Element item = new Element(ESid, ESuid, ESname, EScategory_id, ESowner);
                    DatabaseManager.getInstance(context).elementDao().insert(item);

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

