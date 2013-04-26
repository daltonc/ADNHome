package com.davidkrauser.adnhome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ApplicationListFragment extends Fragment {

	// TODO: this is a mess and probably unsafe. Shouldn't need all of these globals. 
	ApplicationListAdapter mAdapter;
	PackageManager mPackageManager;
	Context mContext;
	GridView mView;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = (GridView) inflater.inflate(R.layout.application_list,
				container, false);
		mContext = inflater.getContext();
		mPackageManager = inflater.getContext().getPackageManager();

		buildApplicationListAdapter();

		return mView;
	}
	
	@Override
	public void onResume()
	{
		buildApplicationListAdapter();
		super.onResume();
	}
	
	private void buildApplicationListAdapter()
	{
		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

		final List<ResolveInfo> packages = mPackageManager.queryIntentActivities(mainIntent,
				0);
		Collections.sort(packages, new ResolveInfo.DisplayNameComparator(mPackageManager));

		ArrayList<ApplicationData> applications = new ArrayList<ApplicationData>();

		for (ResolveInfo info : packages) {
			ApplicationData application = new ApplicationData();
			application.mIcon = info.loadIcon(mPackageManager);
			application.mName = info.loadLabel(mPackageManager).toString();
			application.mIntent = mPackageManager
					.getLaunchIntentForPackage(info.activityInfo.packageName);
			applications.add(application);
		}

		mView.setAdapter(new ApplicationListAdapter(mContext,applications));
	}

	private class ApplicationData {
		public Drawable mIcon;
		public String mName;
		public Intent mIntent;
	}

	private class ApplicationListAdapter extends ArrayAdapter<ApplicationData> {

		public ApplicationListAdapter(Context context,
				List<ApplicationData> objects) {
			super(context, 0, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			LayoutInflater inflater = LayoutInflater.from(getContext());
			View view = (convertView != null)? convertView : inflater.inflate(R.layout.application, parent, false);

			final ApplicationData application = getItem(position);

			if (application != null) {
				final ImageView iv = (ImageView) view
						.findViewById(R.id.application_icon);
				iv.setImageDrawable(application.mIcon);
				iv.setAlpha(1.0f);

				TextView tv = (TextView) view
						.findViewById(R.id.application_label);
				tv.setText(application.mName);

				view.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						iv.setAlpha(0.5f);
						startActivity(application.mIntent);
					}
				});
			}

			return view;
		}
		
	}
}
