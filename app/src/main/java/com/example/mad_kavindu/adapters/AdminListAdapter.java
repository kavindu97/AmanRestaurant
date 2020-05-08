package com.example.mad_kavindu.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_kavindu.R;
import com.example.mad_kavindu.database.MyDatabaseHelper;
import com.example.mad_kavindu.listeners.AdminListClickListener;

public class AdminListAdapter extends RecyclerView.Adapter<AdminListAdapter.AdminViewHolder> {
    private Cursor cursor;
    private Context context;
    String photoUrl;
    private AdminListClickListener listener;
    MyDatabaseHelper databaseHelper;

    public AdminListAdapter(Cursor cursor, Context context, AdminListClickListener listener) {
        this.listener = listener;
        this.cursor = cursor;
        this.context = context;
        databaseHelper = new MyDatabaseHelper(context);
    }

    @NonNull
    @Override
    public AdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_list_custom_design, parent, false);
        return new AdminViewHolder(rowLayout, listener, cursor);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdminViewHolder holder, int position) {
        try {
            cursor.moveToPosition(position);
            holder.name.setText(cursor.getString(1));
            holder.price.setText(cursor.getString(2));
            holder.details.setText(cursor.getString(3));
             photoUrl = cursor.getString(4);
        } catch (Exception e) {
        }

        holder.buttoninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseHelper.insertCustomerData(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
                Cursor search = databaseHelper.SearchData(cursor.getString(0));
                if (search.getCount()>0){
                    databaseHelper.deletesellerrecord(cursor.getString(0));
                    Toast.makeText(context, "run code", Toast.LENGTH_SHORT).show();
                }
             holder.buttoninsert.setClickable(false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    static class AdminViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, price, details;
        Button buttonDelete, buttonEdit,buttoninsert;
        String photourl;

        public AdminViewHolder(@NonNull View itemView, final AdminListClickListener listener, final Cursor cursor) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView2);
            name = itemView.findViewById(R.id.textView);
            price = itemView.findViewById(R.id.textView5);
            details = itemView.findViewById(R.id.textView8);
            buttonDelete = itemView.findViewById(R.id.button2);
            buttonEdit = itemView.findViewById(R.id.update_it_bg);
            buttoninsert = itemView.findViewById(R.id.button3);

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cursor.moveToPosition(getAdapterPosition());
                    listener.onAdminListDeleteClicked(cursor.getString(0));
                }
            });
            buttoninsert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            buttonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cursor.moveToPosition(getAdapterPosition());
                    listener.onAdminListEditClicked(cursor.getString(0));
                }
            });
        }
    }
}
