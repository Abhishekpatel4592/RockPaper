package com.example.abhishekpatel.rockpaper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.btnCreate);
        btn2 = (Button)findViewById(R.id.btnJoin);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        if(view == btn1) {
            Intent in = new Intent(this, CreateGame.class);
            startActivity(in);
        }
        else if (view == btn2){
            Intent in = new Intent(this, JoinGame.class);
            startActivity(in);
        }

    }




    
}
