/*
 * Created by Mohamed Bushra on 09/02/17 20:36
 * Copyright (c) 2017. All rights reserved.
 *
 * Last Modified 09/02/17 20:36.
 */

package uk.ac.brunel.tunel.activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import uk.ac.brunel.tunel.R;

import static uk.ac.brunel.tunel.R.id.signup_button;


public class SignInActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnLogin;
    private Button btnSignup;
    private EditText userEmail;
    private EditText userPassword;
    private ProgressDialog pDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        userEmail = (EditText) findViewById(R.id.log_email);
        userPassword = (EditText) findViewById(R.id.log_pass);
        btnLogin = (Button) findViewById(R.id.log_button);
        btnSignup = (Button) findViewById(signup_button);

        btnLogin.setOnClickListener(this);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        mAuth = FirebaseAuth.getInstance();

        // Check if user is already logged in
        if(mAuth.getCurrentUser() != null){
            /*
              If user is logged in, close this activity
              and direct user to the forum
             */
            finish();
            startActivity(new Intent(getApplicationContext(), UserAccountActivity.class));
        }

        btnSignup.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                /* Setting a new intent which takes the user from the current screen
                to the next (Sign in --> Sign up screen)
                 */
                Intent RegisterIntent = new Intent(SignInActivity.this, RegisterActivity.class);
                startActivity(RegisterIntent);

            }
        });

    }


    private void userSignIn()
    {
        String email = userEmail.getText().toString().trim();
        String password = userPassword.getText().toString().trim();

        //Check if the fields email and/or password are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter a valid email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password) || password.length()<8){
            Toast.makeText(this,"Please enter a valid password",Toast.LENGTH_LONG).show();
            return;
        }

        pDialog.setMessage("Login in...");
        pDialog.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        pDialog.dismiss();
                        /*
                        If the user signs in successfully
                        we direct them to the forum activity
                         */
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(),
                                    UserAccountActivity.class));
                        }

                        else if(!task.isSuccessful())
                        {


                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        if (v == btnLogin) {
            userSignIn();
        }

    }

}



