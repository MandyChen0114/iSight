package edu.cmu.supermandy.isight.model;

/**
 * Created by Mandy on 4/11/16.
 */
public class Quiz {
    String question;
    int correctchoice;
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

    public int getCorrectchoice() {
        if(answer.equals("true")){
            this.correctchoice=2131492991;
        }else{
            this.correctchoice=2131492992;
        }
        return correctchoice;
    }

    public void setCorrectchoice(int correctchoice) {
        this.correctchoice = correctchoice;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
