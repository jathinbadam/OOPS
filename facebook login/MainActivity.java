package com.example.facebook_login;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private CallbackManager mCallbackManager;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth mAuth;
    /*
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAuth = FirebaseAuth.getInstance();

//        private FirebaseAuth mAuth;
// ...
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

//        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);



//        mCallbackManager = CallbackManager.Factory.create();
//
//        LoginButton loginButton = findViewById(R.id.login_button);
//
//        //Setting the permission that we need to read
//        loginButton.setReadPermissions("public_profile","email", "user_birthday");
//
//        //Registering callback!
//        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                //Sign in completed
//                Log.i(TAG, "onSuccess: logged in successfully");
//
//                //handling the token for Firebase Auth
//                handleFacebookAccessToken(loginResult.getAccessToken());
//
//                //Getting the user information
//                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(JSONObject object, GraphResponse response) {
//                        // Application code
//                        Log.i(TAG, "onCompleted: response: " + response.toString());
//                        try {
//                            String email = object.getString("email");
//                            String birthday = object.getString("birthday");
//
//                            Log.i(TAG, "onCompleted: Email: " + email);
//                            Log.i(TAG, "onCompleted: Birthday: " + birthday);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Log.i(TAG, "onCompleted: JSON exception");
//                        }
//                    }
//                });
//
//                Bundle parameters = new Bundle();
//                parameters.putString("fields", "id,name,email,gender,birthday");
//                request.setParameters(parameters);
//                request.executeAsync();
//
//            }
//
//            @Override
//            public void onCancel() {
//                Log.d(TAG, "facebook:onCancel");
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                Log.d(TAG, "facebook:onError", error);
//            }
//        });
//
//    }

// ...
//        @Override
//        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);
//
//            // Pass the activity result back to the Facebook SDK
//            mCallbackManager.onActivityResult(requestCode, resultCode, data);
//        }

//    private void handleFacebookAccessToken(AccessToken token) {
//        Log.d(TAG, "handleFacebookAccessToken:" + token);
//
//        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
//        mFirebaseAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
////                        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
//                        if (task.isSuccessful()) {
////                          if (currentUser != null){
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCredential:success");
//                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
//                            Log.i(TAG, "onComplete: login completed with user: " + user.getDisplayName());
//
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithCredential:failure", task.getException());
//                            Toast.makeText(MainActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//
//                        // ...
//                    }
//                });
//    }



// Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });


}




    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        FirebaseUser user_ = FirebaseAuth.getInstance().getCurrentUser();
                        if (task.isSuccessful()) {
//                        if(user_ != null){
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
        if (currentUser != null) {

            Log.i(TAG, "onStart: Someone logged in <3");
        } else {
            Toast.makeText(MainActivity.this, "Authentication not failed.",
                    Toast.LENGTH_SHORT).show();
            Log.i(TAG, "onStart: No one logged in :/");
        }
    }}


