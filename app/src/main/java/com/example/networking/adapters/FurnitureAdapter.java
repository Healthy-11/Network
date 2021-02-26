package com.example.networking.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.networking.R;
import com.example.networking.models.Furniture;
import com.example.networking.sql.DatabaseManager;

import java.util.List;

public class FurnitureAdapter extends RecyclerView.Adapter<FurnitureAdapter.ViewHolder> {
    private final Context context;
    List<Furniture> list;

    public FurnitureAdapter(Context context) {
        this.context = context;
        this.list = DatabaseManager.getInstance(context).furnitureDao().getAll();
    }

    @NonNull
    @Override
    public FurnitureAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.furniture_row,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.id.setText("Id : " + list.get(position).getServerId());
        holder.uid.setText("UId : " + list.get(position).getUid());
        holder.name.setText("Name : " + list.get(position).getName());
        holder.category_id.setText("Category id : " + list.get(position).getCategory_id());
        holder.category.setText("Category : " + list.get(position).getCategory());
        holder.owner.setText("Owner : " + list.get(position).getOwner());


        switch (list.get(position).getCategory_id()) {
            case "-1":
            case "2" :
                holder.icon.setImageResource(R.drawable.house_icon);
                break;
            case "3":
                holder.icon.setImageResource(R.drawable.elec_icon);
                break;
            case "4":
                holder.icon.setImageResource(R.drawable.toilet_icon);
                break;
            case "5":
                holder.icon.setImageResource(R.drawable.clean_icon);
                break;
        }


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Please confirm...");
                builder.setMessage("Delete this furniture ?");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Furniture d = list.get(holder.getAdapterPosition());
                        DatabaseManager.getInstance(context).furnitureDao().delete(d);
                        list.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, list.size());

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView uid;
        TextView name;
        TextView category_id;
        TextView category;
        TextView owner;
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.textViewFurnitureId);
            uid = (TextView) itemView.findViewById(R.id.textViewFurnitureUid);
            name = (TextView) itemView.findViewById(R.id.textViewFurnitureName);
            category_id = (TextView) itemView.findViewById(R.id.textViewFurnitureCategoryId);
            category = (TextView) itemView.findViewById(R.id.textViewFurnitureCategory);
            owner = (TextView) itemView.findViewById(R.id.textViewFurnitureOwner);
            icon = (ImageView) itemView.findViewById(R.id.imageViewFurnitureCategory);

        }

    }
}
