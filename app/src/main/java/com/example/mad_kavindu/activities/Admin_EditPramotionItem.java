package com.example.mad_kavindu.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mad_kavindu.R;
import com.example.mad_kavindu.database.MyDatabaseHelper;

public class Admin_EditPramotionItem extends AppCompatActivity implements View.OnClickListener {
    private String rowIDtoEdit = null;
    Button update_promo_button;
    private ImageView imageView;
    private TextView tvOldName, tvOldPrice, tvOldDetails;
    private EditText editName, editPrice, editDetails, editPhotoUrl;
    private MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__edit_pramotion_item);
        rowIDtoEdit = getIntent().getStringExtra(MyDatabaseHelper.ROW_ID);
        editName=findViewById(R.id.editText5Name);
        editPrice=findViewById(R.id.editText6Price);
        editDetails=findViewById(R.id.editText7Details);
        editPhotoUrl=findViewById(R.id.editTextPhotoUrl);
        imageView = findViewById(R.id.imageView);
        tvOldName = findViewById(R.id.textViewName);
        tvOldPrice = findViewById(R.id.textView8Price);
        tvOldDetails = findViewById(R.id.textView5Details);
        update_promo_button = findViewById(R.id.button4Update);
        update_promo_button.setOnClickListener(this);
        myDatabaseHelper = new MyDatabaseHelper(this);
        getOldRecords();
    }

    private void getOldRecords() {
        Cursor cursor = myDatabaseHelper.getOneRecord(rowIDtoEdit);
        if (cursor.moveToFirst()) {
            tvOldName.setText(cursor.getString(1));
            tvOldPrice.setText(cursor.getString(2));
            tvOldDetails.setText(cursor.getString(3));
            String photoUrl = cursor.getString(4);
        }
    }

    @Override
    public void onClick(View v) {
        String newName = editName.getText().toString();
        String newPrice = editName.getText().toString();
        String newDetails = editName.getText().toString();
        String newPhotoUrl = editName.getText().toString();
        if ("".equals(newName) || "".equals(newPrice) || "".equals(newDetails) || "".equals(newPhotoUrl)) {
            Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean updated = myDatabaseHelper.updateRecord(rowIDtoEdit, newName, newPrice, newDetails, newPhotoUrl);
        if (updated) {
            Toast.makeText(this, "Updated successfully", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
        } else Toast.makeText(this, "Updating failed", Toast.LENGTH_SHORT).show();
    }
}
