/*
 * Created by Mohamed Bushra on 08/02/17 17:02
 * Copyright (c) 2017. All rights reserved.
 *
 * Last Modified 08/02/17 15:15.
 */

package uk.ac.brunel.tunel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import uk.ac.brunel.tunel.R;

public class UserAccountActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText userFullName;
    private TextView userEmail;
    private EditText userCourse;
    private EditText userCourseYear;
    private EditText userStudentID;
    private Button btnLogout, btnSaveInfo;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useraccount);

        userEmail = (TextView) findViewById(R.id.view_user_email);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        mAuth = FirebaseAuth.getInstance();
        btnLogout.setOnClickListener(this);

        /*
          Checking if the user is not logged in
         */
        if(mAuth.getCurrentUser() == null){
            /*
              If user is logged in, close this activity
              and direct user to the sign in page
             */
            finish();
            startActivity(new Intent(this, SignInActivity.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();

        userEmail = (TextView) findViewById(R.id.view_user_email);
        userFullName = (EditText) findViewById(R.id.fullName);
        userCourse = (EditText) findViewById(R.id.user_course);
        userCourseYear = (EditText) findViewById(R.id.user_course_year);
        userStudentID = (EditText) findViewById(R.id.student_ID);
        btnSaveInfo = (Button) findViewById(R.id.save_info);
        btnSaveInfo.setOnClickListener(this);

         FirebaseUser user = mAuth.getCurrentUser();
        userEmail.setText("Hello "+ user.getEmail());

    }

    private void savedUserInfo()
    {
        String name = userFullName.getText().toString().trim();
        String course = userCourse.getText().toString().trim();
        String course_year = userCourseYear.getText().toString().trim();
        String student_id = userStudentID.getText().toString().trim();

        UserInformation userInformation = new UserInformation(name, course,
                course_year, student_id);
        FirebaseUser user = mAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userInformation);

        Toast.makeText(this, "Saving information....", Toast.LENGTH_LONG).show();


    }

    @Override
    public void onClick(View v) {

        if(v ==btnLogout) {

            mAuth.signOut();
            finish();
            //Send user to sign in screen
            startActivity(new Intent(this, SignInActivity.class));

        }

        if(v == btnSaveInfo)
        {
            savedUserInfo();
        }


    }
}

