package com.example.auth;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class signout extends AppCompatActivity {
    private static final String TAG = "SignInActivity";
    Button signOutButton;
    EditText gmailtext;
    GoogleSignInClient mGoogleSignInClient = Login.mGoogleSignInClient;
    FirebaseAuth auth = Login.mAuth;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signout);
        signOutButton = (Button) findViewById(R.id.signOutButton);
        gmailtext = (EditText) findViewById(R.id.gmailedittext);
        gmailtext.setText(Objects.requireNonNull(auth.getCurrentUser()).getUid());

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

    }
    private void signOut(){

        auth.signOut();
        Log.d(TAG, String.valueOf(mGoogleSignInClient.getInstanceId()));
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent Login = new Intent(signout.this, Login.class);
                        startActivity(Login);
                    }
                });
    }
}
