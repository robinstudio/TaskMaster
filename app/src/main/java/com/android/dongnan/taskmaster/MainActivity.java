package com.android.dongnan.taskmaster;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.android.dongnan.taskmaster.data.CommonHolder;

public class MainActivity extends AppCompatActivity {

    private MainFragment mMainFragment;
    private EditFragment mEditFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CommonHolder.setApplicationContext(this.getApplicationContext());

        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager)findViewById(R.id.container);
        viewPager.setAdapter(new StoryPager(getSupportFragmentManager()));
        viewPager.setCurrentItem(0);
    }

    @Override
    protected void onDestroy() {
        CommonHolder.setApplicationContext(null);
        super.onDestroy();
    }

    private class StoryPager extends FragmentPagerAdapter {

        private StoryPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                if (mMainFragment == null) {
                    mMainFragment = new MainFragment();
                }
                mMainFragment.updateData();
                return mMainFragment;
            } else if (position == 1) {
                if (mEditFragment == null) {
                    mEditFragment = new EditFragment();
                }
                mEditFragment.updateView();
                return mEditFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
