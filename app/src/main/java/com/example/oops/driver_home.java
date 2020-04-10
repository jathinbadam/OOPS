package com.example.oops;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class driver_home extends AppCompatActivity {

    Button btndriver_signin, btndriver_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home);
        btndriver_signin = (Button)findViewById(R.id.btndriver_signin);
        btndriver_signup = (Button)findViewById((R.id.btndriver_signup));

        btndriver_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent driver_signin = new Intent(driver_home.this, driver_signin.class);
                startActivity(driver_signin);
            }
        });

        btndriver_signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent driver_signup = new Intent(driver_home.this, driver_signup.class);
                startActivity(driver_signup);
            }
        });

    }
}
