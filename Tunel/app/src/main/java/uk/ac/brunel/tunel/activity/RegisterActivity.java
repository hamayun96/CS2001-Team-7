/*
 * Created by Mohamed Bushra on 17/01/17 12:59
 * Copyright (c) 2017. All rights reserved.
 *
 * Last Modified 17/01/17 12:58.
 */

package uk.ac.brunel.tunel.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import uk.ac.brunel.tunel.R;
import uk.ac.brunel.tunel.app.AppConfig;
import uk.ac.brunel.tunel.app.AppController;
import uk.ac.brunel.tunel.apphelper.SQLiteHandler;
import uk.ac.brunel.tunel.apphelper.SessionManager;


public class RegisterActivity extends Activity {

    private static final String TAG = RegisterActivity.class.getSimpleName();
    private EditText userFullName;
    private EditText userEmail;
    private EditText userPassword;
    private Spinner courseLevel;
    private EditText studentID;
    private ProgressDialog pDialog;
    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userFullName = (EditText) findViewById(R.id.fullName);
        courseLevel = (Spinner) findViewById(R.id.user_course_level);
        studentID = (EditText) findViewById(R.id.student_ID);
        userEmail = (EditText) findViewById(R.id.user_email);
        userPassword = (EditText) findViewById(R.id.user_pass);
        Button btnSignup = (Button) findViewById(R.id.singup_button);
        SessionManager session;

         /*
        We are displaying/loading the String Array content and displaying it
        in a simple spinner list
           */
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.course_level,android.R.layout.simple_spinner_item);

        // I am specifying to use a dropdown list to use when the list is shown to the user
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Here I am applying the adapter to my spinner
        courseLevel.setAdapter(adapter);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(getApplicationContext());

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(RegisterActivity.this,
                    ForumActivity.class);
            startActivity(intent);
            finish();
        }

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                    When the user clicks 'sign up' button we need to
                    get all the information that they entered
                 */
                String name = userFullName.getText().toString().trim();
                String email = userEmail.getText().toString().trim();
                String studentid = studentID.getText().toString().trim();
                String courselevel = courseLevel.getSelectedItem().toString().trim();
                String password = userPassword.getText().toString().trim();


                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() &&
                        !studentid.isEmpty() && !courselevel.isEmpty())
                {
                    registerUser(name, email, password, studentid, courselevel);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();
                }

            }
        });

    }


    /**
     * Function to store user in MySQL database will post params(tag, name,
     * email, password) to register url
     * */
    private void registerUser(final String name, final String email,
                              final String password, final String studentid, final String courselevel) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                int d = Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
                        String uid = jObj.getString("uid");

                        JSONObject user = jObj.getJSONObject("user");
                        String name = user.getString("name");
                        String email = user.getString("email");
                        String student_id = user.getString("student ID");
                        String course_level = user.getString("course level");

                        // Inserting row in users table
                        db.addUser(uid, name, email, student_id, course_level);

                        Toast.makeText(getApplicationContext(), "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();

                        // Launch login activity
                        Intent intent = new Intent(
                                RegisterActivity.this,
                                SignInActivity.class);
                        startActivity(intent);
                        finish();
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                params.put("student ID", studentid);
                params.put("course level", courselevel);


                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }



}
