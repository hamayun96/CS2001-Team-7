/*
 * Created by Mohamed Bushra on 20/02/17 14:40
 * Copyright (c) 2017. All rights reserved.
 *
 * Last Modified 20/02/17 14:36.
 */

package uk.ac.brunel.tunel.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import uk.ac.brunel.tunel.R;

public class ResetPassActivity extends AppCompatActivity {

    private FirebaseAuth authRef;
    private FirebaseUser userRef;
    private FirebaseAuth.AuthStateListener mAuthLis;
    private String email;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

        authRef = FirebaseAuth.getInstance();
        email = authRef.getCurrentUser().getEmail();
        authRef.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "An email has been sent.");
                        }
                    }
                });

    }




}

