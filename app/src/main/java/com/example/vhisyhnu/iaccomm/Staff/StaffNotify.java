package com.example.vhisyhnu.iaccomm.Staff;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import android.widget.Toast;

import com.example.vhisyhnu.iaccomm.LoginActivity;
import com.example.vhisyhnu.iaccomm.R;
import com.example.vhisyhnu.iaccomm.Student.Notify.Notify;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class StaffNotify extends AppCompatActivity implements ImageAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    private RecyclerView mBlogList;
    private Button Clear;



    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private List<Notify> mUploads;
    private ValueEventListener mDBListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_notify);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView=findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Clear = findViewById(R.id.clearBut);

        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        mUploads=new ArrayList<>();
        mAdapter=new ImageAdapter(StaffNotify.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(StaffNotify.this);

        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef=FirebaseDatabase.getInstance().getReference("Notification");

        mDBListener =  mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mUploads.clear();

                for(DataSnapshot postSnapshot:dataSnapshot.getChildren())
                {
                    Notify upload=postSnapshot.getValue(Notify.class);
                    upload.setKey(postSnapshot.getKey());
                    mUploads.add(upload);

                }

                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(StaffNotify.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
    }
    @Override
    public void onDeleteClick(int position) {
        Notify selectedItem = mUploads.get(position);
        final String selectedKey = selectedItem.getKey();

        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getImageUrl());
        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mDatabaseRef.child(selectedKey).removeValue();
                Toast.makeText(StaffNotify.this, "Notification Cleared", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
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
                startActivity(new Intent(StaffNotify.this, StaffDashboard.class));
                finish();
                break;
            case R.id.st_announ:
                startActivity(new Intent(StaffNotify.this, StaffAnnouncement.class));
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
