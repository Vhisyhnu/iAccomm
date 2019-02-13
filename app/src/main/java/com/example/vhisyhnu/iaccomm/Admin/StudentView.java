package com.example.vhisyhnu.iaccomm.Admin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vhisyhnu.iaccomm.Chat.Adapter.UserAdapter;
import com.example.vhisyhnu.iaccomm.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentView extends AppCompatActivity {

    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;

    private List<Student> mUsers;

    EditText search_users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Student");
        mDatabase.keepSynced(true);

        mUsers = new ArrayList<>();

        mBlogList= findViewById(R.id.listview_student);
        mBlogList.setHasFixedSize(true);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        mBlogList.setLayoutManager(mLayoutManager);

        search_users = findViewById(R.id.search_users);
        search_users.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchUsers(charSequence.toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void searchUsers(String s) {

        final FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();
        Query query = FirebaseDatabase.getInstance().getReference("Student").orderByChild("studentId")
                .startAt(s)
                .endAt(s+"\uf8ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Student user = snapshot.getValue(Student.class);

                    assert user != null;
                    assert fuser != null;

                        mUsers.add(user);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<Student,StudentView.BlogViewHolder> firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<Student, StudentView.BlogViewHolder>
                (Student.class,R.layout.student_view, StudentView.BlogViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(StudentView.BlogViewHolder viewHolder, Student model, int position) {

                viewHolder.setName(model.getName());
                viewHolder.setStudentId(model.getStudentId());
                viewHolder.setEmail(model.getEmail());

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

        public void setName(String name){
            TextView post_Name=(TextView)mView.findViewById(R.id.sName);
            post_Name.setText(name);
        }

        public void setStudentId(String studentId){
            TextView post_Id=(TextView)mView.findViewById(R.id.sId);
            post_Id.setText(studentId);
        }

        public void setEmail(String email){
            TextView post_Email=(TextView)mView.findViewById(R.id.sEmail);
            post_Email.setText(email);
        }




    }



}
