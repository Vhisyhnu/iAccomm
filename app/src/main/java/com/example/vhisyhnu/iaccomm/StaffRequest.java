package com.example.vhisyhnu.iaccomm;

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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StaffRequest extends AppCompatActivity {

    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_request);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mDatabase=FirebaseDatabase.getInstance().getReference().child("Request");
        mDatabase.keepSynced(true);

        mBlogList=(RecyclerView)findViewById(R.id.mmyrecycleview);
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

                viewHolder.setStudentNum(model.getStudentNum());
                viewHolder.setStudentHostel(model.getStudentHostel());
                viewHolder.setStudentRoom(model.getStudentRoom());
                viewHolder.setStudentRequest(model.getStudentRequest());

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

        public void setStudentNum(String studentNum){
            TextView post_num=(TextView)mView.findViewById(R.id.post_num);
            post_num.setText(studentNum);
        }

        public void setStudentHostel(String studentHostel){
            TextView post_hos=(TextView)mView.findViewById(R.id.post_hos);
            post_hos.setText(studentHostel);
        }

        public void setStudentRoom(String studentRoom){
            TextView post_room=(TextView)mView.findViewById(R.id.post_room);
            post_room.setText(studentRoom);
        }

        public void setStudentRequest(String studentRequest){
            TextView post_desc=(TextView)mView.findViewById(R.id.post_desc);
            post_desc.setText(studentRequest);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drawermenu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.nav_db2:
                startActivity(new Intent(StaffRequest.this, StaffDashboard.class));
                finish();
            case R.id.nav_status2:
                startActivity(new Intent(StaffRequest.this, Status.class));
                finish();
                break;
            case R.id.nav_settings2:
                startActivity(new Intent(StaffRequest.this, Settings.class));
                finish();
                break;
            case R.id.logout2:
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
