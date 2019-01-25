package com.example.vhisyhnu.iaccomm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.vhisyhnu.iaccomm.Announcements;
import com.example.vhisyhnu.iaccomm.R;
import com.example.vhisyhnu.iaccomm.Announcement;

import java.util.ArrayList;

/**
 * Created by Oclemy on 6/21/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 * 1. where WE INFLATE OUR MODEL LAYOUT INTO VIEW ITEM
 * 2. THEN BIND DATA
 */
public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<Announcement> announcements;

    public CustomAdapter(Context c, ArrayList<Announcement> announcements) {
        this.c = c;
        this.announcements = announcements;
    }

    @Override
    public int getCount() {
        return announcements.size();
    }

    @Override
    public Object getItem(int pos) {
        return announcements.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(c).inflate(R.layout.model,viewGroup,false);
        }

        TextView subTxt= (TextView) convertView.findViewById(R.id.subTxt);
        TextView descTxt= (TextView) convertView.findViewById(R.id.descTxt);

        final Announcement s= (Announcement) this.getItem(position);

        subTxt.setText(s.getStaffSubject());
        descTxt.setText(s.getStaffAnnouncement());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //OPEN DETAIL
                openDetailActivity(s.getStaffSubject(),s.getStaffAnnouncement());
            }
        });

        return convertView;
    }
    //OPEN DETAIL ACTIVITY
    private void openDetailActivity(String...details)
    {
        Intent i=new Intent(c,Announcements.class);
        i.putExtra("SUB_KEY",details[0]);
        i.putExtra("DESC_KEY",details[1]);

        c.startActivity(i);
    }
}














