package com.example.vhisyhnu.iaccomm.Student.Complaint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.vhisyhnu.iaccomm.LoginActivity;
import com.example.vhisyhnu.iaccomm.R;
import com.example.vhisyhnu.iaccomm.Staff.StaffDashboard;
import com.example.vhisyhnu.iaccomm.Student.Announcement.Announcements;
import com.example.vhisyhnu.iaccomm.Student.Dashboard;
import com.example.vhisyhnu.iaccomm.Student.ResetPassword;
import com.example.vhisyhnu.iaccomm.Student.StudentDashboard;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyComplaint extends AppCompatActivity {

    private RecyclerView mBlogList;
    private DatabaseReference mDatabase, mDatabase1, mDatabase2, mDatabase3, mDatabase4, test;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private LinearLayoutManager mLayoutManager;
    private TextView Status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_complaint);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String uid1 = "b2kAG7ljs5YKYaZsz9cPjBRJreG2";

        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("User").child(firebaseUser.getUid()).child("My Complaint");
        mDatabase1 = FirebaseDatabase.getInstance().getReference().child("User").child(firebaseUser.getUid()).child("My Complaint").child("desc");
        mDatabase2 = FirebaseDatabase.getInstance().getReference().child("User").child("b2kAG7ljs5YKYaZsz9cPjBRJreG2").child("Student Details").child("desc");
        mDatabase3 = FirebaseDatabase.getInstance().getReference().child("User").child("b2kAG7ljs5YKYaZsz9cPjBRJreG2").child("Solved").child("desc");
        mDatabase4 = FirebaseDatabase.getInstance().getReference().child("User").child("b2kAG7ljs5YKYaZsz9cPjBRJreG2").child("In Process").child("desc");
        test = FirebaseDatabase.getInstance().getReference().child("Complaint").child("-LTNd1NmLAfnI7NIceHm").child("studentDescription");

        mDatabase.keepSynced(true);

        mLayoutManager = new LinearLayoutManager(MyComplaint.this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        mBlogList = findViewById(R.id.complain_id);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(mLayoutManager);

        Status = findViewById(R.id.complainstatus);



    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<StudentDetails,StudentAdapter> firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<StudentDetails, StudentAdapter>
                (StudentDetails.class,R.layout.my_complaint,StudentAdapter.class,mDatabase) {
            @Override
            protected void populateViewHolder(StudentAdapter viewHolder,StudentDetails items,int position) {

                viewHolder.setDate(items.getDate());
                viewHolder.setTime(items.getTime());
                viewHolder.setName(items.getName());
                viewHolder.setID(items.getID());
                viewHolder.setRoomNumber(items.getRoomNumber());
                viewHolder.setPhoneNumber(items.getPhoneNumber());
                viewHolder.setComplaint(items.getComplain());
                viewHolder.setDesc(items.getDesc());
            }


        };

        mBlogList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class StudentAdapter extends RecyclerView.ViewHolder{
        View mView;
        public StudentAdapter(View itemView){
            super(itemView);
            mView=itemView;
        }


        public void setDate(String Date)
        {
            TextView post_date = mView.findViewById(R.id.date);
            post_date.setText(Date);
        }

        public void setTime(String Time)
        {
            TextView post_time = mView.findViewById(R.id.time);
            post_time.setText(Time);
        }

        public void setName(String Name)
        {
            TextView post_name = mView.findViewById(R.id.name);
            post_name.setText(Name);
        }

        public void setID(String ID)
        {
            TextView post_id = mView.findViewById(R.id.id);
            post_id.setText(ID);
        }

        public void setRoomNumber(String RoomNumber)
        {
            TextView post_room = mView.findViewById(R.id.roomnum);
            post_room.setText(RoomNumber);
        }

        public void setPhoneNumber (String PhoneNumber)
        {
            TextView post_phone = mView.findViewById(R.id.contact);
            post_phone.setText(PhoneNumber);
        }

        public void setComplaint(String Complaint)
        {
            TextView post_complain = mView.findViewById(R.id.complaincategory);
            post_complain.setText(Complaint);
        }

        public void setDesc(String Desc)
        {
            TextView post_desc = mView.findViewById(R.id.complaindetails);
            post_desc.setText(Desc);
        }

        public void setStatus(String Status)
        {
            TextView post_status = mView.findViewById(R.id.complainstatus);
            post_status.setText(Status);
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drawermenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.nav_home:
                startActivity(new Intent(MyComplaint.this, StudentDashboard.class));
                finish();
                break;
            case R.id.nav_db:
                startActivity(new Intent(MyComplaint.this, Dashboard.class));
                finish();
                break;
            case R.id.nav_announcements:
                startActivity(new Intent(MyComplaint.this, Announcements.class));
                finish();
                break;
            case R.id.nav_change:
                startActivity(new Intent(MyComplaint.this, ResetPassword.class));
                finish();
                break;
            case R.id.logout:
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
