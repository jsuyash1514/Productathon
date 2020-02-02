package com.example.android.gharkikheti.Modal;

public class QuestionModel {
    String question, postedBy, timestamp;

    public QuestionModel(String question, String postedBy, String timestamp) {
        this.question = question;
        this.postedBy = postedBy;
        this.timestamp = timestamp;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
