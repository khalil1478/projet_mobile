package com.example.mobile_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.mobile_project.database.MyDataBase;
import com.example.mobile_project.entities.Event;

import java.util.List;

public class home extends AppCompatActivity {
    //TextView textview_register,input_email,input_password;
   // TextView textview_forgot_password;
    MyDataBase db;
    Button btn_addevent;
    Button btn_Acc;
    Button btn_profile;
    Button btn_login;
    RecyclerView recycle_post;
    PostAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Les inputs
        btn_addevent = findViewById(R.id.buttonaddevent1);
        btn_Acc = findViewById(R.id.buttonaddevent1);
        btn_profile = findViewById(R.id.buttonprofile1);
      //  input_password = findViewById(R.id.input_password);
        btn_login = findViewById(R.id.buttonlogout1);

//déclaration db
        db = MyDataBase.getAppDatabase(this);
        recycle_post = findViewById(R.id.ListEvent);
        List<Event> events = db.EventDao().getEvents();
        adapter = new PostAdapter(events,this);
        recycle_post.setAdapter(adapter);
        recycle_post.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        btn_Acc.setOnClickListener(view ->  {
          //  Log.d("email",input_email.getText().toString());
          //  Log.d("password",input_password.getText().toString());

                Intent intent = new Intent(home.this,home.class);
                startActivity(intent);
        });
        btn_addevent.setOnClickListener(view ->  {
            //  Log.d("email",input_email.getText().toString());
            //  Log.d("password",input_password.getText().toString());

            Intent intent = new Intent(home.this,add_event.class);
            startActivity(intent);
        });
    }
}