package com.davidkrauser.adnhome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ApplicationListFragment extends Fragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		GridView view = (GridView) inflater.inflate(R.layout.application_list,
				container, false);

		final PackageManager pm = inflater.getContext().getPackageManager();

		// get a list of installed apps.
		List<ApplicationInfo> packages = pm
				.getInstalledApplications(PackageManager.GET_META_DATA);
		ArrayList<ApplicationData> applications = new ArrayList<ApplicationData>();

		for (ApplicationInfo info : packages) {
			if (info.enabled) {
				ApplicationData application = new ApplicationData();
				application.mIcon = pm.getApplicationIcon(info);
				application.mName = pm.getApplicationLabel(info).toString();
				application.mIntent = pm.getLaunchIntentForPackage(info.packageName);
				applications.add(application);
			}
		}

		view.setAdapter(new ApplicationListAdapter(inflater.getContext(),
				applications));

		return view;
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
			View view = convertView;

			if (convertView == null) {
				final LayoutInflater inflater = LayoutInflater
						.from(getContext());
				view = inflater.inflate(R.layout.application, parent, false);
			}

			final ApplicationData application = getItem(position);

			if (application != null) {
				ImageView iv = (ImageView) view
						.findViewById(R.id.application_icon);
				iv.setImageDrawable(application.mIcon);
				iv.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						startActivity(application.mIntent);
					}
				});

				TextView tv = (TextView) view
						.findViewById(R.id.application_label);
				tv.setText(application.mName);
			}

			return view;
		}

	}
}
