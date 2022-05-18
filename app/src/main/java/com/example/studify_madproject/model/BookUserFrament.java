package com.example.studify_madproject.model;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studify_madproject.Adapter.AdapterPdfUser;
import com.example.studify_madproject.R;
import com.example.studify_madproject.databinding.FragmentBookUserFramentBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookUserFrament#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookUserFrament extends Fragment {

    private String categoryId;
    private String category;
    private String vid;

    private ArrayList<ModelPdf> pdfArrayList;
    private AdapterPdfUser adapterPdfUser;

    private FragmentBookUserFramentBinding binding;
   private static final String TAG="BOOKS_USER_TAG";


    public BookUserFrament() {
        // Required empty public constructor
    }

    public static BookUserFrament newInstance(String categoryId, String category,String vid) {
        BookUserFrament fragment = new BookUserFrament();
        Bundle args = new Bundle();
        args.putString("categoryId",categoryId);
        args.putString("category",category);
        args.putString("vid",vid);


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryId = getArguments().getString("categoryId");
            category = getArguments().getString("category");
            vid = getArguments().getString("vid");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentBookUserFramentBinding.inflate(LayoutInflater.from(getContext()),container,false);
        Log.d(TAG,"onCreateView: Category"+category);
        if(category.equals("All")){
            loadAllBooks();
        }
        else if(category.equals("Most Viewed"))
        {
            loadMostViewed("viewsCount");
        }
        else if(category.equals("Most Downloads"))
        {
            loadMostDownloads("downloadsCount");
        }
        else
        {
            loadCategorizedBook();
        }
        binding.searchEt.addTextChangesListener(new TextWatcher(){

        });
        //here return is not proper return binding.getRoot();
        return inflater.inflate(R.layout.fragment_book_user_frament, container, false);
    }

    private void loadCategorizedBook() {
        pdfArrayList=new ArrayList<>();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Books");
        ref.orderByChild("categoryId").equalTo(categoryId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        pdfArrayList.clear();
                        for(DataSnapshot ds: snapshot.getChildren()){
                            ModelPdf model=ds.getValue(ModelPdf.class);
                            pdfArrayList.add(model);
                        }
                        adapterPdfUser=new AdapterPdfUser(getContext(),pdfArrayList);

                        binding.booksRv.setAdapter(adapterPdfUser);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void loadMostDownloads() {
    }

    private void loadMostViewed(String orderBy) {
        pdfArrayList=new ArrayList<>();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Books");
        ref.orderByChild(orderBy).limitToLast(10)
        .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pdfArrayList.clear();
                for(DataSnapshot ds: snapshot.getChildren()){
                    ModelPdf model=ds.getValue(ModelPdf.class);
                    pdfArrayList.add(model);
                }
                adapterPdfUser=new AdapterPdfUser(getContext(),pdfArrayList);

                binding.booksRv.setAdapter(adapterPdfUser);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadAllBooks() {
        pdfArrayList=new ArrayList<>();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Books");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pdfArrayList.clear();
                for(DataSnapshot ds: snapshot.getChildren()){
                    ModelPdf model=ds.getValue(ModelPdf.class);
                    pdfArrayList.add(model);
                }
                adapterPdfUser=new AdapterPdfUser(getContext(),pdfArrayList);

                binding.booksRv.setAdapter(adapterPdfUser);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        
    }
}