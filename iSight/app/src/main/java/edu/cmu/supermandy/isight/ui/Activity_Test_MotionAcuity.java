package edu.cmu.supermandy.isight.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Activity_Test_MotionAcuity extends AppCompatActivity {

    private TextView flyingWordTextView;
    private EditText questionForm;
    private Button submitButton;
    private TextView hintTextView;
    private Button startButton;

    private int level = 0;
    private int letterCount = 10;
    private int duration = 1000;
    private ArrayList<String> letters;
    private int passedLetter = 0;
    private int answer = 0;
    private String queryLetter;
    private Random random = new Random();

    private static final int[] fontSizes = {256, 128, 64, 32, 16, 8, 4};
    private static final String alphabet = "QWERTYUIOPASDFGHJKLZXCVBNM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_motionacuity);

        flyingWordTextView = (TextView) findViewById(R.id.flyingWord);
        questionForm = (EditText) findViewById(R.id.questionForm);
        submitButton = (Button) findViewById(R.id.submitbtn);
        hintTextView = (TextView) findViewById(R.id.hintText);
        startButton = (Button) findViewById(R.id.startButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userAnswer = Integer.parseInt(questionForm.getText().toString());
                if (Math.abs(userAnswer - answer) <= 1) {
                    if (level < fontSizes.length - 1) {
                        Toast.makeText(getApplicationContext(), "Congratulations! You are going to the next level.", Toast.LENGTH_LONG).show();
                        level = level + 1;
                        letterCount = letterCount * 2;
                        duration = duration / 2;

                        init();
                    } else {
                        saveResult();
                    }
                } else {
                    saveResult();
                }

            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.setVisibility(View.INVISIBLE);
                loopAnimator();
            }
        });

        init();
    }

    public void init() {
        answer = 0;
        passedLetter = 0;
        letters = new ArrayList<String>(5);
        for (int i = 0; i < 5; i++) {
            int id = random.nextInt(alphabet.length());
            letters.add(Character.toString(alphabet.charAt(id)));
        }
        queryLetter = letters.get(0); // use first letter as query letter

        // Update interface
        hideQueryForm();
        questionForm.setText("");
        String query = "Count the occurrence of " + queryLetter;
        hintTextView.setText(query);
        questionForm.setHint(query);
    }

    public void loopAnimator() {
        if (passedLetter >= letterCount) {
            showQueryForm();
        } else {
            int id = random.nextInt(letters.size());
            String letter = letters.get(id);

            passedLetter = passedLetter + 1;
            if (letter.equals(queryLetter)) {
                answer = answer + 1;
            }

            ObjectAnimator animator = makeAnimator(letter, fontSizes[level], duration);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    loopAnimator();
                }
            });
        }
    }

    public ObjectAnimator makeAnimator(String letter, float size, long duration) {
        flyingWordTextView.setText(letter);
        flyingWordTextView.setTextSize(size);
        flyingWordTextView.setX(0);
        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        ObjectAnimator moveAnim = ObjectAnimator.ofFloat(flyingWordTextView, "X", screenSize.x);
        moveAnim.setDuration(duration);
        moveAnim.setInterpolator(new LinearInterpolator());
        moveAnim.start();
        return moveAnim;
    }

    public void showQueryForm() {
        startButton.setVisibility(View.GONE);
        hintTextView.setVisibility(View.GONE);
        questionForm.setVisibility(View.VISIBLE);
        submitButton.setVisibility(View.VISIBLE);
    }

    public void hideQueryForm() {
        startButton.setVisibility(View.VISIBLE);
        hintTextView.setVisibility(View.VISIBLE);
        questionForm.setVisibility(View.GONE);
        submitButton.setVisibility(View.GONE);
    }

    public void saveResult() {
        Toast.makeText(getApplicationContext(), "Your max level: " + Integer.toString(level), Toast.LENGTH_LONG).show();
    }

}
