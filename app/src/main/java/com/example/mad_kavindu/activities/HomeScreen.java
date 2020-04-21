package com.example.mad_kavindu.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mad_kavindu.R;
import com.example.mad_kavindu.adapters.CustomerListAdapter;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }

    public void cutomerClick(View v) {
        Intent i=new Intent(this, Customer_PramotionList.class);
        startActivity(i);
    }

    public void adminClick(View v) {
        Intent i=new Intent(this, Admin_PramotionList.class);
        startActivity(i);
    }
}
