package com.example.mobile_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile_project.database.MyDataBase;
import com.example.mobile_project.entities.Event;
import com.example.mobile_project.entities.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class add_event extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {


    Button btn_add_event ,button2;
    EditText input_nbr_places,input_tarif,input_name,input_place;
    EditText input_date;
    MyDataBase db;
    Button btn_addevent2;
    Button btn_Acc;
    Button btn_profile;
    Button btn_login;
    String type_select;

    int year,month,day;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);


        //déclaration db
        db = MyDataBase.getAppDatabase(this);


        // déclarer les input
        btn_add_event = findViewById(R.id.btn_add_event);
        input_date = findViewById(R.id.input_date);
        input_nbr_places = findViewById(R.id.input_nbr_places);
        input_tarif = findViewById(R.id.input_tarif);
        input_name = findViewById(R.id.input_name);
        input_place = findViewById(R.id.input_place);
       // button2 = findViewById(R.id.button2);
        Spinner spinner = (Spinner) findViewById(R.id.input_type_event);
        btn_addevent2 = findViewById(R.id.buttonaddevent2);
        btn_Acc = findViewById(R.id.buttonacc2);
        btn_profile = findViewById(R.id.buttonprofil2);
        btn_login = findViewById(R.id.buttonlogout2);


        //test de type de event payant gratuit
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(add_event.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);


// déclaration  pour date
        final Calendar calender = Calendar.getInstance();
        input_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = calender.get(Calendar.YEAR);
                month = calender.get(Calendar.MONTH);
                day = calender.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(add_event.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        input_date.setText(SimpleDateFormat.getDateInstance().format(calender.getTime()));

                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        });


        btn_add_event.setOnClickListener(view -> {

            if (input_name.getText().toString().isEmpty() || input_place.getText().toString().isEmpty() || input_tarif.getText().toString().isEmpty() || input_nbr_places.getText().toString().isEmpty() || input_date.getText().toString().isEmpty())
                alert("les champs obligatoires");

            else {
                Event event = new Event(input_name.getText().toString(),input_place.getText().toString(),input_tarif.getText().toString(),input_nbr_places.getText().toString(),input_date.getText().toString(),spinner.getSelectedItem().toString(),"0"/*input_date.getText().toString()//*,type_select ,"0"*/);
                db.EventDao().addEvent(event);
                Toast.makeText(add_event.this, "add event", Toast.LENGTH_LONG).show();
                Log.d("my event", event.toString());
            }


        });
   btn_Acc.setOnClickListener(view ->  {
        //  Log.d("email",input_email.getText().toString());
        //  Log.d("password",input_password.getText().toString());

           Intent intent = new Intent(add_event.this,home.class);
            startActivity(intent);
        });
        btn_addevent2.setOnClickListener(view ->  {
            //  Log.d("email",input_email.getText().toString());
            //  Log.d("password",input_password.getText().toString());

            Intent intent = new Intent(add_event.this,add_event.class);
            startActivity(intent);
        });
        // redirect to comment page
       /* button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(add_event.this,Commentaires.class);
                startActivity(intent);
            }
        });*/

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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        type_select =adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),type_select,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}