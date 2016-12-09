/*
 * Created by Mohamed Bushra on 09/12/16 12:03
 * Copyright (c) 2016. All rights reserved.
 *
 * Last Modified 09/12/16 12:03.
 */

package uk.ac.brunel.tunel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.*;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

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
            public void onClick(View view)
            {
                /* Setting a new intent which takes the user from the current screen
                to the next (Main --> Sign in screen)
                 */
                Intent SignInIntent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(SignInIntent);
            }

        });
    }


}
