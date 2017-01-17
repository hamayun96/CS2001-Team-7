/*
 * Created by Mohamed Bushra on 17/01/17 12:59
 * Copyright (c) 2017. All rights reserved.
 *
 * Last Modified 17/01/17 10:47.
 */

package uk.ac.brunel.tunel.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import uk.ac.brunel.tunel.R;

/**
 * Created by Mohamed Bushra on 05/12/2016.
 */

public class SplashScreenActivity extends Activity {


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread myThread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    // The splash screen will show the Logo for 2 seconds
                    sleep(2000);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();

                } catch (InterruptedException e) {

                    e.printStackTrace();

                }

            }

        };

        myThread.start();

    }

}
