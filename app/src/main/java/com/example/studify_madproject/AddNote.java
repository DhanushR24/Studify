package com.example.studify_madproject;

import android.os.Bundle;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.studify_madproject.databinding.ActivityAddNoteBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddNote extends AppCompatActivity {

    FloatingActionButton save;
    EditText title, content;
    ProgressBar saveProgress;

    FirebaseFirestore fStore;
    FirebaseUser user;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        user = fAuth.getCurrentUser();

        Toolbar tl = findViewById(R.id.toolbar);
        save = findViewById(R.id.save);

        title = findViewById(R.id.addNoteTitle);
        content = findViewById(R.id.addNoteContent);
        saveProgress = findViewById(R.id.saveProgress);

        setSupportActionBar(tl);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProgress.setVisibility(View.VISIBLE);
                String noteTitle = title.getText().toString();
                String noteContent = content.getText().toString();

                if(noteTitle.isEmpty() || noteContent.isEmpty()) {
                    Snackbar.make(view, "Note title and content is essential", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    saveProgress.setVisibility(View.INVISIBLE);
                    return;
                }
                DocumentReference docRef = fStore.collection("notes").document(user.getUid()).collection("myNotes").document();

                Map<String, Object> note = new HashMap<>();
                note.put("title", noteTitle);
                note.put("content", noteContent);

                docRef.set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Snackbar.make(view, "Note saved", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                        onBackPressed();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(view, "Something went wrong :(", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();


                        saveProgress.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}