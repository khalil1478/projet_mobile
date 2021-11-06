package com.example.mobile_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile_project.database.MyDataBase;

public class MainActivity extends AppCompatActivity {
    TextView textview_register,input_email,input_password;
    TextView textview_forgot_password;
    MyDataBase db;
    Button btn_login;
    CheckBox show_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//Les inputs
        textview_register = findViewById(R.id.text_register);
        textview_forgot_password = findViewById(R.id.forgot_password);
        input_email = findViewById(R.id.input_email);
        input_password = findViewById(R.id.input_password);
        btn_login = findViewById(R.id.btn_login);
        show_password = findViewById(R.id.show_password);

//déclaration db
        db = MyDataBase.getAppDatabase(this);


        textview_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,register.class);
                startActivity(intent);


            }
        });

        textview_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Retrouver_compte.class);
                startActivity(intent);
            }
        });

        //show password
        show_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    input_password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                else
                {
                    input_password.setInputType(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        //Tester les champs

        btn_login.setOnClickListener(view ->  {
             Log.d("email",input_email.getText().toString());
            Log.d("password",input_password.getText().toString());


            if(input_email.getText().toString().isEmpty() ||  input_password.getText().toString().isEmpty()  )
                alert("les champs obligatoires");
            else if(db.userDao().find_User(input_password.getText().toString(),input_email.getText().toString()) == null){
                alert("user n'existe pas");
            }
            else{
                alert("user existe");
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