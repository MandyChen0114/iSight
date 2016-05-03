package edu.cmu.supermandy.isight.ui;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import edu.cmu.supermandy.isight.model.Record;
import edu.cmu.supermandy.isight.util.DBDAO;

public class Activity_Test_VisualAcuity extends Activity {

    public static final char[] candidates = {'N', 'E', 'F', 'P', 'T', 'O', 'Z', 'L', 'D'};
    public static final float[] fontSizes = {60, 45, 35, 25, 16, 12, 6, 4, 2};
    public static final double[] scores = {1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5};
    public static final int MAX_RETRY_COUNT = 3;

    private final int SPEECH_RECOGNITION_CODE = 1;

    private TextView candidateText;
    private TextView hintText;
    private ImageButton btnMicrophone;
    private ImageButton btnRefresh;

    private int candidateIndex = 0;
    private int level = 0;
    private int retryCount = 0;
    private int id;
    private Random random = new Random();
    DBDAO dbdao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_visualacuity_intro);
        Button enterButton = (Button) findViewById(R.id.enterButton);
        id = Integer.valueOf(this.getIntent().getStringExtra("Id"));
        DBDAO dbdao = new DBDAO(this);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_test_visualacuity);

                candidateText = (TextView) findViewById(R.id.candidate);
                btnMicrophone = (ImageButton) findViewById(R.id.btn_mic);
                hintText = (TextView) findViewById(R.id.hint);

                btnMicrophone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startSpeechToText();
                    }
                });

                btnRefresh = (ImageButton) findViewById(R.id.btn_refresh);
                btnRefresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        retry();
                    }
                });
            }
        });
    }

    private void nextLevel() {
        level = level + 1;
        if (level >= scores.length) {
            level = scores.length - 1;
            saveResult();
        } else {
            candidateIndex = random.nextInt(candidates.length);
            retryCount = 0;
            updateCandidate();
        }
    }

    private void retry() {
        candidateIndex = random.nextInt(candidates.length);
        retryCount = retryCount + 1;
        if (retryCount >= MAX_RETRY_COUNT)
            saveResult();
        else
            updateCandidate();
    }

    private void updateCandidate() {
        candidateText.setText(Character.toString(candidates[candidateIndex]));
        candidateText.setTextSize(fontSizes[level]);
    }

    private void saveResult() {
        Toast.makeText(getApplicationContext(),
                "Your test score is: "+Double.toString(scores[level]), Toast.LENGTH_LONG).show();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTimeStamp = dateFormat.format(new Date());
        String result="Level: "+Double.toString(scores[level]);
        Record record=new Record(id,0,currentTimeStamp,result);
        dbdao.insertRecord(record);
    }

    /**
     * Start speech to text intent. This opens up Google Speech Recognition API dialog box to listen the speech input.
     * */
    private void startSpeechToText() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Speak something...");
        try {
            startActivityForResult(intent, SPEECH_RECOGNITION_CODE);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Speech recognition is not supported in this device.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Callback for speech recognition activity
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case SPEECH_RECOGNITION_CODE: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String text = result.get(0);
                    char letter = Character.toUpperCase(text.charAt(0));
                    if (letter == candidates[candidateIndex]) {
                        // success
                        hintText.setText("Correct! You said: " + text);
                        nextLevel();
                    } else {
                        // failed
                        hintText.setText("Wrong! You said: " + text);
                        retry();
                    }
                }
                break;
            }

        }
    }

}
