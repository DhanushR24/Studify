package com.example.studify_madproject.model;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studify_madproject.NoteDetails;
import com.example.studify_madproject.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<String> titles;
    List<String> content;

    public Adapter(List<String> titles, List<String> content) {
        this.titles = titles;
        this.content = content;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_view_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.noteTitle.setText(titles.get(position));
        holder.noteContent.setText(content.get(position));

        int code = getRandomColor();
        holder.cardView.setCardBackgroundColor(holder.view.getResources().getColor(code, null));

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), NoteDetails.class);

                intent.putExtra("title", titles.get(position));
                intent.putExtra("content", content.get(position));
                view.getContext().startActivity(intent);
            }
        });
    }

    private int getRandomColor() {
        List<Integer> colorCode = new ArrayList<>();
        colorCode.add(R.color.light_green);
        colorCode.add(R.color.light_purple);
        colorCode.add(R.color.light_blue);
        colorCode.add(R.color.light_orange);
        colorCode.add(R.color.light_orange);
        colorCode.add(R.color.light_red);
        colorCode.add(R.color.light_yellow);

        Random random = new Random();
        return colorCode.get(random.nextInt(colorCode.size()));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView noteTitle, noteContent;
        CardView cardView;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            noteTitle = itemView.findViewById(R.id.titles);
            noteContent = itemView.findViewById(R.id.content);
            cardView = itemView.findViewById(R.id.noteCard);
            view = itemView;
        }
    }
}
