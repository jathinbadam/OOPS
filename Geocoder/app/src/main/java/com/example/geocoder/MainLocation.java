package com.example.geocoder;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainLocation {

    private static final String TAG = "Geocoding";

    public static double[] getAddressFromLocation(final String locationAddress,
                                              final Context context) {

                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                double[] coordinates = new double[2];


        try {
                    List
                            addressList = geocoder.getFromLocationName(locationAddress, 1);
                    if (addressList != null && addressList.size() > 0) {
                        Address address = (Address) addressList.get(0);

                         DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("Coordinates");
                         Location location = new Location();
                         location.setLatitude(address.getLatitude());
                         location.setLongitude(address.getLongitude());
                         coordinates[0] = address.getLatitude();
                         coordinates[1] = address.getLongitude();
                         reff.push().setValue(location);

                    }
                } catch (IOException e) {
                    Log.e(TAG, "Unable to connect to Geocoder", e);
                }
                finally {
                        return coordinates;
                        }

                }

        };

