package com.example.vhisyhnu.iaccomm.Student.Announcement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;

import com.example.vhisyhnu.iaccomm.R;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DetailsActivity extends AppCompatActivity {

    TextView nameDetailTextView,descriptionDetailTextView,dateDetailTextView, timeDetailTextView;

    private void initializeWidgets(){
        nameDetailTextView= findViewById(R.id.nameDetailTextView);
        descriptionDetailTextView= findViewById(R.id.descriptionDetailTextView);
        dateDetailTextView= findViewById(R.id.dateDetailTextView);
        timeDetailTextView= findViewById(R.id.timeDetailTextView);

    }
    private String getDateToday(){
        DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
        Date date=new Date();
        String today= dateFormat.format(date);
        return today;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initializeWidgets();

        Intent i=this.getIntent();
        String time=i.getExtras().getString("TIME_KEY");
        String date=i.getExtras().getString("DATE_KEY");
        String name=i.getExtras().getString("SUBJECT_KEY");
        String description=i.getExtras().getString("ANNOUNCEMENT_KEY");

        nameDetailTextView.setText(name);
        descriptionDetailTextView.setText(description);
        timeDetailTextView.setText(time);
        dateDetailTextView.setText(date);

    }

}
