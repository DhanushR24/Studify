package com.example.studify_madproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studify_madproject.MainActivity;
import com.example.studify_madproject.R;
import com.example.studify_madproject.model.ToDoModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHoder> {

    public List<ToDoModel> toDoList;
    private FragmentActivity activity;
    private FirebaseFirestore firestore;
    public ToDoAdapter(FragmentActivity mainActivity, List<ToDoModel> toDoList){
        this.toDoList=toDoList;
        activity=mainActivity;
    }

    @NonNull
    @Override
    public MyViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(activity).inflate(R.layout.single_task,parent,false);
        firestore=FirebaseFirestore.getInstance();
        return new MyViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoder holder, int position) {
        ToDoModel toDoModel=toDoList.get(position);
        holder.checkBox.setText(toDoModel.getTask());
        holder.dueDate.setText("Due On " +toDoModel.getDue());

        holder.checkBox.setChecked(toBoolean(toDoModel.getStatus()));
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    firestore.collection("task").document(toDoModel.TaskId).update("status",1);
                }
                else{
                    firestore.collection("task").document(toDoModel.TaskId).update("status",0);
                }
            }
        });
    }

    private boolean toBoolean(int status){
        //return true if status is 1
        return status!=0;
    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }

    public class MyViewHoder extends RecyclerView.ViewHolder{
        TextView dueDate;
        CheckBox  checkBox;

        public MyViewHoder(@NonNull View itemView) {
            super(itemView);

            dueDate=itemView.findViewById(R.id.due_date);
            checkBox=itemView.findViewById(R.id.checkbox1);
        }
    }


}
