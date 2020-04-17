package com.example.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Orderpage extends AppCompatActivity {

    Button regular,occasional;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderpage);
        regular = (Button)findViewById(R.id.regularbtn);
        occasional = (Button)findViewById(R.id.occasionalbtn);

        occasional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Occasional = new Intent(Orderpage.this, Occasional.class);
                startActivity(Occasional);
            }
        });
    }
}
