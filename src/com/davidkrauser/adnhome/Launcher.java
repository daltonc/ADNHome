package com.davidkrauser.adnhome;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;

public class Launcher extends FragmentActivity {

	public static ADNModel sADNModel;
	private LauncherPagerAdapter mLauncherPagerAdapter;
	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launcher);

		sADNModel = new ADNModel();

		mLauncherPagerAdapter = new LauncherPagerAdapter(
				getSupportFragmentManager());

		mViewPager = (ViewPager) findViewById(R.id.launcher_pager);
		mViewPager.setAdapter(mLauncherPagerAdapter);
		mViewPager.setCurrentItem(1);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (mViewPager.getCurrentItem() == 0
					&& ((StreamFragment) ((LauncherPagerAdapter) mViewPager
							.getAdapter()).getItem(0)).onKeyDown(keyCode)) {
				return true;
			} else {
				mViewPager.setCurrentItem(1);
				return true;
			}
		}

		if (keyCode == KeyEvent.KEYCODE_HOME) {
			mViewPager.setCurrentItem(1);
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	private class LauncherPagerAdapter extends FragmentPagerAdapter {

		public LauncherPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;

			switch (position) {
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
			return 2;
		}
	}
}
