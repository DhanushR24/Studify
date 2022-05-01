package com.example.studify_madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditNote extends AppCompatActivity {

    Intent intent;
    EditText editTitle, editContent;
    Toolbar tl;
    FloatingActionButton fab;

    FirebaseFirestore fStore;
    FirebaseUser user;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        tl = findViewById(R.id.toolbar);
        setSupportActionBar(tl);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        user = fAuth.getCurrentUser();

        intent = getIntent();
        String noteTitle = intent.getStringExtra("title");
        String noteContent = intent.getStringExtra("content");

        editTitle = findViewById(R.id.editNoteTitle);
        editContent = findViewById(R.id.editNoteContent);

        editTitle.setText(noteTitle);
        editContent.setText(noteContent);

        fab = findViewById(R.id.saveEdit);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noteTitle = editTitle.getText().toString();
                String noteContent = editContent.getText().toString();

                if (noteTitle.isEmpty() || noteContent.isEmpty()) {
                    Snackbar.make(view, "Note title and content is essential", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }
                DocumentReference docRef = fStore.collection("notes").document(user.getUid()).collection("myNotes").document(intent.getStringExtra("noteId"));

                Map<String, Object> note = new HashMap<>();
                note.put("title", noteTitle);
                note.put("content", noteContent);

                docRef.update(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Snackbar.make(view, "Note saved", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                        Intent i = new Intent(view.getContext(), NoteDetails.class);

                        i.putExtra("title", noteTitle);
                        i.putExtra("content", noteContent);
                        i.putExtra("noteId", intent.getStringExtra("noteId"));

                        startActivity(i);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(view, "Something went wrong :(", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    }
                });
            }
        });
        }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == androidx.appcompat.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}