package com.example.vhisyhnu.iaccomm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class StaffDashboard extends AppCompatActivity implements View.OnClickListener  {

    private CardView announcementCard, complaintCard, requestCard, notifyCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_dashboard);

        announcementCard = (CardView) findViewById(R.id.announcement_card);
        complaintCard =(CardView) findViewById(R.id.scomplaint_card);
        requestCard =(CardView) findViewById(R.id.srequest_card);
        notifyCard = (CardView) findViewById(R.id.snotification_card);

        announcementCard.setOnClickListener(this);
        complaintCard.setOnClickListener(this);
        requestCard.setOnClickListener(this);
        notifyCard.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.announcement_card : i =new Intent(this,StaffAnnouncement.class);startActivity(i); break;
            case R.id.scomplaint_card : i = new Intent(this,StaffComplaint.class);startActivity(i); break;
            case R.id.srequest_card : i = new Intent(this,StaffRequest.class);startActivity(i); break;
            case R.id.snotification_card : i = new Intent(this,StaffNotify.class);startActivity(i); break;
            default:break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drawermenu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.nav_db2:
                startActivity(new Intent(StaffDashboard.this, StaffDashboard.class));
                finish();
            case R.id.nav_status2:
                startActivity(new Intent(StaffDashboard.this, Status.class));
                finish();
                break;
            case R.id.nav_settings2:
                startActivity(new Intent(StaffDashboard.this, Settings.class));
                finish();
                break;
            case R.id.logout2:
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
