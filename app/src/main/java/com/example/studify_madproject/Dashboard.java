package com.example.studify_madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.studify_madproject.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity {

    BottomNavigationView nav;
    public static FirebaseAuth mAuth;
    public static FirebaseUser us;
    public static String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);
        replaceFragment(new HomeFragment());

        mAuth=FirebaseAuth.getInstance();
        us=mAuth.getCurrentUser();
        FirebaseDatabase fdb=FirebaseDatabase.getInstance();
        DatabaseReference rootref = fdb.getReference();

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference nameref = rootref.child("Users").child(user.getUid()).child("Name");

        nameref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name = snapshot.getValue().toString();
                // Toast.makeText(Dashboard.this, "Welcome"+name+"!", Toast.LENGTH_SHORT).show();

                Snackbar.make(findViewById(android.R.id.content), "Hey there, " + name, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        nav = findViewById(R.id.bottomNavigationView);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.navigation_home:
                        replaceFragment(new HomeFragment());
                        return true;

                    case R.id.navigation_notes:
                        replaceFragment(new NotesFragment());
                        return true;

                    case R.id.navigation_reminder:
                        replaceFragment(new ReminderFragment());
                        return true;

                    case R.id.navigation_profile:
                        replaceFragment(new profileFragment());
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FrameContainer, fragment);
        fragmentTransaction.commit();
    }
}