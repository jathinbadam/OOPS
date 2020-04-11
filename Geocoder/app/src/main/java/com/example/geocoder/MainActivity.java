package com.example.geocoder;

import androidx.annotation.NonNull;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


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

        final DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("Location");

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

                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {

                        com.example.geocoder.Location location = new com.example.geocoder.Location();
                        location.setLatitude_pick(coordinates_pick[0]);
                        location.setLongitude_pick(coordinates_pick[1]);
                        location.setLatitude_drop(coordinates_drop[0]);
                        location.setLongitude_drop(coordinates_drop[1]);
                        reff.child("799718878").setValue(location);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Intent maps = new Intent(MainActivity.this,maps.class);
                startActivity(maps);

            }
        });

    }

    }
