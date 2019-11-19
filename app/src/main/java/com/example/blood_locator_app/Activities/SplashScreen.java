package com.example.blood_locator_app.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.blood_locator_app.R;

public class SplashScreen extends AppCompatActivity {
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final Handler h = new Handler();
        h.postDelayed(new Runnable() {

            @Override
            public void run() {
                next();
            }

        }, 2300);

    }
    void next() {
        SharedPreferences pref=getSharedPreferences("users",MODE_PRIVATE);
        String ee=pref.getString("login","notdone");
        if(ee.equals("done")) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this, BoardingPageActivity.class);
            startActivity(intent);
        }
    }
}
