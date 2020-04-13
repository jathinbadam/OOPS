package com.example.oops;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
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

public class driver_signin extends AppCompatActivity {
    private static final String TAG = "user_driver";
    EditText edtdriver_phone, edtdriver_password;
    Button btndriversignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_signin);
        edtdriver_password = (MaterialEditText) findViewById(R.id.edtdriver_password);
        edtdriver_phone = (MaterialEditText) findViewById(R.id.edtdriver_phone);
        btndriversignin = (Button) findViewById(R.id.btndriversignin);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("Drivers");

        btndriversignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progress = new ProgressDialog(driver_signin.this);
                progress.setMessage("Please wait....");
                progress.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        Log.d(TAG, "Volley requester " + dataSnapshot.child(edtdriver_phone.getText().toString()));
                        if(dataSnapshot.child(edtdriver_phone.getText().toString()).exists())
                        {
                            progress.dismiss();
                            Toast.makeText(driver_signin.this, "Perfect!!", Toast.LENGTH_SHORT).show();
                            user_driver user = dataSnapshot.child(edtdriver_phone.getText().toString()).getValue(user_driver.class);
                            Log.d(TAG, "Volley requester " + user.getPassword());
                            if (user.getPassword().equals(edtdriver_password.getText().toString())) {
                                Toast.makeText(driver_signin.this, "Sign in successful!!", Toast.LENGTH_SHORT).show();//
                            } else
                                {
                                Toast.makeText(driver_signin.this, "Wrong Password!!", Toast.LENGTH_SHORT).show();
                            }


                        }

                        else
                        {
                            progress.dismiss();
                            Toast.makeText(driver_signin.this, "User Doesn't exists", Toast.LENGTH_SHORT).show();
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

