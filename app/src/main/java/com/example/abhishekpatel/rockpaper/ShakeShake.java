package com.example.abhishekpatel.rockpaper;

import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.seismic.ShakeDetector;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ShakeShake extends AppCompatActivity implements ShakeDetector.Listener {


    ShakeDetector detector;
    TextView Choice;
    FirebaseDatabase database;
    DatabaseReference root;
    SharedPreferences pref;

    String[] RPS = {"Rock","Paper","Sicssor"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake_shake);

        pref = getSharedPreferences("MyPref",0);

        Choice = (TextView)findViewById(R.id.txtChange);


            SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
            detector = new ShakeDetector(this);
            detector.start(manager);

    }

    @Override
    public void hearShake() {
        Log.d("SHAKE","Phone is Shaken");
        Toast.makeText(this,"Phone is shaking",Toast.LENGTH_SHORT).show();

        int rnd = new Random().nextInt(RPS.length);
        String value = RPS[rnd];

        Choice.setText(String.valueOf(value));

//        database = FirebaseDatabase.getInstance();
//        root = database.getReference();

        Log.d("Choice................................",value);

        //SharedPreferences.Editor editor = pref.edit();

        String id = pref.getString("id",null);

        Log.d("Id......................",id);

        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child(id).push();
        Map<String, Object> updates = new HashMap<String,Object>();
        updates.put("Choice", value);


       ref.updateChildren(updates);





        detector.stop();
    }
}
