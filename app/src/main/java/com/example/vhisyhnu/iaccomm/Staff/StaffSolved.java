package com.example.vhisyhnu.iaccomm.Staff;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vhisyhnu.iaccomm.LoginActivity;
import com.example.vhisyhnu.iaccomm.R;
import com.example.vhisyhnu.iaccomm.Student.Complaint.StudentDetails;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StaffSolved extends AppCompatActivity {

    private int count = 0;

    private DatabaseReference databaseReference, databaseReference2,databaseReference3, reference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    private RecyclerView mBlogList;
    private LinearLayoutManager mLayoutManager;

    @Nullable
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_solved);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child(firebaseUser.getUid()).child("Solved");
        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("User").child(firebaseUser.getUid()).child("In Process");
        reference = FirebaseDatabase.getInstance().getReference();

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        mBlogList = (RecyclerView) findViewById(R.id.user_id2);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<StudentDetails, StudentAdapter> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<StudentDetails, StaffSolved.StudentAdapter>
                (StudentDetails.class,R.layout.staff_solved,StaffSolved.StudentAdapter.class,databaseReference) {
            @Override
            protected void populateViewHolder(final StaffSolved.StudentAdapter viewHolder, final StudentDetails items, int position){
                viewHolder.setDate(items.getDate());
                viewHolder.setTime(items.getTime());
                viewHolder.setName(items.getName());
                viewHolder.setID(items.getID());
                viewHolder.setRoomNumber(items.getRoomNumber());
                viewHolder.setPhoneNumber(items.getPhoneNumber());
                viewHolder.setComplaint(items.getComplain());
                viewHolder.setDesc(items.getDesc());

                final String post_id = this.getRef(position).getKey();
                final DatabaseReference dataRef = databaseReference.child(post_id);

                viewHolder.clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new AlertDialog.Builder(StaffSolved.this)
                                .setMessage("Clear Complaint ?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int k) {
                                        Log.d("Alert Dialog", "Positive");
                                        dataRef.removeValue();
                                    }

                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int y) {
                                        Log.d("AlertDialog", "Negative");
                                    }
                                })
                                .show();

                    }
                });
            }
        };

        mBlogList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class StudentAdapter extends RecyclerView.ViewHolder
    {
        View mView;
        public StudentAdapter(View itemView)
        {
            super(itemView);
            mView = itemView;

        }

        public void setDate(String Date)
        {
            TextView post_date = (TextView)mView.findViewById(R.id.date);
            post_date.setText(Date);
        }

        public void setTime(String Time)
        {
            TextView post_time = (TextView)mView.findViewById(R.id.time);
            post_time.setText(Time);
        }

        public void setName(String Name)
        {
            TextView post_name = (TextView)mView.findViewById(R.id.name);
            post_name.setText(Name);
        }

        public void setID(String ID)
        {
            TextView post_id = (TextView)mView.findViewById(R.id.id);
            post_id.setText(ID);
        }

        public void setRoomNumber(String RoomNumber)
        {
            TextView post_room = (TextView)mView.findViewById(R.id.roomnum);
            post_room.setText(RoomNumber);
        }

        public void setPhoneNumber (String PhoneNumber)
        {
            TextView post_phone = (TextView)mView.findViewById(R.id.contact);
            post_phone.setText(PhoneNumber);
        }

        public void setComplaint(String Complaint)
        {
            TextView post_complain = (TextView)mView.findViewById(R.id.complaincategory);
            post_complain.setText(Complaint);
        }

        public void setDesc(String Desc)
        {
            TextView post_desc = (TextView)mView.findViewById(R.id.complaindetails);
            post_desc.setText(Desc);
        }
        Button clear = itemView.findViewById(R.id.clear);
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
                startActivity(new Intent(StaffSolved.this, StaffDashboard.class));
                finish();
                break;
            case R.id.st_announ:
                startActivity(new Intent(StaffSolved.this, StaffAnnouncement.class));
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
