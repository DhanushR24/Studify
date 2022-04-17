package com.example.studify_madproject;

import android.content.Context;
import android.util.Patterns;
import android.widget.Toast;

public class Validation {
    Context context;

    Validation(Context context)
    {
        this.context=context;
    }

    boolean checkEm(String email)
    {
        if(email.length()==0)
        {
            Toast.makeText(context, "Please Enter your Email", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            return true;
        }
    }
    boolean checkPass(String pass)
    {
        if(pass.length()==0)
        {
            Toast.makeText(context, "Please Enter your Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(pass.length()<6)
        {
            Toast.makeText(context, "Please Enter a Password Greater Than 6", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            return true;
        }
    }
}
