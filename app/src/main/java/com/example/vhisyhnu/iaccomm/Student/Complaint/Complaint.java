package com.example.vhisyhnu.iaccomm.Student.Complaint;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vhisyhnu.iaccomm.LoginActivity;
import com.example.vhisyhnu.iaccomm.R;
import com.example.vhisyhnu.iaccomm.Student.Announcement.Announcements;
import com.example.vhisyhnu.iaccomm.Student.Dashboard;
import com.example.vhisyhnu.iaccomm.Student.IP.InternetAutoSolution;
import com.example.vhisyhnu.iaccomm.Student.ResetPassword;
import com.example.vhisyhnu.iaccomm.Student.StudentDashboard;
import com.google.firebase.auth.FirebaseAuth;

public class Complaint extends AppCompatActivity {


    Dialog epicDialog;
    TextView titleTv, messagetv;
    ImageView closePopupInternet;
    Button butOther, butInternet, btnAgree, btnDisagree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        butOther = (Button) findViewById(R.id.butOther);
        butInternet = (Button) findViewById(R.id.butInternet);
        epicDialog = new Dialog(this);



        butInternet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ShowInternetPopup();
            }
        });

        butOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toy = new Intent(Complaint.this, StudentComplaint.class);

                startActivity(toy);

            }
        });

    }


    public void ShowInternetPopup(){
        epicDialog.setContentView(R.layout.epic_popup_internet);
        closePopupInternet =(ImageView) epicDialog.findViewById(R.id.closePopupInternet);
        titleTv= (TextView) epicDialog.findViewById(R.id.titleTv);
        messagetv = (TextView) epicDialog.findViewById(R.id.messageTv);
        btnAgree = (Button) epicDialog.findViewById(R.id.agree2);
        btnDisagree = (Button) epicDialog.findViewById(R.id.disagree2);

        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toy = new Intent(Complaint.this, InternetAutoSolution.class);

                startActivity(toy);

            }
        });

        btnDisagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toy = new Intent(Complaint.this,StudentComplaint.class);

                startActivity(toy);

            }
        });

        closePopupInternet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                epicDialog.dismiss();
            }
        });

        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();



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
                startActivity(new Intent(Complaint.this, StudentDashboard.class));
                finish();
                break;
            case R.id.nav_db:
                startActivity(new Intent(Complaint.this, Dashboard.class));
                finish();
                break;
            case R.id.nav_announcements:
                startActivity(new Intent(Complaint.this, Announcements.class));
                finish();
                break;
            case R.id.nav_change:
                startActivity(new Intent(Complaint.this, ResetPassword.class));
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
