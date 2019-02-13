package com.example.vhisyhnu.iaccomm.Student.Complaint;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vhisyhnu.iaccomm.DateModel;
import com.example.vhisyhnu.iaccomm.LoginActivity;
import com.example.vhisyhnu.iaccomm.R;
import com.example.vhisyhnu.iaccomm.Student.Announcement.Announcements;
import com.example.vhisyhnu.iaccomm.Student.Dashboard;
import com.example.vhisyhnu.iaccomm.Student.Notify.StudentNotify;
import com.example.vhisyhnu.iaccomm.Student.Request.StudentRequest;
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

public class StudentComplaint extends AppCompatActivity {


    private int count = 0;


    EditText desc;
    Spinner complain;
    Button  butAdd;
    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_complaint);

        desc = findViewById(R.id.editTextComplaint);
        butAdd = findViewById(R.id.buttonAddComplaint);
        complain = findViewById(R.id.spinnerComplaints);

        butAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDetails();
                datesave();
                sendData();
            }
        });
    }

    private void addDetails(){


        String Desc = desc.getText().toString();
        String Complain = complain.getSelectedItem().toString();

        Complain studentDetails = new Complain(Complain,Desc);

        reference.child(firebaseUser.getUid()).child("Complain").setValue(studentDetails);
    }


    private void sendData(){

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                while (count < 1) {

                    String user_date = dataSnapshot.child(firebaseUser.getUid()).child("TimeAndDate").child("date").getValue().toString();
                    String user_time = dataSnapshot.child(firebaseUser.getUid()).child("TimeAndDate").child("time").getValue().toString();
                    String user_id = dataSnapshot.child(firebaseUser.getUid()).child("studentId").getValue().toString();
                    String user_name = dataSnapshot.child(firebaseUser.getUid()).child("name").getValue().toString();
                    String user_RN=dataSnapshot.child(firebaseUser.getUid()).child("RoomDetails").child("room").getValue().toString();
                    String user_PN=dataSnapshot.child(firebaseUser.getUid()).child("RoomDetails").child("number").getValue().toString();
                    String user_Month = dataSnapshot.child(firebaseUser.getUid()).child("TimeAndDate").child("month").getValue().toString();
                    String user_Complain=dataSnapshot.child(firebaseUser.getUid()).child("Complain").child("Complain").getValue().toString();
                    String user_Desc=dataSnapshot.child(firebaseUser.getUid()).child("Complain").child("Desc").getValue().toString();
                    String user_HN=dataSnapshot.child(firebaseUser.getUid()).child("RoomDetails").child("spinner").getValue().toString();

                    if (user_HN.equals("Ilmu")) {
                        String uid1 = "pUZNPM3E6URqSOLX4rX4BIWnA5k2";

                        StudentDetails studentDetails = new StudentDetails(user_date, user_time, user_id, user_name,user_RN, user_PN, user_Month,user_Complain, user_Desc);

                        String uploadId2 = reference.push().getKey();
                        reference.child(uid1).child("Student Details").child(uploadId2).setValue(studentDetails);
                        reference.child(firebaseUser.getUid()).child("My Complain").child(uploadId2).setValue(studentDetails);
                        reference.child(uid1).child("All Details").child(uploadId2).setValue(studentDetails);
                        count++;
                        Toast.makeText(StudentComplaint.this, "Complaint Sent!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(StudentComplaint.this, Dashboard.class);
                        startActivity(intent);

                    }else if(user_HN.equals("Cendi"))
                    {
                        String uid1 = "b2kAG7ljs5YKYaZsz9cPjBRJreG2";

                        StudentDetails studentDetails = new StudentDetails(user_date, user_time, user_id, user_name,user_RN, user_PN, user_Month,user_Complain, user_Desc);

                        String uploadId2 = reference.push().getKey();
                        reference.child(uid1).child("Student Details").push().setValue(studentDetails);
                        reference.child(firebaseUser.getUid()).child("My Complaint").child(uploadId2).setValue(studentDetails);
                        reference.child(uid1).child("All Details").child(uploadId2).setValue(studentDetails);
                        count++;
                        Toast.makeText(StudentComplaint.this, "Complaint Sent!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(StudentComplaint.this, Dashboard.class);
                        startActivity(intent);
                    }else if(user_HN.equals("Amanah"))
                    {
                        String uid1 = "2MXn4aUNi4cQSBTX7xAzYnXUWvz1";

                        StudentDetails studentDetails = new StudentDetails(user_date, user_time, user_id, user_name,user_RN, user_PN, user_Month,user_Complain, user_Desc);

                        String uploadId2 = reference.push().getKey();
                        reference.child(uid1).child("Student Details").push().setValue(studentDetails);
                        reference.child(firebaseUser.getUid()).child("My Complaint").child(uploadId2).setValue(studentDetails);
                        reference.child(uid1).child("All Details").child(uploadId2).setValue(studentDetails);
                        count++;
                        Toast.makeText(StudentComplaint.this, "Complaint Sent!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(StudentComplaint.this, Dashboard.class);
                        startActivity(intent);
                    }else
                    {
                        String uid1 = "fdsSmY2hbOZsXe0Ac8Fxrf90hmH2";

                        StudentDetails studentDetails = new StudentDetails(user_date, user_time, user_id, user_name,user_RN, user_PN, user_Month,user_Complain, user_Desc);

                        String uploadId2 = reference.push().getKey();
                        reference.child(uid1).child("Student Details").push().setValue(studentDetails);
                        reference.child(firebaseUser.getUid()).child("My Complaint").child(uploadId2).setValue(studentDetails);
                        reference.child(uid1).child("All Details").child(uploadId2).setValue(studentDetails);
                        count++;
                        Toast.makeText(StudentComplaint.this, "Complaint Sent!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(StudentComplaint.this, Dashboard.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(StudentComplaint.this, "Failed To Sent", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(StudentComplaint.this, Dashboard.class);
                startActivity(intent);
            }
        });

    }
    private void datesave(){

        Calendar calendar = Calendar.getInstance();

        String currentdate = DateFormat.getDateInstance().format(calendar.getTime());
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat monthformat = new SimpleDateFormat("MMMM");
        String month = monthformat.format(calendar.getTime());
        String time = format.format(calendar.getTime());

        DateModel dateModel = new DateModel(currentdate, time, month);

        reference.child(firebaseUser.getUid()).child("TimeAndDate").setValue(dateModel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drawermenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                startActivity(new Intent(StudentComplaint.this, StudentDashboard.class));
                finish();
                break;
            case R.id.nav_db:
                startActivity(new Intent(StudentComplaint.this, Dashboard.class));
                finish();
                break;
            case R.id.nav_announcements:
                startActivity(new Intent(StudentComplaint.this, Announcements.class));
                finish();
                break;
            case R.id.nav_change:
                startActivity(new Intent(StudentComplaint.this, ResetPassword.class));
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
