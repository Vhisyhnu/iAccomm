package com.example.vhisyhnu.iaccomm.Student.IP;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.vhisyhnu.iaccomm.LoginActivity;
import com.example.vhisyhnu.iaccomm.R;
import com.example.vhisyhnu.iaccomm.Student.Announcement.Announcements;
import com.example.vhisyhnu.iaccomm.Student.Complaint.StudentComplaint;
import com.example.vhisyhnu.iaccomm.Student.Dashboard;
import com.example.vhisyhnu.iaccomm.Student.ResetPassword;
import com.example.vhisyhnu.iaccomm.Student.StudentDashboard;
import com.google.firebase.auth.FirebaseAuth;

public class InternetSolution4 extends AppCompatActivity {

    private Button yesBut, noBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_solution4);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        yesBut = (Button) findViewById(R.id.yesBut);
        noBut = (Button) findViewById(R.id.noBut);

        yesBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InternetSolution4.this, StudentDashboard.class);
                startActivity(i);
            }
        });

        noBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InternetSolution4.this, StudentComplaint.class);
                startActivity(i);
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
        switch (item.getItemId()) {
            case R.id.nav_home:
                startActivity(new Intent(InternetSolution4.this, StudentDashboard.class));
                finish();
                break;
            case R.id.nav_db:
                startActivity(new Intent(InternetSolution4.this, Dashboard.class));
                finish();
                break;
            case R.id.nav_announcements:
                startActivity(new Intent(InternetSolution4.this, Announcements.class));
                finish();
                break;
            case R.id.nav_change:
                startActivity(new Intent(InternetSolution4.this, ResetPassword.class));
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
