package com.example.studify_madproject;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Authenticate extends AppCompatActivity {

    private Button signUp, signIn;
    ImageView image;
    TextView logoText, logoTag;
    private TextInputLayout email, password;
    Validation validate;
    private String em,ps;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        // Remove the status bar (Top)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        image = findViewById(R.id.logoRounded);
        logoText = findViewById(R.id.logoText);
        logoTag = findViewById(R.id.logoTag);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signIn = findViewById(R.id.signIn);
        signUp = findViewById(R.id.newUser);
        validate=new Validation(this);
        mAuth=FirebaseAuth.getInstance();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Authenticate.this, SignUp.class);

                Pair pairs[] = new Pair[7];

                pairs[0] = new Pair<View, String>(image, "logo");
                pairs[1] = new Pair<View, String>(logoText, "logoText");
                pairs[2] = new Pair<View, String>(logoTag, "logoTag");
                pairs[3] = new Pair<View, String>(email, "email");
                pairs[4] = new Pair<View, String>(password, "password");
                pairs[5] = new Pair<View, String>(signIn, "button");
                pairs[6] = new Pair<View, String>(signUp, "loginSignUp");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Authenticate.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLoginBtn();
            }

            private void handleLoginBtn() {
                em=email.getEditText().getText().toString();
                ps=password.getEditText().getText().toString();
                if(validate.checkEm(em) && validate.checkPass(ps)) {
                Toast.makeText(Authenticate.this, "Valid Credentials", Toast.LENGTH_SHORT).show();
                mAuth.signInWithEmailAndPassword(em,ps).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Authenticate.this, "Signin SuccessFull", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(Authenticate.this, "Invalid Login Details", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                }
            }

        });
    }
}