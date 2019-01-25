package com.example.vhisyhnu.iaccomm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class InternetAutoSolution extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_auto_solution);

        listView = (ListView) findViewById(R.id.listview);
        String items[]= new String[] {"test", "test2"};

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){

                    Intent solution = new Intent(view.getContext(),StaffNotify.class);

                    startActivityForResult(solution, 0);
                }
                if (position == 1){

                    Intent solution = new Intent(view.getContext(),StaffNotify.class);

                    startActivityForResult(solution, 0);

                }

            }
        });
    }
}
