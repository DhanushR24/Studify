package com.example.studify_madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThreeUnitActivity extends AppCompatActivity {

    TextView subjectName,unit_number;
    Intent intent2,intent3;
    Button u1,u2,u3;
    int viewId;
    String unitname="",subname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_three_unit);

        subjectName=(TextView) findViewById(R.id.subjectName);
        unit_number = findViewById(R.id.unitNumberText2);
        intent2=getIntent();
        subname=intent2.getStringExtra("SubjectName");
        subjectName.setText(subname);

        unit_number.setText("This is a 3 unit course");

        u1=(Button) findViewById(R.id.unit1);
        u2=(Button) findViewById(R.id.unit2);
        u3=(Button) findViewById(R.id.unit3);



        registerForContextMenu(u1);
        registerForContextMenu(u2);
        registerForContextMenu(u3);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, @NonNull View v, ContextMenu.ContextMenuInfo menuInfo) {
        viewId=v.getId();
        MenuInflater menuInflater=getMenuInflater();
        switch (viewId)
        {
            case R.id.unit1: unitname="unit1";
                menuInflater.inflate(R.menu.menu,menu);
                break;
            case R.id.unit2: unitname="unit2";
                menuInflater.inflate(R.menu.menu,menu);
                break;
            case R.id.unit3: unitname="unit3";
                menuInflater.inflate(R.menu.menu,menu);
                break;

        }

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        intent3=new Intent(getApplicationContext(),WebPageActivity.class);
        if(item.getItemId()==R.id.theory)
        {
            intent3.putExtra("subName",subname);
            intent3.putExtra("unitName",unitname);
            intent3.putExtra("type","Theory");
            startActivity(intent3);
        }
        else if(item.getItemId()==R.id.videos)
        {
            intent3.putExtra("subName",subname);
            intent3.putExtra("unitName",unitname);
            intent3.putExtra("type","Video");
            startActivity(intent3);
        }
        return super.onContextItemSelected(item);
    }
}