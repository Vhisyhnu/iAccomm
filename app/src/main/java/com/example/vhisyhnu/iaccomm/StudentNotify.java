package com.example.vhisyhnu.iaccomm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class StudentNotify extends AppCompatActivity {

    EditText editTextNum, editTextRoom, editTextNotify;
    ImageView imageView;
    Button buttonAdd, btnUpload, btnChoose;
    Spinner spinnerHostels;
    Uri filepath;
    final int PICK_IMAGE_REQUEST = 71;

    FirebaseStorage storage;
    StorageReference storageReference;

    DatabaseReference databaseNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_notify);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        databaseNotification = FirebaseDatabase.getInstance().getReference("Notification");

        editTextNum = (EditText) findViewById(R.id.editTextNum);
        spinnerHostels = (Spinner) findViewById(R.id.spinnerHostels);
        editTextRoom = (EditText) findViewById(R.id.editTextRoom);
        editTextNotify = (EditText) findViewById(R.id.editTextNotify);
        buttonAdd = (Button) findViewById(R.id.buttonAddNotify);
        btnChoose = (Button) findViewById(R.id.btnChoose);
        btnUpload =(Button) findViewById(R.id.btnUpload);
        imageView = (ImageView) findViewById(R.id.imgView);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNotify();
            }
        });

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
            }
        });
    }

    private void uploadImage(){
        if(filepath!=null){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filepath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(StudentNotify.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(StudentNotify.this, "Failed"+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress =(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded"+(int)progress+"%");
                        }
                    });
        }
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            filepath= data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                imageView.setImageBitmap(bitmap);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    private void addNotify() {
        String num = editTextNum.getText().toString().trim();
        String hostel = spinnerHostels.getSelectedItem().toString();
        String room = editTextRoom.getText().toString().trim();
        String anotify = editTextNotify.getText().toString().trim();


        if (!TextUtils.isEmpty(num)) {
            String id = databaseNotification.push().getKey();
            Notify notify = new Notify(id, num, hostel, room, anotify);

            databaseNotification.child(id).setValue(notify);

            Toast.makeText(this, "Notification Sent", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Dashboard.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "You should enter your Student Id", Toast.LENGTH_LONG).show();
        }

    }
}
