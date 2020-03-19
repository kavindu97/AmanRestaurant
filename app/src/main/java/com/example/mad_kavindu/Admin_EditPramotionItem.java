package com.example.mad_kavindu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin_EditPramotionItem extends AppCompatActivity {

    Button update_promo_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__edit_pramotion_item);

        update_promo_button = findViewById(R.id.button4);


        update_promo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4 = new Intent(getApplicationContext(), Admin_PramotionList.class);
                startActivity(i4);
            }
        });
    }
}
