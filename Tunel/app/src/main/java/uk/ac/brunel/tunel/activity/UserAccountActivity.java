/*
 * Created by Mohamed Bushra on 09/02/17 20:36
 * Copyright (c) 2017. All rights reserved.
 *
 * Last Modified 09/02/17 18:45.
 */

package uk.ac.brunel.tunel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
    private EditText userStudentID;
    private Button btnLogout, btnSaveInfo;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private Spinner user_course;
    private Spinner user_courseyear;
    private Spinner gtaPal;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useraccount);

        userEmail = (TextView) findViewById(R.id.view_user_email);
        user_course = (Spinner) findViewById(R.id.course_spinner);
        user_courseyear = (Spinner) findViewById(R.id.courseyear_spinner);
        gtaPal = (Spinner) findViewById(R.id.gtapal_spinner);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        mAuth = FirebaseAuth.getInstance();
        btnLogout.setOnClickListener(this);

        /*Create an ArrayAdapter using the string array and a default spinner layout
        Create an ArrayAdapter using the string array and a default spinner layout\
        Apply the adapter to the spinner
         */
        ArrayAdapter<CharSequence> course_adapter = ArrayAdapter.createFromResource(this,
                R.array.course_level, android.R.layout.simple_spinner_item);
        course_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        user_course.setAdapter(course_adapter);

        ArrayAdapter<CharSequence> courseyear_adapter = ArrayAdapter.createFromResource(this,
                R.array.course_year, android.R.layout.simple_spinner_item);
        courseyear_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        user_courseyear.setAdapter(courseyear_adapter);

        ArrayAdapter<CharSequence> gtapal_adapter = ArrayAdapter.createFromResource(this,
                R.array.gta_pal, android.R.layout.simple_spinner_item);
        course_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gtaPal.setAdapter(gtapal_adapter);

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
        user_course = (Spinner) findViewById(R.id.course_spinner);
        user_courseyear = (Spinner) findViewById(R.id.courseyear_spinner);
        gtaPal = (Spinner) findViewById(R.id.gtapal_spinner);
        userStudentID = (EditText) findViewById(R.id.student_ID);
        btnSaveInfo = (Button) findViewById(R.id.save_info);
        btnSaveInfo.setOnClickListener(this);

         FirebaseUser user = mAuth.getCurrentUser();
        userEmail.setText("Hello "+ user.getEmail());

    }

    private void savedUserInfo()
    {
        String name = userFullName.getText().toString().trim();
        String course = user_course.getSelectedItem().toString().trim();
        String course_year = user_courseyear.getSelectedItem().toString().trim();
        String student_id = userStudentID.getText().toString().trim();
        String user_gtapal = gtaPal.getSelectedItem().toString().trim();

        UserInformation userInformation = new UserInformation(name, course,
                course_year, student_id, user_gtapal);
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

