/*
 * Created by Mohamed Bushra on 17/01/17 12:59
 * Copyright (c) 2017. All rights reserved.
 *
 * Last Modified 14/01/17 19:42.
 */

package uk.ac.brunel.tunel.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import uk.ac.brunel.tunel.R;

import static uk.ac.brunel.tunel.R.id.sign_up;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Assigning the 'Sign in' button to a click listener
        so it takes the user to the next screen
         */
        Button userSignIn = (Button) findViewById(R.id.sign_in);
        userSignIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /* Setting a new intent which takes the user from the current screen
                to the next (Main --> Sign in screen)
                 */
                Intent SignInIntent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(SignInIntent);
            }

        });

        Button userSignUp = (Button)findViewById(sign_up);
        userSignUp.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                /* Setting a new intent which takes the user from the current screen
                to the next (Sign in --> Sign up screen)
                 */
                Intent RegisterIntent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(RegisterIntent);

            }
        });
    }


}
