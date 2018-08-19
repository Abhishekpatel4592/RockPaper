package com.example.abhishekpatel.rockpaper;

import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.hardware.SensorManager;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.seismic.ShakeDetector;

import java.util.Random;


public class CreateGame extends AppCompatActivity implements View.OnClickListener {



    TextView IDGenerate,Choice1;
    FirebaseDatabase database;
    DatabaseReference root;
    Button btnSave;
    EditText edName;
    String name = "";
    //ShakeDetector detector;
    //int num;
    int num = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        IDGenerate = (TextView)findViewById(R.id.txtRandom);

        btnSave = (Button) findViewById(R.id.btnSave);
        edName = (EditText) findViewById(R.id.edt1);

        generatecode();

        btnSave.setOnClickListener(this);

    }



    public String generatecode() {

        Random randnumber = new Random();
        num = 100000 + randnumber.nextInt(100000);

        IDGenerate.setText(String.valueOf(num));
        database = FirebaseDatabase.getInstance();
        root = database.getReference();



        return String.valueOf(num);


    }


    @Override
    public void onClick(View view) {

        name = edName.getText().toString();
        DatabaseReference ref = root.child(String.valueOf(num));
        DatabaseReference nref = ref.push();
        //Log.d("EDNAME:", String.valueOf(edName));
        nref.child("Name").setValue(name);
        nref.child("Choice").setValue("No Choice");




        Intent Main = new Intent(CreateGame.this, ShakeShake.class);
        startActivity(Main);

    }
}
