package uk.ac.brunel.tunel.activity;

/**
 * Created by falehalrashidi on 20/02/2017.
 */

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import uk.ac.brunel.tunel.R;

public class AskQuestionActivity extends Activity implements View.OnClickListener {

    private Button buttonConfirm;
    private Button buttonCancel;
    private EditText editTextQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);

        buttonConfirm = (Button) findViewById(R.id.button_confirm);
        buttonConfirm.setOnClickListener(this);
        buttonCancel = (Button) findViewById(R.id.button_cancel);
        buttonCancel.setOnClickListener(this);

        editTextQuestion = (EditText) findViewById(R.id.edittext_question);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.button_cancel) {
            finish();
        } else if (id == R.id.button_confirm) {
            if (editTextQuestion != null && !editTextQuestion.getText().toString().isEmpty()) {
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
                mDatabase.child("questions").push().setValue(new Question(editTextQuestion.getText().toString(), mUser.getEmail()));
                Toast.makeText(this, "Question asked successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
