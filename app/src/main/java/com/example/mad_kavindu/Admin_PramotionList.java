package com.example.mad_kavindu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin_PramotionList extends AppCompatActivity {

    Button edit_button1, edit_button2, edit_button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__pramotion_list);

        edit_button1 = findViewById(R.id.button13);
        edit_button2 = findViewById(R.id.button9);
        edit_button3 = findViewById(R.id.button11);

        edit_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(getApplicationContext(), Admin_EditPramotionItem.class);
                startActivity(i1);
            }
        });

        edit_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(getApplicationContext(), Admin_EditPramotionItem.class);
                startActivity(i2);
            }
        });

        edit_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(getApplicationContext(), Admin_EditPramotionItem.class);
                startActivity(i3);
            }
        });

    }

}
