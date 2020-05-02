package com.example.mad_kavindu.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mad_kavindu.R;
import com.example.mad_kavindu.listeners.AdminListClickListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SellerListAdapter extends RecyclerView.Adapter<SellerListAdapter.AdminViewHolder> {
    private Cursor cursor;
    private Context context;
    private AdminListClickListener listener;

    public SellerListAdapter(Cursor cursor, Context context, AdminListClickListener listener) {
        this.listener = listener;
        this.cursor = cursor;
        this.context = context;
    }

    @NonNull
    @Override
    public AdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.adminlist_row_item, parent, false);
        return new AdminViewHolder(rowLayout, listener, cursor);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminViewHolder holder, int position) {
        try {
            cursor.moveToPosition(position);
            holder.name.setText(cursor.getString(1));
            holder.price.setText(cursor.getString(2));
            holder.details.setText(cursor.getString(3));
            String photoUrl = cursor.getString(4);
        } catch (Exception e) {
        }
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    static class AdminViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, price, details;
        Button buttonDelete, buttonEdit;

        public AdminViewHolder(@NonNull View itemView, final AdminListClickListener listener, final Cursor cursor) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.adminlist_name);
            price = itemView.findViewById(R.id.adminlist_price);
            details = itemView.findViewById(R.id.adminlist_details);
            buttonDelete = itemView.findViewById(R.id.adminlist_button_delete);
            buttonEdit = itemView.findViewById(R.id.adminlist_button_edit);

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cursor.moveToPosition(getAdapterPosition());
                    listener.onAdminListDeleteClicked(cursor.getString(0));
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
