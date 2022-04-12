package com.example.studify_madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {

    Button signUp, signIn;
    ImageView image;
    TextView logoText, logoTag;
    TextInputLayout email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // TO disable the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        image = findViewById(R.id.logoRounded);
        logoText = findViewById(R.id.logoText);
        logoTag = findViewById(R.id.logoTag);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signIn = findViewById(R.id.existingUser);
        signUp = findViewById(R.id.signUp);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, Authenticate.class);

                Pair pairs[] = new Pair[7];

                pairs[0] = new Pair<View, String>(image, "logo");
                pairs[1] = new Pair<View, String>(logoText, "logoText");
                pairs[2] = new Pair<View, String>(logoTag, "logoTag");
                pairs[3] = new Pair<View, String>(email, "email");
                pairs[4] = new Pair<View, String>(password, "password");
                pairs[5] = new Pair<View, String>(signIn, "button");
                pairs[6] = new Pair<View, String>(signUp, "loginSignUp");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });
    }
}