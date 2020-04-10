package com.example.oops;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class driver_signup extends AppCompatActivity
{
    MaterialEditText driver_signup_phone, driver_signup_password , driver_signup_radius, driver_signup_community, driver_signup_email, driver_signup_name;
    Button btndriversignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_signup);

        driver_signup_name = (MaterialEditText)findViewById(R.id.driver_signup_name);
        driver_signup_password = (MaterialEditText)findViewById(R.id.driver_signup_password);
        driver_signup_email = (MaterialEditText)findViewById(R.id.driver_signup_email);
        driver_signup_radius = (MaterialEditText)findViewById(R.id.driver_signup_radius);
        driver_signup_community = (MaterialEditText)findViewById(R.id.driver_signup_community);
        driver_signup_phone = (MaterialEditText)findViewById(R.id.driver_signup_phone);
        btndriversignup = (Button) findViewById(R.id.btndriversignup);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("Drivers");

        btndriversignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                final ProgressDialog progress  = new ProgressDialog(driver_signup.this);
                progress.setMessage("Please wait....");
                progress.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //get user info
                        //Checking if the user not exists in database
                        if(dataSnapshot.child(driver_signup_phone.getText().toString()).exists()) {
                            progress.dismiss();
                            Toast.makeText(driver_signup.this, "Phone Number already exists!!", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            progress.dismiss();
                            user_driver user = new user_driver( driver_signup_radius.getText().toString(), driver_signup_email.getText().toString(), driver_signup_community.getText().toString(), driver_signup_name.getText().toString(), driver_signup_password.getText().toString());
                            table_user.child(driver_signup_phone.getText().toString()).setValue(user);
                            Toast.makeText(driver_signup.this, "Sign up successful!!", Toast.LENGTH_SHORT).show();
                            finish();
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }
}