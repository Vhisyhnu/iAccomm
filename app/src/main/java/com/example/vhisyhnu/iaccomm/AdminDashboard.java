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

public class AdminDashboard extends AppCompatActivity implements View.OnClickListener  {

    private CardView regstaffCard, regstudentCard, vstaffCard, vstudentCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        regstaffCard = (CardView) findViewById(R.id.regstaff_card);
        regstudentCard =(CardView) findViewById(R.id.regstudent_card);
        vstaffCard =(CardView) findViewById(R.id.vstaff_card);
        vstudentCard = (CardView) findViewById(R.id.vstudent_card);

        regstaffCard.setOnClickListener(this);
        regstudentCard.setOnClickListener(this);
        vstaffCard.setOnClickListener(this);
        vstudentCard.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.regstaff_card : i =new Intent(this,StaffRegister.class);startActivity(i); break;
            case R.id.regstudent_card : i = new Intent(this,StudentRegister.class);startActivity(i); break;
            case R.id.vstaff_card : i = new Intent(this,StaffRequest.class);startActivity(i); break;
            case R.id.vstudent_card : i = new Intent(this,StaffNotify.class);startActivity(i); break;
            default:break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drawermenu3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.nav_db3:
                startActivity(new Intent(AdminDashboard.this, AdminDashboard.class));
                finish();
                break;

            case R.id.logout3:
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
