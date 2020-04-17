package com.example.auth;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Sms extends AppCompatActivity
{
    Button smsbtn;
    int count = Cartpage.counter+1;
    FirebaseAuth auth = Login.mAuth;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        smsbtn = findViewById(R.id.fuckthebutton);
        final DatabaseReference counter_Reff = FirebaseDatabase.getInstance().getReference().child("Users");
        userID = auth.getUid();

        smsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                counter_Reff.addListenerForSingleValueEvent(new ValueEventListener() {

                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(userID).exists())
                        {


                            counter_Reff.child(Objects.requireNonNull(auth.getCurrentUser()).getUid()).child("counter").setValue(count);
                            Toast.makeText(Sms.this, "Sign up successful!!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(Sms.this, "User Doesn't exists", Toast.LENGTH_SHORT).show();
                        }
                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Intent Homepage = new Intent(Sms.this, Homepage.class);
                startActivity(Homepage);
            }


        });
}
    }
