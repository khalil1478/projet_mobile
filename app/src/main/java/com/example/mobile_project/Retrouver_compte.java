package com.example.mobile_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile_project.database.MyDataBase;
import com.example.mobile_project.entities.User;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Retrouver_compte extends AppCompatActivity {
    Button button_back,btn_chercher;
    TextView input_email;
    MyDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrouver_compte);

        //déclarer les inputs
        input_email = findViewById(R.id.input_email);
        button_back = findViewById(R.id.button_back);
        btn_chercher = findViewById(R.id.btn_chercher);

        //declaration bd
        db = MyDataBase.getAppDatabase(this);


        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Retrouver_compte.this,MainActivity.class);
                startActivity(intent);


            }
        });

        //vérifier email existe ou pas

        btn_chercher.setOnClickListener(view -> {
             if(db.userDao().existe_email(input_email.getText().toString()) ==null)
            {
                alert("email n'existe pas");
            }
             else
             {
                /* Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                 intent.setType("text/plain");
                 intent.putExtra(Intent.EXTRA_SUBJECT, "Subject of email");
                 intent.putExtra(Intent.EXTRA_TEXT, "Body of email");
                 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                 startActivity(intent);*/


                 final String username = "socotutest@gmail.com";
                 final String password = "gzetkybbzarhzocz";
                  String nouveau_password = "123456789";
                 Properties properties  = new Properties();
                 properties.put("mail.smtp.auth","true");
                 properties.put("mail.smtp.starttls.enable","true");
                 properties.put("mail.smtp.host","smtp.gmail.com");
                 properties.put("mail.smtp.port","587");
                 Session session =Session.getInstance(properties,
                         new javax.mail.Authenticator() {
                             protected PasswordAuthentication getPasswordAuthentication() {
                                 return new PasswordAuthentication(username, password);
                             }
                         });

                 try {
                     Message msg = new MimeMessage( session );

                     msg.setFrom(new InternetAddress(username));
                     msg.setRecipients( javax.mail.Message.RecipientType.TO, InternetAddress.parse(input_email.getText().toString()) );
                     msg.setSubject("Your password" );
                     msg.setText( "your password"+nouveau_password);
                     Transport.send(msg);

                 }catch (MessagingException e)
                 {
                     throw new RuntimeException(e);
                 }
                 db.userDao().update_password("123456789",input_email.getText().toString());
                 Toast.makeText(Retrouver_compte.this,"check your email ",Toast.LENGTH_LONG).show();
                 User u = db.userDao().existe_email(input_email.getText().toString());
                 Log.d("user est modifier",u.toString());
                 Intent intent = new Intent(Retrouver_compte.this,Reinitialiser_mdp.class);
                 intent.putExtra("email",input_email.getText().toString());

                 startActivity(intent);
             }
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);








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