package com.example.mad_kavindu.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_kavindu.R;
import com.example.mad_kavindu.listeners.CustemerListClickListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.CustomerViewHolder> {
    private Cursor cursor;
    private Context context;
    private CustemerListClickListener listener;

    public CustomerListAdapter(Cursor cursor, Context context, CustemerListClickListener listener) {
        this.listener = listener;
        this.cursor = cursor;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.customerlist_row_item, parent, false);
        return new CustomerViewHolder(rowLayout, listener, cursor);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
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

    static class CustomerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, price, details;
        Button buttonAddToCart, buttonOrder;
        EditText editQuantity;
        Cursor cursor;
        CustemerListClickListener listener;


        public CustomerViewHolder(@NonNull View itemView, final CustemerListClickListener listener, final Cursor cursor) {
            super(itemView);
            this.cursor = cursor;
            this.listener = listener;
            imageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.customerlist_name);
            price = itemView.findViewById(R.id.cutomerlist_price);
            details = itemView.findViewById(R.id.customerlist_details);
            buttonAddToCart = itemView.findViewById(R.id.customerlist_addtocart);
            buttonOrder = itemView.findViewById(R.id.customerlist_order);
            editQuantity = itemView.findViewById(R.id.customerlist_quantity);
            buttonOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!editQuantity.getText().toString().equals("")) {
                        cursor.moveToPosition(getAdapterPosition());
                        String rowID = cursor.getString(0);
                        listener.onCustomerListOrderClicked(rowID, editQuantity.getText().toString());
                    } else {
                        Toast.makeText(v.getContext(), "Select quantity", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            buttonAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!editQuantity.getText().toString().equals("")) {
                        cursor.moveToPosition(getAdapterPosition());
                        String rowID = cursor.getString(0);
                        listener.onCustomerListAddToCartClicked(rowID, editQuantity.getText().toString());
                    } else {
                        Toast.makeText(v.getContext(), "Select quantity", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
    }


}
