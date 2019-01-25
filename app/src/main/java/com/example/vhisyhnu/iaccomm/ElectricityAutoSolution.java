package com.example.vhisyhnu.iaccomm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ElectricityAutoSolution extends AppCompatActivity {

    Button btnYes, btnNo;

    public void init() {
        btnYes = (Button) findViewById(R.id.btnYes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toy = new Intent(ElectricityAutoSolution.this, Dashboard.class);

                startActivity(toy);

            }
        });

        btnNo = (Button) findViewById(R.id.btnNo);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toy = new Intent(ElectricityAutoSolution.this, StudentComplaint.class);

                startActivity(toy);

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity_auto_solution);
        init();
    }
}
