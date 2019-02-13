package com.example.vhisyhnu.iaccomm.Student;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vhisyhnu.iaccomm.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    public EditText Room, Number;
    public Button detailS;
    private TextView Names;
    Spinner mySpinner;
    private TextView ID;
    String UID;
    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        UID = firebaseUser.getUid();

        Room =(EditText) findViewById(R.id.RoomNumber);
        Number =(EditText) findViewById(R.id.PhoneNumber);
        mySpinner = (Spinner) findViewById(R.id.HostelName);
        detailS=(Button) findViewById(R.id.Register);

        detailS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDetails();
                sucessful();
            }
        });

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.hostel));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

    }

    protected void onStart(){
        super.onStart();
        if(firebaseAuth.getCurrentUser() != null);
    }

    private void addDetails(){

        String room = Room.getText().toString();
        String number = Number.getText().toString();
        String service = mySpinner.getSelectedItem().toString();

        RoomDetails roomDetails = new RoomDetails(room,number,service);

        reference.child(firebaseUser.getUid()).child("RoomDetails").setValue(roomDetails);
    }

    private void sucessful(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String roomdetails = dataSnapshot.getValue().toString();
                if(roomdetails.contains("RoomDetails"))
                {
                    Toast.makeText(RegisterActivity.this, "Register Sucessful", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "Register Unsucessful", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
