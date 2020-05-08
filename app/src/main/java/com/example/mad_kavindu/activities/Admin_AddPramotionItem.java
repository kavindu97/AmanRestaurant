package com.example.mad_kavindu.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mad_kavindu.R;
import com.example.mad_kavindu.SharedPref;
import com.example.mad_kavindu.database.MyDatabaseHelper;

public class Admin_AddPramotionItem extends AppCompatActivity implements View.OnClickListener {

    private Button buttonAddPromotion;
    private EditText editTextName, editTextPrice, editTextDetails, editTextPhotoUrl;
    private MyDatabaseHelper myDatabaseHelper;
    boolean inserted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_pramotion_item);
        buttonAddPromotion = findViewById(R.id.button_addPromotion);
        editTextName = findViewById(R.id.editText_name);
        editTextPrice = findViewById(R.id.editText_price);
        editTextDetails = findViewById(R.id.editText_details);
        buttonAddPromotion = findViewById(R.id.button_addPromotion);
        editTextPhotoUrl = findViewById(R.id.editText_photoUrl);
        buttonAddPromotion.setOnClickListener(this);
        myDatabaseHelper = new MyDatabaseHelper(this);


    }

    @Override
    public void onClick(View v) {
        String name = editTextName.getText().toString();
        String price = editTextPrice.getText().toString();
        String details = editTextDetails.getText().toString();
        String photoUrl = editTextPhotoUrl.getText().toString();
        if ("".equals(name) || "".equals(price) || "".equals(details) || "".equals(photoUrl)) {
            Toast.makeText(this, "all fields are required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (SharedPref.getString(Admin_AddPramotionItem.this,"id").equals("0")){
             inserted = myDatabaseHelper.insertSellerData(name, price, details, photoUrl);
             inserted = myDatabaseHelper.insertData(name, price, details, photoUrl);
        }
        if (inserted) {
            Toast.makeText(this, "Insertion successful", Toast.LENGTH_SHORT).show();
            editTextName.getText().clear();
            editTextPrice.getText().clear();
            editTextDetails.getText().clear();
            editTextPhotoUrl.getText().clear();
            setResult(RESULT_OK);
        } else Toast.makeText(this, "Insertion failed", Toast.LENGTH_SHORT).show();

    }
}
