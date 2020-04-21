package com.example.mad_kavindu.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mad_kavindu.R;
import com.example.mad_kavindu.adapters.CustomerListAdapter;

public class HomeScreen extends AppCompatActivity {
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        btn1 =findViewById(R.id.admin_pb);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),admin_panel.class);
                startActivity(i);
            }
        });

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
