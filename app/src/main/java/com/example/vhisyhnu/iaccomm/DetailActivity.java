package com.example.vhisyhnu.iaccomm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView subTxt,descTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        subTxt = (TextView) findViewById(R.id.subDetailTxt);
        descTxt= (TextView) findViewById(R.id.descDetailTxt);


        //GET INTENT
        Intent i=this.getIntent();


        //RECEIVE DATA
        String sub=i.getExtras().getString("SUB_KEY");
        String desc=i.getExtras().getString("DESC_KEY");

        //BIND DATA
        subTxt.setText(sub);
        descTxt.setText(desc);
    }
}
