package com.example.vhisyhnu.iaccomm;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

public class Complaint extends AppCompatActivity {


    Dialog epicDialog;
    TextView titleTv, messagetv;
    ImageView closePopupElectricity, closePopupInternet;
    Button butElectric, butOther, butInternet, btnAgree, btnDisagree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        butOther = (Button) findViewById(R.id.butOther);
        butElectric = (Button) findViewById(R.id.butElectric);
        butInternet = (Button) findViewById(R.id.butInternet);
        epicDialog = new Dialog(this);


        butElectric.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ShowElectricityPopup();
            }
        });

        butInternet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ShowInternetPopup();
            }
        });

        butOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toy = new Intent(Complaint.this, StudentComplaint.class);

                startActivity(toy);

            }
        });

    }

    public void ShowElectricityPopup(){
        epicDialog.setContentView(R.layout.epic_popup_electricity);
        closePopupElectricity =(ImageView) epicDialog.findViewById(R.id.closePopupElectricity);
        titleTv= (TextView) epicDialog.findViewById(R.id.titleTv);
        messagetv = (TextView) epicDialog.findViewById(R.id.messageTv);
        btnAgree = (Button) epicDialog.findViewById(R.id.agree);
        btnDisagree = (Button) epicDialog.findViewById(R.id.disagree);

        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toy = new Intent(Complaint.this,ElectricityAutoSolution.class);

                startActivity(toy);

            }
        });

        btnDisagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toy = new Intent(Complaint.this,StudentComplaint.class);

                startActivity(toy);

            }
        });

        closePopupElectricity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                epicDialog.dismiss();
            }
        });

        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();



    }

    public void ShowInternetPopup(){
        epicDialog.setContentView(R.layout.epic_popup_internet);
        closePopupInternet =(ImageView) epicDialog.findViewById(R.id.closePopupInternet);
        titleTv= (TextView) epicDialog.findViewById(R.id.titleTv);
        messagetv = (TextView) epicDialog.findViewById(R.id.messageTv);
        btnAgree = (Button) epicDialog.findViewById(R.id.agree2);
        btnDisagree = (Button) epicDialog.findViewById(R.id.disagree2);

        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toy = new Intent(Complaint.this,InternetAutoSolution.class);

                startActivity(toy);

            }
        });

        btnDisagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toy = new Intent(Complaint.this,StudentComplaint.class);

                startActivity(toy);

            }
        });

        closePopupInternet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                epicDialog.dismiss();
            }
        });

        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();



    }


}
