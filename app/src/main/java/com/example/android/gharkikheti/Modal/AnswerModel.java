package com.example.android.gharkikheti.Modal;

public class AnswerModel {
    String name, timestamp, answer;

    public AnswerModel(String name, String timestamp, String answer) {
        this.name = name;
        this.timestamp = timestamp;
        this.answer = answer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
