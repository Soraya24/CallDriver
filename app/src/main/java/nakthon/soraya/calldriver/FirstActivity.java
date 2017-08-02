package nakthon.soraya.calldriver;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class FirstActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

//        Create ToolBar
        createToolBar();

//        Create ViewPager
        createViewPager();



    }   // Main Method

    private void createViewPager() {
        viewPager = (ViewPager) findViewById(R.id.my_viewpaper);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(myPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void createToolBar() {
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.my_tablayout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.search_phone));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.report_pure_Job));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.report_job));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.edit_job));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }
}   // Main Class
