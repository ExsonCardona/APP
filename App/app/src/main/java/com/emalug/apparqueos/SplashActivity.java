package com.emalug.apparqueos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.emalug.apparqueos.Activitis.LoginActivity;
import com.emalug.apptaxisv2.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    static  final long SPLASH_SCREEN_DELAY = 3000 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask task  = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

        };

        Timer timer = new Timer();
        timer.schedule(task,SPLASH_SCREEN_DELAY);
    }
}
