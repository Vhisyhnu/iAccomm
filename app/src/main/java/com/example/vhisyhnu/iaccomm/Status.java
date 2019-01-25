package com.example.vhisyhnu.iaccomm;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class Status extends AppCompatActivity{

    private DrawerLayout dl;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        dl = (DrawerLayout) findViewById(R.id.dl);
        toggle = new ActionBarDrawerToggle(this, dl, R.string.open, R.string.close);
        dl.addDrawerListener(toggle);
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nv);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(nvDrawer);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void selectItemDrawer(MenuItem menuItem) {
        int id= menuItem.getItemId();
        switch (id) {
            case R.id.nav_db:
                Intent d = new Intent(Status.this,Dashboard.class);
                startActivity(d);
                break;
            case R.id.nav_announcements:
                Intent a = new Intent(Status.this,Announcements.class);
                startActivity(a);
                break;
            case R.id.nav_status:
                Intent s = new Intent(Status.this,Status.class);
                startActivity(s);
                break;
            case R.id.nav_settings:
                Intent e = new Intent(Status.this,Settings.class);
                startActivity(e);
                break;
            case R.id.logout:
                Intent i = new Intent(Status.this,Complaint.class);
                startActivity(i);
                break;
        }

    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
    }


}


