package uk.ac.brunel.tunel.activity;

import java.util.Date;

/**
 * Created by falehalrashidi on 20/02/2017.
 */

public class Question {

    public String question;
    public String userID;
    public long dateCreated;
    public long dateModified;

    // Firebase requires an empty constructor
    public Question() {
    }

    public Question(String question, String userID) {
        this.question = question;
        this.userID = userID;
        dateCreated =  new Date().getTime();
        dateModified = dateCreated;
    }

    public String getQuestion() {
        return question;
    }

    public String getUserID() {
        return userID;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public long getDateModified() {
        return dateModified;
    }
}
