package com.example.studify_madproject;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.studify_madproject.databinding.ActivityNoteDetailsBinding;

public class NoteDetails extends AppCompatActivity {
    Toolbar toolbar;
    TextView noteContent;
    FloatingActionButton fab;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent = getIntent();

        noteContent = findViewById(R.id.NoteContent);
        noteContent.setMovementMethod(new ScrollingMovementMethod());

        setTitle(intent.getStringExtra("title"));
        noteContent.setText(intent.getStringExtra("content"));

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NoteDetails.this, EditNote.class);
                i.putExtra("title", intent.getStringExtra("title"));
                i.putExtra("content", intent.getStringExtra("content"));
                i.putExtra("noteId", intent.getStringExtra("noteId"));
                startActivity(i);
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