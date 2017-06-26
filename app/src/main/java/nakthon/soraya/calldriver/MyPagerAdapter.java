package nakthon.soraya.calldriver;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by masterUNG on 6/26/2017 AD.
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter{

    private FragmentManager fragmentManager;
    private int anInt;  //Number of Tab

    public MyPagerAdapter(FragmentManager fragmentManager,
                          int anInt) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
        this.anInt = anInt;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                SearchViewFragment searchViewFragment = new SearchViewFragment();
                return searchViewFragment;
            case 1:
                ReportPureJobFragment reportPureJobFragment = new ReportPureJobFragment();
                return reportPureJobFragment;
            case 2:
                ReportJobTableFragment reportJobTableFragment = new ReportJobTableFragment();
                return reportJobTableFragment;
            case 3:
                EdtiJobFragment edtiJobFragment = new EdtiJobFragment();
                return edtiJobFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return anInt;
    }
}   // Main Class
