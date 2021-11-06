package com.example.mobile_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile_project.database.MyDataBase;
import com.example.mobile_project.entities.User;

public class register extends AppCompatActivity {
        TextView text_login,input_username,input_email,input_password,input_password_confirm;
        Button button_back,btn_register;
    MyDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


// les inputs
        text_login = findViewById(R.id.text_login);
        input_username = findViewById(R.id.input_username);
        input_email = findViewById(R.id.input_email);
        input_password = findViewById(R.id.input_password);
        input_password_confirm = findViewById(R.id.input_password_confirm);
        button_back = findViewById(R.id.button_back);
        btn_register = findViewById(R.id.btn_register);

//declaration bd
        db = MyDataBase.getAppDatabase(this);

// redirect to login page
        text_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(register.this,login.class);
                startActivity(intent);
            }
        });

// redirect to login page
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(register.this,login.class);
                startActivity(intent);
            }
        });

        //Tester les champs

        btn_register.setOnClickListener(view ->  {

            if(input_username.getText().toString().isEmpty() ||  input_email.getText().toString().isEmpty() || input_password.getText().toString().isEmpty() || input_password_confirm.getText().toString().isEmpty() )
                    alert("les champs obligatoires");

            else if(db.userDao().existe_email(input_email.getText().toString()) !=null)
            {
                alert("email est déja existe");
            }
            else if(input_password.getText().toString() != input_password_confirm.getText().toString())
            {
                alert("vérifier votre password");
            }
            else
            {
                User user = new User(input_username.getText().toString(),input_email.getText().toString(),input_password.getText().toString());
                db.userDao().addUser(user);
                Toast.makeText(this,"add User",Toast.LENGTH_LONG).show();
                Log.d("my user",user.toString());

            }

        });







    }

    private void alert(String message) {

        AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle("Message").setMessage(message)
        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        })
                .create();
        alertDialog.show();
    }
}