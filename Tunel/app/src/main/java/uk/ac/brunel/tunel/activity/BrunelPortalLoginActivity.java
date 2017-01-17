/*
 * Created by Mohamed Bushra on 17/01/17 12:59
 * Copyright (c) 2017. All rights reserved.
 *
 * Last Modified 17/01/17 12:58.
 */

package uk.ac.brunel.tunel.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import uk.ac.brunel.tunel.R;

public class BrunelPortalLoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brunel_portal_login);

        /*
        When user wants to register, we need to validate
        that they are a Brunel student, we are checking this using
        our dummy DB containing Brunel students
         */
        final EditText br_Username = (EditText) findViewById(R.id.br_Username);
        final EditText br_Password = (EditText) findViewById(R.id.br_Password);
        final Button br_login = (Button) findViewById(R.id.br_login);

        br_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!validateEmail(br_Username.getText().toString())) {
                    br_Username.setError("Invalid Email");
                    br_Username.requestFocus();
                } else if (!validatePassword(br_Password.getText().toString())) {
                    br_Password.setError("Invalid Password");
                    br_Password.requestFocus();
                } else {
                    Toast.makeText(BrunelPortalLoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    /*For now this returns true if password is valid
   and false if invalid, still need to connect it with
   the server for Database validation same goes for ValidateEmail
    */
    protected boolean validatePassword(String br_password)
    {

        return br_password != null && br_password.length() > 8;
    }

    //
    protected boolean validateEmail(String br_email)
    {
        return br_email!=null && android.util.Patterns.EMAIL_ADDRESS.matcher(br_email).matches();
        else
        {
            return false;
        }
    }
}
