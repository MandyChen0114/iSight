package edu.cmu.supermandy.isight.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.cmu.supermandy.isight.model.Test;

/**
 * Created by Mandy on 4/4/16.
 */
public class Fragment_Setting extends Fragment {
    public static ArrayList<String> settingList;
    private SettingListAdapter settingListAdapter;
    private ListView settingListView;

    public Fragment_Setting() {
        settingList = new ArrayList<String>();
        settingList.add("Account Information");
        settingList.add("Change Password");
        settingList.add("Manage History");
        settingList.add("Test Standard");
        settingList.add("Share");
        settingList.add("Log out");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        settingListAdapter = new SettingListAdapter(settingList);
        settingListView = (ListView) getActivity().findViewById(R.id.settinglistView);
        settingListView.setAdapter(settingListAdapter);
        settingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = settingListAdapter.getItem(position);
                if(position==5){
                    Intent intent=new Intent(getActivity(),LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private class SettingListAdapter extends ArrayAdapter<String> {
        public SettingListAdapter(ArrayList<String> settingList) {
            super(getActivity(), android.R.layout.simple_list_item_1, settingList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_setting_item, null);
            }
            // configure the view for this Song
            String settingname = getItem(position);
            TextView settingNameTextView = (TextView) convertView.findViewById(R.id.setting_Name);

            settingNameTextView.setText(settingname);
            return convertView;
        }
    }
}
