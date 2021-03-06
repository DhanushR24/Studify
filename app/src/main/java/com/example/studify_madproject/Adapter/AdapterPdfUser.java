package com.example.studify_madproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studify_madproject.MyApplication;
import com.example.studify_madproject.PdfDetailActivity;
import com.example.studify_madproject.databinding.ViewPdfBinding;
import com.example.studify_madproject.filters.FilterPdfUser;
import com.example.studify_madproject.model.ModelPdf;
import com.github.barteksc.pdfviewer.PDFView;

import java.util.ArrayList;

public class AdapterPdfUser extends RecyclerView.Adapter<AdapterPdfUser.HolderPdfUser> implements Filterable {
    private Context context;
    public ArrayList<ModelPdf> pdfArrayList,filterList;
    private ViewPdfBinding binding;
    private FilterPdfUser filter;

    private static final String TAG="ADAPTER_PDF_USER_TAG";

    public AdapterPdfUser(Context context, ArrayList<ModelPdf> pdfArrayList) {
        this.context = context;
        this.pdfArrayList = pdfArrayList;
        this.filterList=pdfArrayList;
    }

    @NonNull
    @Override
    public HolderPdfUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding=ViewPdfBinding.inflate(LayoutInflater.from(context),parent,false);

        return new HolderPdfUser(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull HolderPdfUser holder, int position) {
        ModelPdf model=pdfArrayList.get(position);
        String bookId= model.getId();
        String title=model.getTitle();
        String description=model.getDescription();
        String pdfUrl=model.getUrl();
        String categoryid=model.getCategoryid();
        long timestamp=model.getTimestamp();
        String date= MyApplication.formatTimesStamp(timestamp);


        holder.titleTv.setText(title);
        holder.descriptionTv.setText(description);
        holder.dateTv.setText(date);

        MyApplication.loadPdfFromUrl(""+pdfUrl,""+title,holder.pdfView,holder.progressBar);
        MyApplication.loadCategory(""+categoryid,holder.categoryTv);
        MyApplication.loadPdfSize(""+pdfUrl,""+title,holder.sizeTv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, PdfDetailActivity.class);
                intent.putExtra("bookId",bookId);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return pdfArrayList.size();
    }

    @Override
    public Filter getFilter() {
        if(filter == null)
        {
            filter=new FilterPdfUser(filterList,this);
        }
        return filter;
    }

    class HolderPdfUser extends RecyclerView.ViewHolder{

        TextView titleTv,descriptionTv,categoryTv,sizeTv,dateTv;
        PDFView pdfView;
        ProgressBar progressBar;
        public HolderPdfUser(@NonNull View itemView)
        {
            super(itemView);
            titleTv=binding.titleTv;
            descriptionTv= binding.descriptionTv;
            categoryTv= binding.categoryTv;
            sizeTv= binding.sizeTv;
            dateTv= binding.dateTv;
            pdfView=binding.pdfView;
            progressBar=binding.progressBar;
        }
    }
}
