package com.android.dongnan.taskmaster;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.android.dongnan.taskmaster.data.CommonHolder;
import com.android.dongnan.taskmaster.data.DataConstants;

public class MainActivity extends AppCompatActivity implements DataConstants.ActivityCallback{

    private MainFragment mMainFragment;
    private EditFragment mEditFragment;
    private ViewPager mViewPager;

    public static final int INDEX_MAIN = 0;
    public static final int INDEX_EDIT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CommonHolder.setApplicationContext(this.getApplicationContext());

        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager)findViewById(R.id.container);
        mViewPager.setAdapter(new StoryPager(getSupportFragmentManager()));
        mViewPager.setCurrentItem(0);
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
            if (position == INDEX_MAIN) {
                if (mMainFragment == null) {
                    mMainFragment = new MainFragment();
                    mMainFragment.registerCallback(MainActivity.this);
                }
                mMainFragment.updateData();
                return mMainFragment;
            } else if (position == INDEX_EDIT) {
                if (mEditFragment == null) {
                    mEditFragment = new EditFragment();
                    mEditFragment.registerCallback(MainActivity.this);
                }
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

    @Override
    public void onItemClicked(DataConstants.StoryData data) {
        if (mEditFragment == null) {
            mEditFragment = new EditFragment();
        }
        mEditFragment.updateView(data);
        mViewPager.setCurrentItem(INDEX_EDIT);
    }

    @Override
    public void onDataChanged() {
        mViewPager.setCurrentItem(INDEX_MAIN);
    }
}
