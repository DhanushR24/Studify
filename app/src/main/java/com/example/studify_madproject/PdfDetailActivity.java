package com.example.studify_madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.studify_madproject.databinding.ActivityPdfDetailBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PdfDetailActivity extends AppCompatActivity {
    private ActivityPdfDetailBinding binding;

    String bookid,bookTitle,bookUrl;
    private static final String TAG_DOWNLOAD="DOWNLOAD_TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPdfDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent=getIntent();
        bookid=intent.getStringExtra("bookId");
        loadBookDetails();
        MyApplication.incrementBookViewCount(bookid);
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    private void loadBookDetails(){
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Books");
        ref.child(bookid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String title=""+snapshot.child("title").getValue();
                        String description=""+snapshot.child("description").getValue();
                        String category=""+snapshot.child("categoryId").getValue();
                        String viewsCount=""+snapshot.child("viewsCount").getValue();
                        String downloadsCount=""+snapshot.child("downloadsCount").getValue();
                        String url=""+snapshot.child("url").getValue();
                        String timestamp=""+snapshot.child("timestamp").getValue();

                        String date=MyApplication.formatTimesStamp(Long.parseLong(timestamp));
                        MyApplication.loadCategory(
                            ""+category,
                        binding.categoryTv
                            );
                        MyApplication.loadPdfFromUrl(""+url,""+title, binding.pdfView, binding.progressBar);
                        MyApplication.loadPdfSize(""+url,""+title, binding.sizeTv);

                        binding.titleTv.setText(title);
                        binding.descriptionTv.setText(description);
                        binding.viewsTv.setText(viewsCount.replace("null","N/A"));
                        binding.downloadsTv.setText(downloadsCount.replace("null","N/A"));
                        binding.dateTv.setText(date);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}