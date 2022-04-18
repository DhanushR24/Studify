package com.example.studify_madproject;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Pair;


public class MainActivity extends AppCompatActivity {

    ImageView image;
    ProgressBar logoText;
    private static int SPLASH_SCREEN_TIME = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().hide();
        }

        image = findViewById(R.id.logoRounded);
        logoText = findViewById(R.id.progressBar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, Authenticate.class);

                        Pair pairs[] = new Pair[2];

                        pairs[0] = new Pair<View, String>(image, "logo");
                        pairs[1] = new Pair<View, String>(logoText, "logoText");

                        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                        startActivity(intent, activityOptions.toBundle());

                        finish();
                    }
                }
        , SPLASH_SCREEN_TIME);
    }
}