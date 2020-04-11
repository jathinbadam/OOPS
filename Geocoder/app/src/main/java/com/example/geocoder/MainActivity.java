package com.example.geocoder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {
    public static double[] coordinates_pick = new double[2];
    public static double[] coordinates_drop = new double[2];
    Button addressButton;
    TextView addressTV;
    TextView latLongTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addressTV = (TextView) findViewById(R.id.addressTV);
        latLongTV = (TextView) findViewById(R.id.latLongTV);

        addressButton = (Button) findViewById(R.id.addressButton);
        addressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                EditText editTextpick = (EditText) findViewById(R.id.addresspick);
                EditText editTextdrop = (EditText) findViewById(R.id.addressdrop);
                String addresspick = editTextpick.getText().toString();
                String addressdrop = editTextdrop.getText().toString();

                MainLocation locationAddress = new MainLocation ();
                coordinates_pick =  locationAddress.getAddressFromLocation(addresspick,
                        getApplicationContext());
                coordinates_drop =  locationAddress.getAddressFromLocation(addressdrop,
                        getApplicationContext());
                Intent maps = new Intent(MainActivity.this,maps.class);
                startActivity(maps);

            }
        });

    }

    }
