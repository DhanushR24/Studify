package com.example.studify_madproject.model;

import androidx.annotation.NonNull;

import com.google.firebase.database.Exclude;

public class TaskId {
    //Exclude => This task id won't be affected from any data change
    @Exclude
    public String TaskId;

    public <T extends  TaskId> T withId(@NonNull final String id){
        this.TaskId=id;
        return (T) this;
    }

}
