package com.example.mobile_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobile_project.database.MyDataBase;

public class Reinitialiser_mdp extends AppCompatActivity {
    Button button_back, btn_confirme;
    TextView input_code;
    MyDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reinitialiser_mdp);
// déclarer les input
        button_back = findViewById(R.id.button_back);
        btn_confirme = findViewById(R.id.btn_confirme);
        input_code = findViewById(R.id.input_code);

        //déclaration db
        db = MyDataBase.getAppDatabase(this);

        //récuperer email a partie de activity retrouver_compte
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Reinitialiser_mdp.this, Retrouver_compte.class);
                startActivity(intent);


            }
        });
//vérifier le code
        btn_confirme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (input_code.getText().toString().isEmpty()) {
                    alert("saisir votre code");
                } else if (db.userDao().find_User(input_code.getText().toString(), email) == null) {
                    alert("vérifier votre code ");
                }
                else
                {
                    alert("user est bien modifier");
                }


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
