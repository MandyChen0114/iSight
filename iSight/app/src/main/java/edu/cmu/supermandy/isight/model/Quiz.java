package edu.cmu.supermandy.isight.model;

/**
 * Created by Mandy on 4/11/16.
 */
public class Quiz {
    String question;
    String answer;

    public Quiz(String question, String answer) {
        this.question = question;
        this.answer=answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
