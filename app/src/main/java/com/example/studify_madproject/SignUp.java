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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private Button signUp, signIn;
    ImageView image;
    TextView logoText, logoTag;
    private TextInputLayout name, email, password;
    private String em,ps,nm;
    Validation validate;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // TO disable the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        image = findViewById(R.id.logoRounded);
        logoText = findViewById(R.id.logoText);
        logoTag = findViewById(R.id.logoTag);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signIn = findViewById(R.id.existingUser);
        signUp = findViewById(R.id.signUp);
        mAuth=FirebaseAuth.getInstance();
        validate=new Validation(this);
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
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               handleSignup();
            }

            private void handleSignup() {
                em = email.getEditText().getText().toString();
                ps = password.getEditText().getText().toString();
                nm=name.getEditText().getText().toString();

                if(!nm.isEmpty()) {
                    if (validate.checkEm(em) && validate.checkPass(ps)) {
                        mAuth.createUserWithEmailAndPassword(em, ps).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    saveName(user);
                                    user.sendEmailVerification()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(SignUp.this, "Verify Your Email", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                    Toast.makeText(SignUp.this, user.getEmail() + "Signup Success", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Name Empty", Toast.LENGTH_SHORT).show();
                    }
                }
                    else
                    {
                        Toast.makeText(SignUp.this, "Invalid Details Enter Again", Toast.LENGTH_SHORT).show();
                    }
                }

            private void saveName(FirebaseUser user) {
                FirebaseDatabase fbdbase=FirebaseDatabase.getInstance();
                DatabaseReference rootref=fbdbase.getReference();
                DatabaseReference nameref=rootref.child("Users").child(user.getUid()).child("Name");
                nameref.setValue(nm);
            }

        });
    }
}