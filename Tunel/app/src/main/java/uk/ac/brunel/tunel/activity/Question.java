package uk.ac.brunel.tunel.activity;

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

    public Question(String question, String userID, long timeCreated) {
        this.question = question;
        this.userID = userID;
        dateCreated = timeCreated;
        dateModified = timeCreated;
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
