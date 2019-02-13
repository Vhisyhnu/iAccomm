package com.example.vhisyhnu.iaccomm.Staff;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.vhisyhnu.iaccomm.Chat.MainChatActivity;
import com.example.vhisyhnu.iaccomm.LoginActivity;
import com.example.vhisyhnu.iaccomm.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StaffDashboard extends AppCompatActivity implements View.OnClickListener  {

    private TextView NotiN, ReqN;

    private int countNotify = 0 ;
    private int countRequest = 0 ;

    private CardView announcementCard, complaintCard, requestCard, notifyCard;
    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference reference, reference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_dashboard);

        announcementCard =  findViewById(R.id.announcement_card);
        complaintCard = findViewById(R.id.scomplaint_card);
        requestCard = findViewById(R.id.srequest_card);
        notifyCard =  findViewById(R.id.snotification_card);
        NotiN =  findViewById(R.id.notifyN);
        ReqN =  findViewById(R.id.requestN);

        announcementCard.setOnClickListener(this);
        complaintCard.setOnClickListener(this);
        requestCard.setOnClickListener(this);
        notifyCard.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        reference1=FirebaseDatabase.getInstance().getReference().child("User").child(firebaseUser.getUid()).child("Request");

        reference = FirebaseDatabase.getInstance().getReference().child("Notification");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists())
                {
                    countNotify = (int) dataSnapshot.getChildrenCount();
                    NotiN.setText(Integer.toString(countNotify));
                }
                else
                {
                    NotiN.setText(" O ");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists())
                {
                    countRequest = (int) dataSnapshot.getChildrenCount();
                    ReqN.setText(Integer.toString(countRequest));
                }
                else
                {
                    ReqN.setText(" O ");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.announcement_card : i =new Intent(this, MainChatActivity.class);startActivity(i); break;
            case R.id.scomplaint_card : i = new Intent(this,ComplaintDashboard.class);startActivity(i); break;
            case R.id.srequest_card : i = new Intent(this,StaffRequest.class);startActivity(i); break;
            case R.id.snotification_card : i = new Intent(this,StaffNotify.class);startActivity(i); break;
            default:break;
        }

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
                startActivity(new Intent(StaffDashboard.this, StaffDashboard.class));
                finish();
                break;
            case R.id.st_announ:
                startActivity(new Intent(StaffDashboard.this, StaffAnnouncement.class));
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
