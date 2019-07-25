package com.example.floatview;

import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private static final int ALERT_WINDOW_PERMISSION_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        finish();
        Intent intent = new Intent(getApplicationContext(), Service1.class);
        startService(intent);
    }
}
