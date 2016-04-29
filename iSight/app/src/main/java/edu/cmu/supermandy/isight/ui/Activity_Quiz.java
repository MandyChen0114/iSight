package edu.cmu.supermandy.isight.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import edu.cmu.supermandy.isight.model.Quiz;
import edu.cmu.supermandy.isight.util.DBDAO;

/**
 * Created by Mandy on 4/27/16.
 */
public class Activity_Quiz extends Activity {
    TextView indexTextView;
    TextView questionTextView;
    TextView scoreTextView;
    TextView correctTextView;
    TextView wrongTextView;
    TextView wronglistTextView;
    Button nextbtn;
    RadioGroup choicegroup;
    View resultView;
    LinearLayout resultLayout;
    List<Quiz> quizlist=new ArrayList<Quiz>();
    Set<Integer> quizSet=new HashSet<Integer>();
    List<Quiz> wronglist=new ArrayList<Quiz>();

    Quiz current_quiz;

    static final int quiz_size=2;
    int index;
    int correct;
    int wrong;
    String question;
    int correctchoice;
    int score;
    int quiz_all_size;
    String wrongliststr="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        indexTextView= (TextView) findViewById(R.id.quiz_question_index);
        questionTextView=(TextView) findViewById(R.id.quiz_question);
        choicegroup=(RadioGroup)findViewById(R.id.quiz_choice_group);

        final DBDAO quizDAO=new DBDAO(this);
        int quiz_all_size=quizDAO.count("QuizTable");
        if(quiz_all_size==0) {
            loadQuestion(quizDAO);
        }
        getQuestionSet(quizDAO);
        getQuestion();

        nextbtn=(Button) findViewById(R.id.quiz_next_button);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId=choicegroup.getCheckedRadioButtonId();
                if(selectedId==correctchoice){
                    correct++;
                }else{
                    wrong++;
                    wronglist.add(current_quiz);
                }
                if(index<quiz_size) {
                    getQuestion();
                }else{
                    setContentView(R.layout.activity_quiz_result);
                    resultLayout=(LinearLayout)findViewById(R.id.quiz_result_layout);
                    scoreTextView=(TextView)findViewById(R.id.quiz_result6);
                    correctTextView=(TextView)findViewById(R.id.quiz_result2);
                    wrongTextView=(TextView)findViewById(R.id.quiz_result4);

                    score=correct*10;
                    scoreTextView.setText(String.valueOf(score));
                    correctTextView.setText(String.valueOf(correct));
                    wrongTextView.setText(String.valueOf(wrong));

                    if(wrong==0){
                        resultView=getLayoutInflater().inflate(R.layout.activity_quiz_pass, resultLayout,false);
                        resultLayout.addView(resultView);
                    }else{
                        resultView=getLayoutInflater().inflate(R.layout.activity_quiz_fail, resultLayout,false);
                        resultLayout.addView(resultView);
                        wronglistTextView=(TextView)findViewById(R.id.quiz_result_fail_textview);
                        for(int i=0;i<wronglist.size();i++){
                            String wrong_question=wronglist.get(i).getQuestion();
                            String wrong_answer=wronglist.get(i).getAnswer();
                            wrongliststr+="\""+wrong_question+"\" should be: "+wrong_answer.toUpperCase()+"\n\n";
                        }
                        wronglistTextView.setText(wrongliststr);
                    }


                }
            }
        });


    }

    private void getQuestion() {
        current_quiz=quizlist.get(index);
        question=current_quiz.getQuestion();
        correctchoice=current_quiz.getCorrectchoice();
        index+=1;
        indexTextView.setText(String.valueOf(index));
        questionTextView.setText(". "+question+":");

        if(index==quiz_size){
            nextbtn.setText("Get score!");
        }
    }

    private void getQuestionSet(DBDAO quizDAO) {
        Random rand = new Random();
        String[] columnindex=new String[]{"Question","Answer"};
        quiz_all_size=quizDAO.count("QuizTable");
        for(int i=0;i<quiz_all_size;i++){
            int id=rand.nextInt(quiz_all_size)+1;
            if(quizSet.add(id)) {
                String[] quizinfo=quizDAO.getRowData("QuizTable","Id",id+" ",columnindex);
                String question=quizinfo[0];
                String answer=quizinfo[1];
                Quiz q=new Quiz(question,answer);
                quizlist.add(q);
            }else{
                i--;
            }
            if(quizSet.size()==quiz_size){
                break;
            }
        }
    }


    private void loadQuestion(DBDAO quizDAO) {

        Quiz q1=new Quiz("Rubbing your eye is a good way to remove foreign particles","false");
        Quiz q2=new Quiz("If a chemical gets splashed in your eyes,flush your eyes for at least 20 minutes","true");
        quizDAO.insertQuiz(q1);
        quizDAO.insertQuiz(q2);
    }
}
