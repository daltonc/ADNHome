package com.davidkrauser.adnhome;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class StreamFragment extends Fragment {
	
	public static WebView mWebView;

	@SuppressLint("SetJavaScriptEnabled")
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mWebView = (WebView) inflater.inflate(R.layout.stream, container, false);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.loadUrl("http://alpha.app.net");
		
		mWebView.setWebViewClient(new WebViewClient() {
		    @Override
		    public boolean shouldOverrideUrlLoading(WebView view, String url) {
		        if (Uri.parse(url).getHost().contains("app.net")) {
		            return false;
		        }

		        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		        startActivity(intent);
		        return true;
		    }
		});

//		EditText composeStatusField = (EditText) view
//				.findViewById(R.id.compose_post_field);
//
//		composeStatusField
//				.setOnEditorActionListener(new OnEditorActionListener() {
//					public boolean onEditorAction(TextView v, int actionId,
//							KeyEvent event) {
//						boolean result = false;
//
//						if (actionId == EditorInfo.IME_ACTION_SEND) {
//							Launcher.sADNModel.dispatchPost(v.getText()
//									.toString(), (EditText) v);
//							result = true;
//						}
//
//						return result;
//					}
//				});

		return mWebView;
	}

    public boolean onKeyDown(int keyCode) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        
        return false;
    }
}
