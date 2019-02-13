package com.example.vhisyhnu.iaccomm.Student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.vhisyhnu.iaccomm.AboutActvity;
import com.example.vhisyhnu.iaccomm.Chat.MainChatActivity;
import com.example.vhisyhnu.iaccomm.Student.Announcement.Announcements;
import com.example.vhisyhnu.iaccomm.LoginActivity;
import com.example.vhisyhnu.iaccomm.R;
import com.example.vhisyhnu.iaccomm.Student.Complaint.Complaint;
import com.example.vhisyhnu.iaccomm.Student.Complaint.MyComplaint;
import com.example.vhisyhnu.iaccomm.Student.Notify.StudentNotify;
import com.example.vhisyhnu.iaccomm.Student.Request.StudentRequest;
import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    private CardView  complaintCard, requestCard, notifyCard, chatCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        complaintCard =(CardView) findViewById(R.id.complaint_card);
        requestCard =(CardView) findViewById(R.id.request_card);
        notifyCard = (CardView) findViewById(R.id.notify_card);
        chatCard = (CardView) findViewById(R.id.chat_card);

        complaintCard.setOnClickListener(this);
        requestCard.setOnClickListener(this);
        notifyCard.setOnClickListener(this);
        chatCard.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.complaint_card : i = new Intent(this, Complaint.class);startActivity(i); break;
            case R.id.request_card : i = new Intent(this, StudentRequest.class);startActivity(i); break;
            case R.id.notify_card : i = new Intent(this, StudentNotify.class);startActivity(i); break;
            case R.id.chat_card : i = new Intent(this, MainChatActivity.class);startActivity(i); break;
            default:break;
        }

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
                startActivity(new Intent(Dashboard.this, StudentDashboard.class));
                finish();
                break;
            case R.id.nav_db:
                startActivity(new Intent(Dashboard.this, Dashboard.class));
                finish();
                break;
            case R.id.nav_announcements:
                startActivity(new Intent(Dashboard.this, Announcements.class));
                finish();
                break;
            case R.id.nav_change:
                startActivity(new Intent(Dashboard.this, ResetPassword.class));
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
