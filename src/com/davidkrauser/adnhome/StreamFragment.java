package com.davidkrauser.adnhome;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class StreamFragment extends Fragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.stream, container, false);

		EditText composeStatusField = (EditText) view
				.findViewById(R.id.compose_post_field);

		composeStatusField
				.setOnEditorActionListener(new OnEditorActionListener() {
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {
						boolean result = false;

						if (actionId == EditorInfo.IME_ACTION_SEND) {
							Launcher.sADNModel.dispatchPost(v.getText()
									.toString(), (EditText) v);
							result = true;
						}

						return result;
					}
				});

		return view;
	}
}
