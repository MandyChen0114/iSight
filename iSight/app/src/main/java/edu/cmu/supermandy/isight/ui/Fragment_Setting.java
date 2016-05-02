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
import android.widget.Toast;

import java.util.ArrayList;

import edu.cmu.supermandy.isight.util.DBDAO;

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
        settingList.add("Clear History");
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
                if(position==0) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), AccountActivity.class);
                    intent.putExtra("Id", getActivity().getIntent().getStringExtra("Id").toString());
                    startActivity(intent);
                }
                else if(position==1) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), ChangePWDActivity.class);
                    intent.putExtra("Id", getActivity().getIntent().getStringExtra("Id").toString());
                    startActivity(intent);
                }
                else if(position==2) {
                    final DBDAO recorddao = new DBDAO(getActivity());
                    recorddao.clearRecord();
                    Toast.makeText(getActivity(), "Your history has been deleted.\n ", Toast.LENGTH_LONG).show();
                }
                else if(position==3) {
                    Intent intent=new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT,"Share");
                    intent.putExtra(Intent.EXTRA_TEXT, "I have tested my eyes, do you wanna try?");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(Intent.createChooser(intent, "Select the way to share"));
                }
                else if(position==4){
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
