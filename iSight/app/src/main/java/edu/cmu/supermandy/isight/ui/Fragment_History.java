package edu.cmu.supermandy.isight.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.cmu.supermandy.isight.model.Record;
import edu.cmu.supermandy.isight.util.DBDAO;

/**
 * Created by Mandy on 4/4/16.
 */
public class Fragment_History extends Fragment {
    private Spinner testSpinner;
    private String[] testItems = new String[]{"Visual Acuity", "Presbyopic", "Color Blindness", "Amsler Grid", "Motion Acuity", "Astigmatism", "Pupillary Distance"};
    TextView historyTextView;
    String userid;
    Button during1_button;
    Button during2_button;
    Button during3_button;
    Button during4_button;
    long startTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final DBDAO dbdao = new DBDAO(this.getActivity());
        final String[] columnindex = new String[]{"UserId", "TestId", "Timestamp", "Result"};

        userid = this.getActivity().getIntent().getStringExtra("Id");
        loadData(dbdao);
        testSpinner = (Spinner) getActivity().findViewById(R.id.testSpinner);
        historyTextView = (TextView) getActivity().findViewById(R.id.historyTextView);
        during1_button = (Button) getActivity().findViewById(R.id.during1_button);
        during2_button = (Button) getActivity().findViewById(R.id.during2_button);
        during3_button = (Button) getActivity().findViewById(R.id.during3_button);
        during4_button = (Button) getActivity().findViewById(R.id.during4_button);



        during1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startTime=(new Date().getTime())/1000-2678400;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
System.out.println(dateFormat.format(startTime));
            }
        });
        during2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime=(new Date().getTime())/1000-2678400*3;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println(dateFormat.format(startTime));
            }
        });
        during3_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime=(new Date().getTime())/1000-2678400*6;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println(dateFormat.format(startTime));
            }
        });
        during4_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime=-1;
            }
        });
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), R.layout.support_simple_spinner_dropdown_item, testItems);
        testSpinner.setAdapter(adapter);
        testSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==5) {
                    String testResult = gethistory(dbdao, position, startTime, columnindex);
                    historyTextView.setText(testResult);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                historyTextView.setText("Go to test and record your results now!");
            }
        });
    }

    public String gethistory(DBDAO dbdao, int testid, long startTime, String[] columnindex) {
        String testResult = "";
        List<String[]> rowlist = new ArrayList<String[]>();
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (startTime == -1) {

            rowlist = dbdao.getRowData("RecordTable", "UserId", userid, "TestId", testid + "", columnindex);
        } else {
            rowlist = dbdao.getTimeRowData("RecordTable", "UserId", userid, "TestId", testid + "", "Timestamp", (int)startTime, columnindex);
        }
        for (String[] history : rowlist) {
            String timestamp = dateFormat.format(new Date(Long.parseLong(history[2])));
            String result = history[3];
            testResult += "[" + timestamp + "] " + result + "\n";
        }
        return testResult;
    }

    public void loadData(DBDAO dbdao){

            Record r1=new Record(Integer.parseInt(userid),5,1459577678,"Result: YES");
            Record r2=new Record(Integer.parseInt(userid),5,1454220904,"Result: YES");
            Record r3=new Record(Integer.parseInt(userid),5,1446185758,"Result: YES");
            dbdao.insertRecord(r1);
            dbdao.insertRecord(r2);
            dbdao.insertRecord(r3);

    }
}
