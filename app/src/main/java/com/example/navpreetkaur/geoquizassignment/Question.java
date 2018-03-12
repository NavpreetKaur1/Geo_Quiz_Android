package com.example.navpreetkaur.geoquizassignment;

/**
 * Created by Navpreet kaur on 06/02/2018.
 */

public class Question {
    private String query;
    private boolean answer;

    public Question(String query, boolean answer) {
        this.query = query;
        this.answer = answer;
    }

    public boolean getAnswer() {

        return answer;
    }

    public String getQuery() {
        return query;
    }
    public void setQuery(String query) {
        this.query = query;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
