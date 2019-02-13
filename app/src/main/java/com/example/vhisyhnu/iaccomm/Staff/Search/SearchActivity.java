package com.example.vhisyhnu.iaccomm.Staff.Search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.vhisyhnu.iaccomm.LoginActivity;
import com.example.vhisyhnu.iaccomm.R;
import com.example.vhisyhnu.iaccomm.Staff.ComplaintDashboard;
import com.example.vhisyhnu.iaccomm.Staff.StaffAnnouncement;
import com.example.vhisyhnu.iaccomm.Staff.StaffComplaint;
import com.example.vhisyhnu.iaccomm.Staff.StaffDashboard;
import com.example.vhisyhnu.iaccomm.Staff.StaffNotify;
import com.example.vhisyhnu.iaccomm.Staff.StaffRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.Month;

public class SearchActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference reference;

    Spinner spinner;
    Button mSearch, iSearch;
    EditText StudentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner = findViewById(R.id.monthsC);

        mSearch = findViewById(R.id.search_month);

        iSearch = findViewById(R.id.search_id);

        StudentID = findViewById(R.id.studentidsearch);

        reference = FirebaseDatabase.getInstance().getReference().child("User").child(firebaseUser.getUid()).child("All Details");

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.months));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(SearchActivity.this,MonthRecycler.class);

                intent1.putExtra("Month",spinner.getSelectedItem().toString());

                startActivityForResult(intent1,1);
            }
        });

        iSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(SearchActivity.this,IdRecycler.class);

                intent1.putExtra("Student",StudentID.getText().toString());

                startActivityForResult(intent1,1);
            }
        });
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
                startActivity(new Intent(SearchActivity.this, StaffDashboard.class));
                finish();
                break;
            case R.id.st_announ:
                startActivity(new Intent(SearchActivity.this, StaffAnnouncement.class));
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
