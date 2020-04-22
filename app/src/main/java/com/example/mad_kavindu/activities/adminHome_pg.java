package com.example.mad_kavindu.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mad_kavindu.R;

public class adminHome_pg extends AppCompatActivity {
Button btn11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_pg);
        btn11=findViewById(R.id.update_it_bg);
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),admin_update_it.class);
                startActivity(i);
            }
        });
    }
}
