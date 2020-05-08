package com.example.mad_kavindu.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mad_kavindu.R;

public class Login extends AppCompatActivity {

    private EditText emailText;
    private EditText passText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = (EditText) findViewById(R.id.email);
        passText = (EditText) findViewById(R.id.password);
    }

    public void loginbtnclicked (View view) {

        String email = emailText.getText().toString().trim();
        String password = passText.getText().toString().trim();



        if ( (email.equals("admin") || email.equals("cgn") ) && password.equals("123") ){
            Toast.makeText(this,"Admin Logged",Toast.LENGTH_LONG).show();
            Intent i=new Intent(this, adminHome_pg.class);
            startActivity(i);
        }else{
            Toast.makeText(this,"User Logged",Toast.LENGTH_LONG).show();
            Intent i=new Intent(this, Customer_PramotionList.class);
            startActivity(i);
        }

    }

}
