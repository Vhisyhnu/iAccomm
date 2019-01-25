package com.example.vhisyhnu.iaccomm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentRequest extends AppCompatActivity {

    EditText editTextNum, editTextRoom, editTextRequest;
    Button buttonAdd;
    Spinner spinnerHostels;

    DatabaseReference databaseRequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_request);

        databaseRequests = FirebaseDatabase.getInstance().getReference("Request");

        editTextNum = (EditText) findViewById(R.id.editTextNum);
        spinnerHostels = (Spinner) findViewById(R.id.spinnerHostels);
        editTextRoom = (EditText) findViewById(R.id.editTextRoom);
        editTextRequest = (EditText) findViewById(R.id.editTextRequest);
        buttonAdd = (Button) findViewById(R.id.buttonAddRequest);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRequest();
            }
        });
    }
    private void addRequest() {
        String num = editTextNum.getText().toString().trim();
        String hostel = spinnerHostels.getSelectedItem().toString();
        String room = editTextRoom.getText().toString().trim();
        String arequest = editTextRequest.getText().toString().trim();


        if (!TextUtils.isEmpty(num)) {
            String id = databaseRequests.push().getKey();
            Request request = new Request(id, num, hostel, room, arequest);

            databaseRequests.child(id).setValue(request);

            Toast.makeText(this, "Request Sent", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Dashboard.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "You should enter your Student Id", Toast.LENGTH_LONG).show();
        }

    }
}
