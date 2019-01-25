package com.example.vhisyhnu.iaccomm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StaffNotify extends AppCompatActivity {

    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_notify);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mDatabase=FirebaseDatabase.getInstance().getReference().child("Notification");
        mDatabase.keepSynced(true);

        mBlogList=(RecyclerView)findViewById(R.id.lmyrecycleview);
        mBlogList.setHasFixedSize(true);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        mBlogList.setLayoutManager(mLayoutManager);

    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<nBlog,BlogViewHolder>firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<nBlog, BlogViewHolder>
                (nBlog.class,R.layout.nblog_row,BlogViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, nBlog model, int position) {

                viewHolder.setStudentNum(model.getStudentNum());
                viewHolder.setStudentHostel(model.getStudentHostel());
                viewHolder.setStudentRoom(model.getStudentRoom());
                viewHolder.setStudentNotify(model.getStudentNotify());

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

        public void setStudentNotify(String studentNotify){
            TextView post_desc=(TextView)mView.findViewById(R.id.post_desc);
            post_desc.setText(studentNotify);
        }

    }

}
