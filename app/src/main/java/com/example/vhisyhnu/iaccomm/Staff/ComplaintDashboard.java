package com.example.vhisyhnu.iaccomm.Staff;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.vhisyhnu.iaccomm.LoginActivity;
import com.example.vhisyhnu.iaccomm.R;
import com.example.vhisyhnu.iaccomm.Staff.Search.SearchActivity;
import com.example.vhisyhnu.iaccomm.Student.Announcement.Announcements;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ComplaintDashboard extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference reference, reference2,reference3;

    CardView Com, Sol, Pen;

    TextView ComN, SolN, PenN, Search;

    private int countComplain = 0 ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_dashboard);


        Com = findViewById(R.id.first);
        Sol = findViewById(R.id.second);
        Pen = findViewById(R.id.third);


        ComN =  findViewById(R.id.complainN);
        SolN =  findViewById(R.id.solveN);
        PenN =  findViewById(R.id.pendingN);
        Search = findViewById(R.id.search);

        reference = FirebaseDatabase.getInstance().getReference().child("User").child(firebaseUser.getUid()).child("Student Details");
        reference2 = FirebaseDatabase.getInstance().getReference().child("User").child(firebaseUser.getUid()).child("Solved");
        reference3 = FirebaseDatabase.getInstance().getReference().child("User").child(firebaseUser.getUid()).child("In Process");

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ComplaintDashboard.this, SearchActivity.class);
                startActivity(i);
            }
        });

        Com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ComplaintDashboard.this, StaffComplaint.class);
                startActivity(i);
            }
        });

        Pen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ComplaintDashboard.this, StaffInProcess.class);
                startActivity(i);
            }
        });

        Sol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ComplaintDashboard.this, StaffSolved.class);
                startActivity(i);
            }
        });



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists())
                {
                    countComplain = (int) dataSnapshot.getChildrenCount();
                    ComN.setText(Integer.toString(countComplain));
                }
                else
                {
                    ComN.setText(" O ");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists())
                {
                    countComplain = (int) dataSnapshot.getChildrenCount();
                    SolN.setText(Integer.toString(countComplain));
                }
                else
                {
                    SolN.setText(" O ");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists())
                {
                    countComplain = (int) dataSnapshot.getChildrenCount();
                    PenN.setText(Integer.toString(countComplain));
                }
                else
                {
                    PenN.setText(" O ");
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
        inflater.inflate(R.menu.staffmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.st_db:
                startActivity(new Intent(ComplaintDashboard.this, StaffDashboard.class));
                finish();
                break;
            case R.id.st_announ:
                startActivity(new Intent(ComplaintDashboard.this, StaffAnnouncement.class));
                finish();
                break;
            case R.id.st_logout:
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
