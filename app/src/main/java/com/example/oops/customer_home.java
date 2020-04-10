package com.example.oops;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class customer_home extends AppCompatActivity {


    Button btncustomer_signin, btncustomer_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);
        btncustomer_signin = (Button)findViewById(R.id.btncustomer_signin);
        btncustomer_signup = (Button)findViewById((R.id.btncustomer_signup));

        btncustomer_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent customer_signin = new Intent(customer_home.this, customer_signin.class);
                startActivity(customer_signin);
            }
        });
    }
}
