package edu.cmu.supermandy.isight.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.cmu.supermandy.isight.model.Test;
import edu.cmu.supermandy.isight.model.TestAmslerGrid;
import edu.cmu.supermandy.isight.model.TestColorBlind;
import edu.cmu.supermandy.isight.model.TestMotionAcuity;
import edu.cmu.supermandy.isight.model.TestPD;
import edu.cmu.supermandy.isight.model.TestPresbyopic;
import edu.cmu.supermandy.isight.model.TestVisualAcuity;
import edu.cmu.supermandy.isight.util.DBDAO;

/**
 * Created by Mandy on 4/4/16.
 */
public class Fragment_Test extends Fragment {
    public static ArrayList<Test> testList;
    private TestListAdapter testListAdapter;
    private ListView testListView;

    public Fragment_Test() {
        testList = new ArrayList<Test>();
        Test t1 = new TestVisualAcuity();
        t1.setName("Visual Acuity");
        t1.setIntroduction("To test the clarity or sharpness of vistion related to myopia and hyperopia by Snellen eye chart");
        testList.add(t1);

        Test t2 = new TestPresbyopic();
        t2.setName("Presbyopic");
        t2.setIntroduction("To measure your ability to read and see objects close up by near vision test");
        testList.add(t2);

        Test t3 = new TestColorBlind();
        t3.setName("Color Blindness");
        t3.setIntroduction("To test the ability of seeing color, or preceiving color differences by Ishihara plate test");
        testList.add(t3);

        Test t4 = new TestAmslerGrid();
        t4.setName("Amsler Grid");
        t4.setIntroduction("To test macular degeneration, glaucoma or other problems related to central visual field");
        testList.add(t4);

        Test t5 = new TestMotionAcuity();
        t5.setName("Motion Acuity");
        t5.setIntroduction("To test gaze stability during sinusoidal, examiner mediated head rotation");
        testList.add(t5);

        Test t6 = new TestAmslerGrid();
        t6.setName("Astigmatism");
        t6.setIntroduction("To test if there is an imperfection in the curvature of cornea or in the shape of the eye's lens");
        testList.add(t6);

        Test t7 = new TestPD();
        t7.setName("Pupillary Distance");
        t7.setIntroduction("To test pupillary Distance by face recognition");
        testList.add(t7);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        testListAdapter = new TestListAdapter(testList);
        testListView = (ListView) getActivity().findViewById(R.id.testlist);
        testListView.setAdapter(testListAdapter);
        testListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Test t = testListAdapter.getItem(position);
                Intent intent;
                switch (position)
                {
                    case 0:
                        intent=new Intent(getActivity(),Activity_Test_VisualAcuity.class);
                        intent.putExtra("Id", getActivity().getIntent().getStringExtra("Id"));
                        startActivity(intent);
                        break;
                    case 1:
                        intent=new Intent(getActivity(),Activity_Test_Presbyopic.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), Activity_Test_ColorBlind.class);
                        intent.putExtra("Id", getActivity().getIntent().getStringExtra("Id"));
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(getActivity(), Activity_Test_AmslerGrid.class);
                        intent.putExtra("Id", getActivity().getIntent().getStringExtra("Id"));
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(getActivity(), Activity_Test_MotionAcuity.class);
                        intent.putExtra("Id", getActivity().getIntent().getStringExtra("Id"));
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(getActivity(), Activity_Test_Astigmatism.class);
                        intent.putExtra("Id", getActivity().getIntent().getStringExtra("Id"));
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(getActivity(), Activity_Test_PD.class);
                        intent.putExtra("Id", getActivity().getIntent().getStringExtra("Id"));
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private class TestListAdapter extends ArrayAdapter<Test> {
        public TestListAdapter(ArrayList<Test> testList) {
            super(getActivity(), android.R.layout.simple_list_item_1, testList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_test_item, null);
            }
            // configure the view for this Song
            Test t = getItem(position);
            TextView testNameTextView = (TextView) convertView.findViewById(R.id.test_Name);
            TextView testIntroTextView = (TextView) convertView.findViewById(R.id.test_Introduction);
            testNameTextView.setText(t.getName());
            testIntroTextView.setText(t.getIntroduction());
            return convertView;
        }
    }
}
