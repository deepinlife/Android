package com.deepinlife.med_det;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {
Intent myintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        myintent = new Intent(this, HomePage.class);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                startActivity(myintent);
                finish();
            }
        }, 2000);
    }
}
