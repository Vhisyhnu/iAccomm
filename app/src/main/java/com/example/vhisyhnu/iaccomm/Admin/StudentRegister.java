package com.example.vhisyhnu.iaccomm.Admin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vhisyhnu.iaccomm.R;
import com.example.vhisyhnu.iaccomm.Student.Dashboard;
import com.example.vhisyhnu.iaccomm.Student.Request.StudentRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentRegister extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail, editTextPassword, editTextName, editTextId;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;



    private DatabaseReference mRef;

    Spinner spinnerStudent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextEmail = findViewById(R.id.stemail);
        editTextPassword = findViewById(R.id.stpassword);
        editTextName = findViewById(R.id.stname);
        editTextId = findViewById(R.id.stid);
        progressBar = findViewById(R.id.loading);
        spinnerStudent = (Spinner) findViewById(R.id.spinnerStudent);
        progressBar.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();




        findViewById(R.id.stregister).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            //handle the already login user
        }
    }

    private void registerStudent() {
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        assert firebaseUser != null;
        final String email = editTextEmail.getText().toString().trim();
        final String name = editTextName.getText().toString().trim();
        final String id = firebaseUser.getUid();
        String password = editTextPassword.getText().toString().trim();
        final String type = spinnerStudent.getSelectedItem().toString();
        final String studentId = editTextId.getText().toString().trim();



        if (email.isEmpty()) {
            editTextEmail.setError(getString(R.string.input_error_email));
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError(getString(R.string.input_error_email_invalid));
            editTextEmail.requestFocus();
            return;
        }

        if (name.isEmpty()) {
            editTextName.setError(getString(R.string.input_error_name));
            editTextName.requestFocus();
            return;
        }

        if (id.isEmpty()) {
            editTextId.setError(getString(R.string.input_error_id));
            editTextId.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError(getString(R.string.input_error_password));
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError(getString(R.string.input_error_password_length));
            editTextPassword.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Student student = new Student(
                                    email,
                                    name,
                                    studentId,
                                    type,
                                    id
                            );

                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(student).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(StudentRegister.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(StudentRegister.this, AdminDashboard.class);
                                        startActivity(intent);
                                    } else {
                                        //display a failure message
                                    }
                                }
                            });

                            FirebaseDatabase.getInstance().getReference("Student")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(student).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(StudentRegister.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(StudentRegister.this, AdminDashboard.class);
                                        startActivity(intent);
                                    } else {
                                        //display a failure message
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(StudentRegister.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.stregister:
                registerStudent();
                break;
        }
    }
}