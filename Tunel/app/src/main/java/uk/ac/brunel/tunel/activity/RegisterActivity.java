/*
 * Created by Mohamed Bushra on 08/02/17 17:02
 * Copyright (c) 2017. All rights reserved.
 *
 * Last Modified 08/02/17 16:21.
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


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnSignup;
    private EditText userEmail, userPassword, userPasswordVal;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userEmail = (EditText) findViewById(R.id.reg_email);
        userPassword = (EditText) findViewById(R.id.reg_pass);
        userPasswordVal = (EditText) findViewById(R.id.reg_pass_val);
        btnSignup = (Button) findViewById(R.id.reg_button);

        mAuth = FirebaseAuth.getInstance();
        btnSignup.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);

        //Check if user is already logged in
        if(mAuth.getCurrentUser() != null){
            //Close current page and open forum activity
            finish();
            startActivity(new Intent(getApplicationContext(), UserAccountActivity.class));
        }

    }

    private void registerUser() {
        String email = userEmail.getText().toString().trim();
        String password = userPassword.getText().toString().trim();
        String password_val = userPasswordVal.getText().toString().trim();

        //Check if the fields email and/or password are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter a email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter a password",Toast.LENGTH_LONG).show();
            return;
        }

        //Validate the password entered by the user
        if(!password_val.equals(password))
        {
            Toast.makeText(this,"Passwords don't match",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        /*
                        Checking if user has successfully registered
                         */
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,"Successfully registered",
                                    Toast.LENGTH_LONG).show();
                            // Launch login activity
                            Intent intent = new Intent(
                                    RegisterActivity.this,
                                    SignInActivity.class);
                            startActivity(intent);
                            finish();
                        }else{

                            Toast.makeText(RegisterActivity.this,"Registration failed",
                                    Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    @Override
    public void onClick(View v) {
        if(v == btnSignup)
        {
            registerUser();
        }

    }




}
