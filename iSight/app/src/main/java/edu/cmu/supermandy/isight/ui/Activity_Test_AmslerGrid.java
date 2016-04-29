package edu.cmu.supermandy.isight.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by Mandy on 4/28/16.
 */
public class Activity_Test_AmslerGrid extends Activity{
    CheckBox amsler_grid_check4;
    Button resultbtn;
    TextView testResult_amslerGrid;
    String testResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_amsler_grid);

        amsler_grid_check4=(CheckBox)findViewById(R.id.amsler_grid_check4);
        resultbtn=(Button)findViewById(R.id.amsler_grid_res_btn);

        resultbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_test_amsler_grid_result);
                testResult_amslerGrid=(TextView)findViewById(R.id.testResult_amslerGrid);
                if(amsler_grid_check4.isChecked()){
                    testResult="CONGRATULATIONS, you are not at risk for macular degeneration or other eye diseases related to central visual field!";
                }else{
                    testResult="UNFORTUNATELY, you are at risk for macular degeneration or other eye diseases related to central visual field! Please visit your eye doctor ASAP.";
                }
                testResult_amslerGrid.setText(testResult);
            }
        });
    }
}
