package com.davidkrauser.adnhome;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class Launcher extends FragmentActivity {

	public static ADNModel sADNModel;
	private LauncherPagerAdapter mLauncherPagerAdapter;
	private ViewPager mViewPager;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher);

        sADNModel = new ADNModel();
        
        mLauncherPagerAdapter = new LauncherPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.launcher_pager);
        mViewPager.setAdapter(mLauncherPagerAdapter);
        mViewPager.setCurrentItem(1);        
    }
    
    private class LauncherPagerAdapter extends FragmentPagerAdapter {

        public LauncherPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
        	Fragment fragment = null;
        	
        	switch(position)
        	{
        	case 0:
        		fragment = new StreamFragment();
        		break;
        	case 1:
        		fragment = new ApplicationListFragment();
        		break;
        	case 2:
        		fragment = new MessagesFragment();
        		break;
        	default:
        		break;
        	}
        	
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 3;
        }
    }
}
