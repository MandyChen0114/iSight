package edu.cmu.supermandy.isight.ui;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Mandy on 4/4/16.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Fragment_Test tab1 = new Fragment_Test();
                return tab1;
            case 1:
                Fragment_Quiz tab2 = new Fragment_Quiz();
                return tab2;
            case 2:
                Fragment_History tab3 = new Fragment_History();
                return tab3;
            case 3:
                Fragment_Setting tab4 = new Fragment_Setting();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
