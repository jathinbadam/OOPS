package com.example.geocoder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.LocationManager;
import android.location.LocationListener;
import android.os.Bundle;
import android.text.TextUtils;
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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends Activity implements LocationListener {
    private static final String TAG = "location page";
    public static double[] coordinates_pick = new double[2];
    public static double[] coordinates_drop = new double[2];
    public static double[] coordinates_current = new double[2];
    Button addressButton;
    TextView addressTV;
    TextView latLongTV;
    private String provider;
    private LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addressTV = (TextView) findViewById(R.id.addressTV);
        latLongTV = (TextView) findViewById(R.id.latLongTV);
        addressButton = (Button) findViewById(R.id.addressButton);
        final DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("Location");
        Log.d(TAG, "Click Successfull!!!1");
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Log.d(TAG, "Click Successfull!!!2");
        android.location.Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
            Log.d(TAG, "Location not available");
        }
        Log.d(TAG, "Click Successfull!!!3");
        addressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                EditText editTextpick = (EditText) findViewById(R.id.addresspick);
                EditText editTextdrop = (EditText) findViewById(R.id.addressdrop);
                String addresspick = editTextpick.getText().toString();
                String addressdrop = editTextdrop.getText().toString();
                String pickup = editTextpick.getText().toString();
                String drop = editTextdrop.getText().toString();

                MainLocation locationAddress = new MainLocation();
                if(TextUtils.isEmpty(pickup))
                {
                    coordinates_pick = coordinates_current;
                }
                else
                {
                    coordinates_pick = locationAddress.getAddressFromLocation(addresspick,
                            getApplicationContext());
                }
                if(TextUtils.isEmpty(drop))
                {
                    Log.d(TAG,"holaaaaaaaaaaaaaaaaaaaaaa");
                    coordinates_drop = coordinates_current;
                }
                else
                {
                    coordinates_drop = locationAddress.getAddressFromLocation(addressdrop,
                            getApplicationContext());
                }

                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        com.example.geocoder.Location location = new com.example.geocoder.Location();
                        location.setLatitude_pick(coordinates_pick[0]);
                        location.setLongitude_pick(coordinates_pick[1]);
                        location.setLatitude_drop(coordinates_drop[0]);
                        location.setLongitude_drop(coordinates_drop[1]);
                        reff.child("7991887").setValue(location);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Intent maps = new Intent(MainActivity.this, maps.class);
                startActivity(maps);

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(android.location.Location location) {
        coordinates_current[0] =  (location.getLatitude());
        coordinates_current[1] = (location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }

    }
