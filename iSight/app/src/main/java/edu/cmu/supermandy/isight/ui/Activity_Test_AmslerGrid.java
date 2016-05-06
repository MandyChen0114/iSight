package edu.cmu.supermandy.isight.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.cmu.supermandy.isight.model.Record;
import edu.cmu.supermandy.isight.util.DBDAO;
import edu.cmu.supermandy.isight.ws.remote.RecordRequest;

/**
 * Created by Mandy on 4/28/16.
 */
public class Activity_Test_AmslerGrid extends Activity{
    CheckBox amsler_grid_check4;
    Button resultbtn;
    TextView testResult_amslerGrid;
    String testResult;
    String result;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_amsler_grid);

        amsler_grid_check4=(CheckBox)findViewById(R.id.amsler_grid_check4);
        resultbtn=(Button)findViewById(R.id.amsler_grid_res_btn);

        id = Integer.valueOf(this.getIntent().getStringExtra("Id"));
        final DBDAO dbdao = new DBDAO(this);
        final RecordRequest rq = new RecordRequest();
        resultbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_test_amsler_grid_result);
                testResult_amslerGrid=(TextView)findViewById(R.id.testResult_amslerGrid);
                if(amsler_grid_check4.isChecked()){
                    testResult="CONGRATULATIONS, you are not at risk for macular degeneration or other eye diseases related to central visual field!";
                    result="Result: YES";
                }else{
                    testResult="UNFORTUNATELY, you are at risk for macular degeneration or other eye diseases related to central visual field! Please visit your eye doctor ASAP.";
                    result="Result: NO";
                }
                testResult_amslerGrid.setText(testResult);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTimeStamp = dateFormat.format(new Date());
                Record record=new Record(id,3,currentTimeStamp,result);
                dbdao.insertRecord(record);
                rq.insertRecord(record);
            }
        });
    }
}
