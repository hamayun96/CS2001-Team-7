package uk.ac.brunel.tunel.activity;

/**
 * Created by falehalrashidi on 20/02/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import uk.ac.brunel.tunel.R;

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.QuestionViewHolder> {

    private ArrayList<Question> questions;

    public QuestionListAdapter() {
        questions = new ArrayList<>();
    }

    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_view, parent, false);
        return new QuestionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final QuestionViewHolder holder, final int position) {
        if (position > -1 && position < questions.size()) {
            Question question = questions.get(position);
            holder.setQuestion(question.getQuestion());
            holder.setUserID(question.getUserID());
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(holder.layout.getContext(), "Question " + position + " selected!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public void addQuestion(Question question) {
        questions.add(question);
        notifyDataSetChanged();
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
        notifyDataSetChanged();
    }

    /**
     * Created by falehalrashidi on 20/02/2017.
     *
     * We have to use a custom ViewHolder so that we can use the Android RecyclerView /
     * ViewHolder pattern. This class holds and re-draws the question layout.
     */
    public static class QuestionViewHolder extends RecyclerView.ViewHolder {

        public View layout;
        private TextView textQuestion;
        private TextView textUserID;

        public QuestionViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            textQuestion = (TextView) itemView.findViewById(R.id.text_question);
            textUserID = (TextView) itemView.findViewById(R.id.text_user_id);
        }

        public void setQuestion(String question) {
            textQuestion.setText(question + "?");
        }

        public void setUserID(String userID) {
            textUserID.setText("User: " +userID);
        }

    }
}
