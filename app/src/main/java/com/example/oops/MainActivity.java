package com.example.oops;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btncustomer, btndriver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btncustomer = (Button)findViewById(R.id.btncustomer);
        btndriver = (Button)findViewById(R.id.btndriver);

        btndriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent driver_home = new Intent(MainActivity.this,driver_home.class);
                startActivity(driver_home);
            }
        });

        btncustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent customer_home = new Intent(MainActivity.this,customer_home.class);
                startActivity(customer_home);
            }
        });

    }
}
