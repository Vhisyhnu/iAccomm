package com.example.vhisyhnu.iaccomm.Staff.Search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.vhisyhnu.iaccomm.LoginActivity;
import com.example.vhisyhnu.iaccomm.R;
import com.example.vhisyhnu.iaccomm.Staff.ComplaintDashboard;
import com.example.vhisyhnu.iaccomm.Staff.StaffAnnouncement;
import com.example.vhisyhnu.iaccomm.Staff.StaffDashboard;
import com.example.vhisyhnu.iaccomm.Staff.StaffRequest;
import com.example.vhisyhnu.iaccomm.Student.Complaint.StudentDetails;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class IdRecycler extends AppCompatActivity {

    private int count = 0;

    private DatabaseReference databaseReference,reference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    private RecyclerView mBlogList;
    private LinearLayoutManager mLayoutManager;

    @Nullable
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.id_recycler);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child(firebaseUser.getUid()).child("All Details");
        reference = FirebaseDatabase.getInstance().getReference();

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        mBlogList = (RecyclerView) findViewById(R.id.student_id);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onStart() {
        super.onStart();

        Intent intent2 = getIntent();

        final String Id = intent2.getStringExtra("Student");

        FirebaseRecyclerAdapter<StudentDetails, IdRecycler.StudentAdapter> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<StudentDetails, IdRecycler.StudentAdapter>
                (StudentDetails.class,R.layout.month_recycler_view,IdRecycler.StudentAdapter.class,databaseReference.orderByChild("id").equalTo(Id)) {
            @Override
            protected void populateViewHolder(final IdRecycler.StudentAdapter viewHolder, final StudentDetails items, int position){

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
                startActivity(new Intent(IdRecycler.this, StaffDashboard.class));
                finish();
                break;
            case R.id.st_announ:
                startActivity(new Intent(IdRecycler.this, StaffAnnouncement.class));
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
