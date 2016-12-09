/*
 * Created by Mohamed Bushra on 07/12/16 22:09
 * Copyright (c) 2016. All rights reserved.
 *
 *  Last modified 07/12/16 21:21
 */

package uk.ac.brunel.tunel;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        final EditText email = (EditText) findViewById(R.id.etUsername);
        final EditText password = (EditText) findViewById(R.id.etPassword);
        Button login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!validateEmail(email.getText().toString())) {
                    email.setError("Invalid Email");
                    email.requestFocus();
                } else if (!validatePassword(password.getText().toString())) {
                    password.setError("Invalid Password");
                    password.requestFocus();
                } else {
                    Toast.makeText(SignInActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    /*For now this returns true if password is valid
    and false if invalid, still need to connect it with
    the server for Database validation
    same goes for ValidateEmail
     */
    protected boolean validatePassword(String password) {

        if(password!=null && password.length()>8)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //
    protected boolean validateEmail(String email)
    {
        if(email!=null)
        {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
        else
        {
            return false;
        }
    }


}

