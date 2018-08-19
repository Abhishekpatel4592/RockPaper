package com.example.abhishekpatel.rockpaper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class JoinGame extends AppCompatActivity {


    Button btnSave;
    EditText edtCode, edtName;
    String ID, Username;
    String name = "";
    FirebaseDatabase database;
    DatabaseReference root;
    SharedPreferences pre;
    ArrayList<String> Userlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_game);

        pre = getApplicationContext().getSharedPreferences("My", 0);

        btnSave = (Button) findViewById(R.id.btn1);
        edtCode = (EditText) findViewById(R.id.edt1);
        edtName = (EditText) findViewById(R.id.edt2);

        database = FirebaseDatabase.getInstance();
        root = database.getReference();


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ID = edtCode.getText().toString();
                Username = edtName.getText().toString();
                SharedPreferences.Editor editor = pre.edit();
                editor.putString("Name", Username);
                editor.putString("id", ID);
                editor.commit();

                name = edtName.getText().toString();
                DatabaseReference ref = root.child(String.valueOf(ID));
                DatabaseReference nref = ref.push();
                //DatabaseReference nref = ref.child("Player2");
                //Log.d("EDNAME:", String.valueOf(edName));

                nref.child("Name").setValue(name);
                nref.child("Choice").setValue("No Choice");
                matchGameid();



                Toast.makeText(JoinGame.this, ID, Toast.LENGTH_LONG).show();
                System.out.print(Username);
                //Toast.makeText(joiningGame.this, name, Toast.LENGTH_SHORT).show();



            }
        });

        //Getting the Key(GameID) From the firebase
        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                // Result will be holded Here

                for (DataSnapshot dsp : dataSnapshot.getChildren()) {

                    //adding the keys in the arraylist
                    Userlist.add(dsp.getKey().toString());

                    Log.d("Array", Userlist.toString());


                    //   Toast.makeText(joiningGame.this, dsp.getKey().toString(), Toast.LENGTH_LONG).show();

                }


                //Toast.makeText(joiningGame.this, dataSnapshot.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void matchGameid() {
        for (int i = 0; i < Userlist.size(); i++) {
            if (Userlist.get(i).contains(ID.trim())) {

                Intent Main = new Intent(JoinGame.this, ShakeShake.class);
                startActivity(Main);
                //  Toast.makeText(joiningGame.this, "DONE", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(JoinGame.this, "Enter Correct GameID", Toast.LENGTH_SHORT).show();
            }


        }


    }

}
