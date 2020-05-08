package com.example.mad_kavindu.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_kavindu.R;
import com.example.mad_kavindu.adapters.CustomerListAdapter;
import com.example.mad_kavindu.database.MyDatabaseHelper;
import com.example.mad_kavindu.listeners.CustemerListClickListener;

public class Customer_PramotionList extends AppCompatActivity implements CustemerListClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__pramotion_list);
        RecyclerView recyclerView = findViewById(R.id.customerlist_recyclerview);
        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Cursor allRecordCursor = myDatabaseHelper.getCustomerAllRecords();
        CustomerListAdapter customerListAdapter = new CustomerListAdapter(allRecordCursor, this, this);
        recyclerView.setAdapter(customerListAdapter);
    }


    @Override
    public void onCustomerListAddToCartClicked(String rowId, String quantity) {
        Toast.makeText(this, "Customer clicked Add To Cart\nRowID= " + rowId + "\nwith Quantity= " + quantity, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCustomerListOrderClicked(String rowId, String quantity) {
        Toast.makeText(this, "Customer clicked ORDER\nRowID= " + rowId + "\nwith Quantity= " + quantity, Toast.LENGTH_SHORT).show();
    }

}

