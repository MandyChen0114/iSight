package edu.cmu.supermandy.isight.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import edu.cmu.supermandy.isight.model.Test;

/**
 * Created by Mandy on 4/4/16.
 */
public class Fragment_History extends Fragment {
    private Spinner testSpinner;
    private String[] testItems=new String[]{"Visual Acuity","Presbyopic","Color Blindness","Amsler Grid","Motion Acuity","Astigmatism","Pupillary Distance"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        testSpinner=(Spinner)getActivity().findViewById(R.id.testSpinner);
        setTest();
    }

    private void setTest() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), R.layout.support_simple_spinner_dropdown_item, testItems);
        testSpinner.setAdapter(adapter);
    }
}
