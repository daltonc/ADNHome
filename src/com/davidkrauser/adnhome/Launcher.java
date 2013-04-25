package com.davidkrauser.adnhome;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

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

        EditText composeStatusField = (EditText) findViewById(R.id.compose_post_field);
        
        composeStatusField.setOnEditorActionListener(new OnEditorActionListener() {
        	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        		boolean result = false;
        		
        		if (actionId == EditorInfo.IME_ACTION_SEND)
        		{
        			Launcher.sADNModel.dispatchPost(v.getText().toString(), (EditText) v);
        			result = true;
        		}
        		
        		return result;
        	}
        });
        
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
        		fragment = new ApplicationListFragment();
        		break;
        	case 1:
        		fragment = new StreamFragment();
        		break;
        	default:
        		break;
        	}
        	
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
    }
}
