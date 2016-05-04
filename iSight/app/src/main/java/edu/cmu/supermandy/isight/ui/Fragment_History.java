package edu.cmu.supermandy.isight.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import edu.cmu.supermandy.isight.model.Record;
import edu.cmu.supermandy.isight.util.DBDAO;

/**
 * Created by Mandy on 4/4/16.
 */
public class Fragment_History extends Fragment {
    private Spinner testSpinner;
    private String[] testItems = new String[]{"Visual Acuity", "Presbyopic", "Amsler Grid", "Motion Acuity", "Astigmatism", "Pupillary Distance"};
    TextView historyTextView;
    String userid;
    String startTime;

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

        testSpinner = (Spinner) getActivity().findViewById(R.id.testSpinner);
        historyTextView = (TextView) getActivity().findViewById(R.id.historyTextView);


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), R.layout.support_simple_spinner_dropdown_item, testItems);
        testSpinner.setAdapter(adapter);
        testSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("-------------------->"+dbdao.checkDataExist("RecordTable","UserId",userid));

                    loadData(dbdao);

                if (position >= 2) position += 1;

                String testResult = gethistory(dbdao, position, columnindex);
                historyTextView.setText(testResult);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                historyTextView.setText("Go to test and record your results now!");
            }
        });
    }

    public String gethistory(DBDAO dbdao, int testid, String[] columnindex) {
        String testResult = "";
        List<String[]> rowlist = dbdao.getRowData("RecordTable", "UserId", userid, "TestId", testid + "", columnindex);

        for (String[] history : rowlist) {
            String timestamp = history[2];
            String result = history[3];
            testResult += "[" + timestamp + "] " + result + "\n";
        }
        return testResult;
    }

    public void loadData(DBDAO dbdao) {
        for (int i = 0; i <=6; i++) {
            String res1 = "";
            String res2 = "";
            String res3 = "";
            if (i == 0 || i == 1 || i == 4) {
                res1 = "Level: 1.0";
                res2 = "Level: 3.0";
                res3 = "Level: 2.0";
            } else if (i == 2) {
                continue;
            } else if (i == 3 || i == 5) {
                res1 = "Result: YES";
                res2 = "Result: NO";
                res3 = "Result: YES";
            } else if (i == 6) {
                res1 = "\nPupil Distance/ Face = 43.65%";
                res2 = "\nPupil Distance/ Face = 43.94%";
                res3 = "\nPupil Distance/ Face = 43.12%";
            }

            Record r1 = new Record(Integer.parseInt(userid), i, "2016-02-28 06:20:34", res1);
            Record r2 = new Record(Integer.parseInt(userid), i, "2016-01-14 07:33:12", res2);
            Record r3 = new Record(Integer.parseInt(userid), i, "2015-10-16 10:11:23", res3);
            dbdao.insertRecord(r1);
            dbdao.insertRecord(r2);
            dbdao.insertRecord(r3);
        }
    }
}
