package edu.cmu.supermandy.isight.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.cmu.supermandy.isight.model.TestColorBlind;


/**
 * Created by Mandy on 4/4/16.
 */
public class Activity_Test_ColorBlind extends Activity{
    ImageView imageView;
    List<TestColorBlind> list=new ArrayList<TestColorBlind>();
    Button showResultbtn;
    Button nextbtn;
    TextView testInstruction;

    int imgid;
    int res_imgid;
    String instruction;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_colorblind);

        imageView= (ImageView) findViewById(R.id.testimage);
        showResultbtn=(Button) findViewById(R.id.showResultbtn);
        nextbtn=(Button) findViewById(R.id.nextbtn);
        testInstruction=(TextView) findViewById(R.id.testInstruction);

        loadTestImages();
        getTestImages();
        imageView.setImageResource(imgid);
        testInstruction.setText(instruction);
        showResultbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(res_imgid);
                testInstruction.setText(result);
            }
        });
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTestImages();
                imageView.setImageResource(imgid);
                testInstruction.setText(instruction);
            }
        });
    }

    private void getTestImages() {


        int size=list.size();
        Random rand = new Random();
        TestColorBlind  testimg = list.get(rand.nextInt(size));
        imgid=testimg.getTestImgId();
        res_imgid=testimg.getResImgId();
        result=testimg.getTestResult();
        instruction=testimg.getTestInstruction();
    }

    private void loadTestImages() {
        TestColorBlind t1= new TestColorBlind(R.drawable.plate1,R.drawable.plate1a,12);
        TestColorBlind t2= new TestColorBlind(R.drawable.plate2,R.drawable.plate2a,8,3,0);
        TestColorBlind t3= new TestColorBlind(R.drawable.plate3,R.drawable.plate3a,29,70,0);
        TestColorBlind t4= new TestColorBlind(R.drawable.plate4,R.drawable.plate4a,5,2,0);
        TestColorBlind t5= new TestColorBlind(R.drawable.plate5,R.drawable.plate5a,3,5,0);
        TestColorBlind t6= new TestColorBlind(R.drawable.plate8,R.drawable.plate8a,6,0);
        TestColorBlind t7= new TestColorBlind(R.drawable.plate14,R.drawable.plate14,0,5);
        TestColorBlind t8= new TestColorBlind(R.drawable.plate15,R.drawable.plate15,0,45);
        TestColorBlind t9= new TestColorBlind(R.drawable.plate16,R.drawable.plate16a,26,6,2,2,6);
        TestColorBlind t10= new TestColorBlind(R.drawable.plate17,R.drawable.plate17a,42,2,4,4,2);
        TestColorBlind t11= new TestColorBlind(R.drawable.plate18,R.drawable.plate18a,"plate18");
        TestColorBlind t12= new TestColorBlind(R.drawable.plate19,R.drawable.plate19a,"plate19");
        TestColorBlind t13= new TestColorBlind(R.drawable.plate20,R.drawable.plate20a,"plate20");
        TestColorBlind t14= new TestColorBlind(R.drawable.plate22,R.drawable.plate22a,"plate22");
        TestColorBlind t15= new TestColorBlind(R.drawable.plate23,R.drawable.plate23a,"plate23");
        TestColorBlind t16= new TestColorBlind(R.drawable.plate24,R.drawable.plate24a,"plate24");
        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);
        list.add(t6);
        list.add(t7);
        list.add(t8);
        list.add(t9);
        list.add(t10);
        list.add(t11);
        list.add(t12);
        list.add(t13);
        list.add(t14);
        list.add(t15);
        list.add(t16);
    }


}
