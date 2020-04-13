package com.example.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText email, password;
    Button signin, changepassword, signup;
    private FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.loginemail);
        password = (EditText) findViewById(R.id.loginpassword);
        signin = (Button) findViewById(R.id.loginbtn);
        signup = (Button) findViewById(R.id.registerbtn);
        changepassword = (Button) findViewById(R.id.forgotpasswordbtn);
        mauth = FirebaseAuth.getInstance();
//        googlesignin = (Button) findViewById(R.id.googleloginbtn);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginuser();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Register = new Intent(Login.this, Register.class);
                startActivity(Register);

            }
        });





    }


    protected void onStart() {

        super.onStart();
        if (mauth.getCurrentUser() != null) {

        }
    }

    private void loginuser()
    {
        final String EMAIL = email.getText().toString();
        final String PASSWORD = password.getText().toString();

        mauth.signInWithEmailAndPassword(EMAIL,PASSWORD)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
//                            Intent MapsActivity = new Intent(Login.this,MapsActivity.class);
//                            startActivity(MapsActivity);
                            Toast.makeText(Login.this, "User Login Successful", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Intent Register = new Intent(Login.this, Register.class);
                            startActivity(Register);
                        }
                    }
                });

    }
}

