package com.example.vhisyhnu.iaccomm.Student;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vhisyhnu.iaccomm.AboutActvity;
import com.example.vhisyhnu.iaccomm.Student.Announcement.Announcements;
import com.example.vhisyhnu.iaccomm.LoginActivity;
import com.example.vhisyhnu.iaccomm.R;
import com.example.vhisyhnu.iaccomm.Student.Complaint.MyComplaint;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentDashboard extends AppCompatActivity{

    private TextView uEmail, uName, uId, mNum;
    private LinearLayout mComplaint;
    private DrawerLayout dl;
    private ActionBarDrawerToggle toggle;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    final FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference reference1, reference = FirebaseDatabase.getInstance().getReference().child("User");

    private int countComplaint = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        uEmail = (TextView)findViewById(R.id.UserEmail);
        uId = (TextView) findViewById(R.id.UserId);
        uName = (TextView)findViewById(R.id.UserName);
        mNum = (TextView) findViewById(R.id.my_num);
        mComplaint = (LinearLayout) findViewById(R.id.my_complaint);

        dl = (DrawerLayout) findViewById(R.id.dl);
        toggle = new ActionBarDrawerToggle(this, dl, R.string.open, R.string.close);
        dl.addDrawerListener(toggle);
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nv);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(nvDrawer);

        reference1 = FirebaseDatabase.getInstance().getReference().child("User").child(firebaseUser.getUid()).child("My Complaint");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String user_email = dataSnapshot.child(firebaseUser.getUid()).child("email").getValue().toString();
                String user_id = dataSnapshot.child(firebaseUser.getUid()).child("studentId").getValue().toString();
                String user_name = dataSnapshot.child(firebaseUser.getUid()).child("name").getValue().toString();
                uEmail.setText(user_email);
                uId.setText(user_id);
                uName.setText(user_name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(StudentDashboard.this,databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }
        });

        mComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StudentDashboard.this, MyComplaint.class);
                startActivity(i);
            }
        });

        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists())
                {
                    countComplaint = (int) dataSnapshot.getChildrenCount();
                    mNum.setText(Integer.toString(countComplaint));
                }
                else
                {
                    mNum.setText(" O ");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void selectItemDrawer(MenuItem menuItem) {
        int id= menuItem.getItemId();
        switch (id) {
            case R.id.nav_home:
                startActivity(new Intent(StudentDashboard.this, StudentDashboard.class));
                finish();
                break;
            case R.id.nav_db:
                startActivity(new Intent(StudentDashboard.this, Dashboard.class));
                finish();
                break;
            case R.id.nav_announcements:
                startActivity(new Intent(StudentDashboard.this, Announcements.class));
                finish();
                break;
            case R.id.nav_change:
                startActivity(new Intent(StudentDashboard.this, ResetPassword.class));
                finish();
                break;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent (this, LoginActivity.class));
                break;
        }

    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                selectItemDrawer(item);
                return true;
            }
        });
    }


    }

