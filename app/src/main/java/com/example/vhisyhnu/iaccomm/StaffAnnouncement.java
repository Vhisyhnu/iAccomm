package com.example.vhisyhnu.iaccomm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StaffAnnouncement extends AppCompatActivity {

    EditText editTextSubject, editTextAnnouncement;
    Button buttonAdd;

    DatabaseReference databaseAnnouncements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_announcement);

        databaseAnnouncements = FirebaseDatabase.getInstance().getReference("Announcement");

        editTextSubject = (EditText) findViewById(R.id.editTextSubject);
        editTextAnnouncement = (EditText) findViewById(R.id.editTextAnnouncement);
        buttonAdd = (Button) findViewById(R.id.buttonAddAnnouncement);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAnnouncement();
            }
        });
    }
    private void addAnnouncement() {
        String subject = editTextSubject.getText().toString().trim();
        String sannouncement = editTextAnnouncement.getText().toString().trim();



        if (!TextUtils.isEmpty(subject)) {
            String id = databaseAnnouncements.push().getKey();
            Announcement announcement = new Announcement(id, subject, sannouncement);

            databaseAnnouncements.child(id).setValue(announcement);

            Toast.makeText(this, "Announcement Updated", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "You should enter your Subject", Toast.LENGTH_LONG).show();
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
                startActivity(new Intent(StaffAnnouncement.this, StaffDashboard.class));
                finish();
            case R.id.nav_status2:
                startActivity(new Intent(StaffAnnouncement.this, Status.class));
                finish();
                break;
            case R.id.nav_settings2:
                startActivity(new Intent(StaffAnnouncement.this, Settings.class));
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
