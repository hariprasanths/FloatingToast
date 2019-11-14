package hari.floatingtoastsample;

import android.graphics.Color;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewPager viewPager = findViewById(R.id.viewPager);

        final TabLayout tabLayout = findViewById(R.id.tabs);

        if (viewPager != null) {
            setupViewPager(viewPager);
            tabLayout.setupWithViewPager(viewPager);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View tabView = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(tab.getPosition());
                tabView.setBackgroundColor(Color.parseColor("#404e4e"));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View tabView = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(tab.getPosition());
                tabView.setBackgroundColor(Color.parseColor("#5a7374"));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        int[] iconsList = {R.drawable.phone_white_24x24, R.drawable.phone_forwarded_white_24x24, R.drawable.phone_missed_white_24x24};

        for (int i = 0; i < tabLayout.getTabCount(); i++) {

            final TabLayout.Tab tab = tabLayout.getTabAt(i);
            View tabView = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);

            TextView tabTabText = (TextView) LayoutInflater.from(this).inflate(R.layout.tab_layout, null);
            tabTabText.setText("Tab " + (i + 1));
            tabTabText.setCompoundDrawablesWithIntrinsicBounds(0, iconsList[i], 0, 0);
            tabLayout.getTabAt(i).setCustomView(tabTabText);

            if (tab.isSelected()) {
                tabView.setBackgroundColor(Color.parseColor("#404e4e"));
            } else {
                tabView.setBackgroundColor(Color.parseColor("#5a7374"));
            }
        }
    }

    private void setupViewPager(ViewPager viewPager) {

        CustomPagerAdapter adapter = new CustomPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(FirstFragment.newInstance("Fixed positions"), "Fixed Positions");
        adapter.addFragment(SecondFragment.newInstance("Dynamic positions"), "Dynamic Positions");

        viewPager.setAdapter(adapter);
    }
}
