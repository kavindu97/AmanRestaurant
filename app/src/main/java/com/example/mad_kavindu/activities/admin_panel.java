package com.example.mad_kavindu.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mad_kavindu.R;

public class admin_panel extends AppCompatActivity {
    Button updt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        updt1 =findViewById(R.id.update_it);
        updt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),update_admin_it.class);
                startActivity(i);
            }
        });
    }
}
