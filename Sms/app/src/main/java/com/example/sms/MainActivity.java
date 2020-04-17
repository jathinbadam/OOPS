package com.example.sms;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompatSideChannelService;
import androidx.core.content.ContextCompat;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;

import android.telephony.SmsManager;

import android.util.Log;
import android.view.Menu;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
    private static final int REQUEST_SMS_PERMISSION = 123;
    private static final String TAG = "ok babhi";
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 123;
    Button sendBtn;
    EditText txtphoneNo;
    EditText txtMessage;
    String phoneNo;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendBtn = (Button) findViewById(R.id.button);
        txtphoneNo = (EditText) findViewById(R.id.textView);
        txtMessage = (EditText) findViewById(R.id.messagetxt);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               requestSmsPermission();
            }
        });
    }

        private void requestSmsPermission()
    {
        phoneNo = txtphoneNo.getText().toString();
        message = txtMessage.getText().toString();
        int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_PHONE_STATE);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.READ_PHONE_STATE))
            {
                Toast.makeText(MainActivity.this,"Permission not granted",Toast.LENGTH_SHORT).show();;
            }
            else
            {
                requestPermission(Manifest.permission.READ_PHONE_STATE, REQUEST_SMS_PERMISSION);
            }
        }
        else
        {
            Toast.makeText(MainActivity.this,"Permission already exists",Toast.LENGTH_SHORT).show();
            sendSMS();
        }
    }

    private void requestPermission(String permissionName, int permissionCode) {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{permissionName}, permissionCode);
    }
    private  void sendSMS() {
        Toast.makeText(MainActivity.this, "Inside SMS send method", Toast.LENGTH_SHORT).show();
        SmsManager smsManager = android.telephony.SmsManager.getDefault();
        PendingIntent sentPI;
        String SENT = "SMS_SENT";
        try {
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(MainActivity.this, "hello hello", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            ;
            Log.d(TAG, e.toString());
            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            switch (requestCode) {
                case REQUEST_SMS_PERMISSION:
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MainActivity.this, "sms permission obtained", Toast.LENGTH_SHORT).show();
                        sendSMS();
                    } else {
                        Toast.makeText(MainActivity.this, "Permission Denied to send confirmation", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }


}