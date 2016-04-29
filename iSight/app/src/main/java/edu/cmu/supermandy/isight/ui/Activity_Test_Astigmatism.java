package edu.cmu.supermandy.isight.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Mandy on 4/28/16.
 */
public class Activity_Test_Astigmatism extends Activity {
    RadioGroup choicegroup;
    Button astigmatism_res_btn;
    TextView testResult_astigmatism;
String testresult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_astigmatism);
        choicegroup=(RadioGroup)findViewById(R.id.astigmatism_choice_group);
        astigmatism_res_btn=(Button) findViewById(R.id.astigmatism_res_btn);
        astigmatism_res_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = choicegroup.getCheckedRadioButtonId();
                setContentView(R.layout.activity_test_astigmatism_result);
                testResult_astigmatism=(TextView)findViewById(R.id.testResult_astigmatism);
                 if(selectedId==R.id.astigmatism_choice_1){
                    testresult="CONGRATULATIONS, you are not at risk for astigmatism!";
                }else{
                    testresult="UNFORTUNATELY, you are at risk for astigmatism! Please visit your eye doctor ASAP!";
                }
                    testResult_astigmatism.setText(testresult);
            }
        });
    }
}
