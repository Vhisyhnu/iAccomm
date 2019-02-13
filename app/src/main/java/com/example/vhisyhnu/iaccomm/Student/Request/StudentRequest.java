package com.example.vhisyhnu.iaccomm.Student.Request;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vhisyhnu.iaccomm.LoginActivity;
import com.example.vhisyhnu.iaccomm.R;
import com.example.vhisyhnu.iaccomm.Student.Announcement.Announcements;
import com.example.vhisyhnu.iaccomm.Student.Complaint.MyComplaint;
import com.example.vhisyhnu.iaccomm.Student.Dashboard;
import com.example.vhisyhnu.iaccomm.Student.ResetPassword;
import com.example.vhisyhnu.iaccomm.Student.StudentDashboard;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StudentRequest extends AppCompatActivity {

    EditText editTextRequest;
    Button buttonAdd;


    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User");

    DatabaseReference databaseRequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_request);

        databaseRequests = FirebaseDatabase.getInstance().getReference("Request");


        editTextRequest = findViewById(R.id.editTextRequest);
        buttonAdd = findViewById(R.id.buttonAddRequest);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRequest();
            }
        });
    }
    private void addRequest() {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Calendar calendar = Calendar.getInstance();

                String currentdate = DateFormat.getDateInstance().format(calendar.getTime());
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                String time = format.format(calendar.getTime());
                String user_name = dataSnapshot.child(firebaseUser.getUid()).child("name").getValue().toString();
                String user_id = dataSnapshot.child(firebaseUser.getUid()).child("studentId").getValue().toString();
                String user_RN=dataSnapshot.child(firebaseUser.getUid()).child("RoomDetails").child("room").getValue().toString();
                String user_PN=dataSnapshot.child(firebaseUser.getUid()).child("RoomDetails").child("spinner").getValue().toString();
                String user_HN=dataSnapshot.child(firebaseUser.getUid()).child("RoomDetails").child("number").getValue().toString();
                String arequest = editTextRequest.getText().toString().trim();

                if (!TextUtils.isEmpty(arequest)) {
                    String id = databaseRequests.push().getKey();
                    Request request = new Request(currentdate, time, arequest, user_name, user_id, user_RN, user_HN, user_PN);

                    databaseRequests.child(id).setValue(request);

                    Toast.makeText(StudentRequest.this, "Request Sent", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(StudentRequest.this, Dashboard.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(StudentRequest.this, "You should enter your request", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drawermenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.nav_home:
                startActivity(new Intent(StudentRequest.this, StudentDashboard.class));
                finish();
                break;
            case R.id.nav_db:
                startActivity(new Intent(StudentRequest.this, Dashboard.class));
                finish();
                break;
            case R.id.nav_announcements:
                startActivity(new Intent(StudentRequest.this, Announcements.class));
                finish();
                break;
            case R.id.nav_change:
                startActivity(new Intent(StudentRequest.this, ResetPassword.class));
                finish();
                break;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent (this, LoginActivity.class));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

}
