package com.example.vhisyhnu.iaccomm.Admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.vhisyhnu.iaccomm.LoginActivity;
import com.example.vhisyhnu.iaccomm.R;
import com.example.vhisyhnu.iaccomm.Staff.StaffNotify;
import com.example.vhisyhnu.iaccomm.Staff.StaffRequest;
import com.google.firebase.auth.FirebaseAuth;

public class AdminDashboard extends AppCompatActivity implements View.OnClickListener  {

    private CardView regstaffCard, regstudentCard, vstaffCard, vstudentCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        regstudentCard =(CardView) findViewById(R.id.regstudent_card);
        vstudentCard = (CardView) findViewById(R.id.vstudent_card);


        regstudentCard.setOnClickListener(this);

        vstudentCard.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {

            case R.id.regstudent_card : i = new Intent(this, StudentRegister.class);startActivity(i); break;

            case R.id.vstudent_card : i = new Intent(this, StudentView.class);startActivity(i); break;
            default:break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.adminmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.ad_db:
                startActivity(new Intent(AdminDashboard.this, AdminDashboard.class));
                finish();
                break;

            case R.id.ad_logout:
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
