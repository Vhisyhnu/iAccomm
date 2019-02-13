package com.example.vhisyhnu.iaccomm.Staff;

import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.vhisyhnu.iaccomm.Student.Request.rBlog;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StaffRequest extends AppCompatActivity {

    private RecyclerView mBlogList;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_request);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        mDatabase=FirebaseDatabase.getInstance().getReference().child("User").child(firebaseUser.getUid()).child("Request");
        mDatabase.keepSynced(true);

        mBlogList=findViewById(R.id.mmyrecycleview);
        mBlogList.setHasFixedSize(true);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        mBlogList.setLayoutManager(mLayoutManager);

    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<rBlog,BlogViewHolder> firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<rBlog,BlogViewHolder>
                (rBlog.class,R.layout.rblog_row,BlogViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, rBlog model, int position) {

                viewHolder.setDate(model.getDate());
                viewHolder.setTime(model.getTime());
                viewHolder.setName(model.getName());
                viewHolder.setID(model.getID());
                viewHolder.setRoomNumber(model.getRoomNumber());
                viewHolder.setPhoneNumber(model.getPhoneNumber());
                viewHolder.setStudentRequest(model.getStudentRequest());

                final String post_id = getRef(position).getKey();
                final DatabaseReference dataRef = mDatabase.child(post_id);
                viewHolder.Clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(StaffRequest.this)
                                .setMessage("Clear Request?")
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

    public static class BlogViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public BlogViewHolder(View itemView){
            super(itemView);
            mView=itemView;
        }

        public void setDate(String date){
            TextView post_date=mView.findViewById(R.id.post_date);
            post_date.setText(date);
        }

        public void setTime(String time){
            TextView post_time=mView.findViewById(R.id.post_time);
            post_time.setText(time);
        }

        public void setName(String name){
            TextView post_name=mView.findViewById(R.id.post_name);
            post_name.setText(name);
        }

        public void setID(String id){
            TextView post_id=mView.findViewById(R.id.post_id);
            post_id.setText(id);
        }

        public void setRoomNumber(String room){
            TextView post_room=mView.findViewById(R.id.post_room);
            post_room.setText(room);
        }

        public void setPhoneNumber(String number){
            TextView post_number=mView.findViewById(R.id.post_number);
            post_number.setText(number);
        }

        public void setStudentRequest(String notify){
            TextView post_request=mView.findViewById(R.id.post_request);
            post_request.setText(notify);
        }

        Button Clear = itemView.findViewById(R.id.clearBut);

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
                startActivity(new Intent(StaffRequest.this, StaffDashboard.class));
                finish();
                break;
            case R.id.st_announ:
                startActivity(new Intent(StaffRequest.this, StaffAnnouncement.class));
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
