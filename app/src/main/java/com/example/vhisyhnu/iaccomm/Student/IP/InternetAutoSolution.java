package com.example.vhisyhnu.iaccomm.Student.IP;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.vhisyhnu.iaccomm.LoginActivity;
import com.example.vhisyhnu.iaccomm.R;
import com.example.vhisyhnu.iaccomm.Staff.StaffNotify;
import com.example.vhisyhnu.iaccomm.Student.Announcement.Announcements;
import com.example.vhisyhnu.iaccomm.Student.Complaint.StudentComplaint;
import com.example.vhisyhnu.iaccomm.Student.Dashboard;
import com.example.vhisyhnu.iaccomm.Student.ResetPassword;
import com.example.vhisyhnu.iaccomm.Student.StudentDashboard;
import com.google.firebase.auth.FirebaseAuth;

public class InternetAutoSolution extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_auto_solution);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0){

            Intent solution = new Intent(view.getContext(), InternetSolution.class);

            startActivityForResult(solution, 0);
        }
        else if (position == 1){

            Intent solution = new Intent(view.getContext(), InternetSolution1.class);

            startActivityForResult(solution, 0);

        }
        else if (position == 2){

            Intent solution = new Intent(view.getContext(), InternetSolution2.class);

            startActivityForResult(solution, 0);

        }
        else if (position == 3){

            Intent solution = new Intent(view.getContext(), InternetSolution3.class);

            startActivityForResult(solution, 0);

        }
        else if (position == 4){

            Intent solution = new Intent(view.getContext(), InternetSolution4.class);

            startActivityForResult(solution, 0);

        }
        else {
            Intent solution = new Intent(view.getContext(), StudentComplaint.class);

            startActivityForResult(solution, 0);
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
                startActivity(new Intent(InternetAutoSolution.this, StudentDashboard.class));
                finish();
                break;
            case R.id.nav_db:
                startActivity(new Intent(InternetAutoSolution.this, Dashboard.class));
                finish();
                break;
            case R.id.nav_announcements:
                startActivity(new Intent(InternetAutoSolution.this, Announcements.class));
                finish();
                break;
            case R.id.nav_change:
                startActivity(new Intent(InternetAutoSolution.this, ResetPassword.class));
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
