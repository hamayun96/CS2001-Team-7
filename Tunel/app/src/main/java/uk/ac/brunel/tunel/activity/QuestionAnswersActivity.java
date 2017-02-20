
package uk.ac.brunel.tunel.activity;

/**
 * Created by falehalrashidi on 20/02/2017.
 */

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import uk.ac.brunel.tunel.R;

public class QuestionAnswersActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseUser mUser;
    private QuestionListAdapter mQuestionListAdapter;

    private RecyclerView mRecyclerView;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answers);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        fab = (FloatingActionButton) findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Intent(QuestionAnswersActivity.this, QuestionAnswersActivity.class); // todo newQUESTIONS
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        mQuestionListAdapter = new QuestionListAdapter();
        mRecyclerView.setAdapter(mQuestionListAdapter);

        // Set up question table listener
        mDatabase.child("questions").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Question question = dataSnapshot.getValue(Question.class);
                mQuestionListAdapter.addQuestion(question);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Question question = dataSnapshot.getValue(Question.class);
                mQuestionListAdapter.removeQuestion(question);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                // We don't need this
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                // we don't need this
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(QuestionAnswersActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
