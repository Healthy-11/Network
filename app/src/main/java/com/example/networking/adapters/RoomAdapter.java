package com.example.networking.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.networking.R;
import com.example.networking.models.Room;
import com.example.networking.sql.DatabaseManager;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {
    private final Context context;
    List<Room> list;

    public RoomAdapter(Context context) {
        this.context = context;
        this.list = DatabaseManager.getInstance(context).roomDao().getAll();
    }

    @NonNull
    @Override
    public RoomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.room_row,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.id.setText("Id : " + list.get(position).getServerId());
        holder.uid.setText("UId : " + list.get(position).getUid());
        holder.name.setText("Name : " + list.get(position).getName());
        holder.owner.setText("Owner : " + list.get(position).getOwner());


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Please confirm...");
                builder.setMessage("Delete this room ?");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Room d = list.get(holder.getAdapterPosition());
                        DatabaseManager.getInstance(context).roomDao().delete(d);
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
        TextView owner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.textViewRoomId);
            uid = (TextView) itemView.findViewById(R.id.textViewRoomUid);
            name = (TextView) itemView.findViewById(R.id.textViewRoomName);
            owner = (TextView) itemView.findViewById(R.id.textViewRoomOwner);

        }

    }
}
