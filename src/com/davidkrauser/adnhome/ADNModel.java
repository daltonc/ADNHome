package com.davidkrauser.adnhome;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class ADNModel {
	
	public ADNModel() {
		
	}
	
	public void authorize() {
		if (!isAuthorized())
		{
			
		}
	}
	
	public void dispatchPost(String postText, EditText composeField)
	{
		composeField.setText("");
	}
	
	private boolean isAuthorized() {
		return false;
	}
	
	private class AuthorizationActivity extends Activity
	{
		@Override
	    @SuppressLint("SetJavaScriptEnabled")
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        WebView wv = new WebView(this);
	        wv.getSettings().setJavaScriptEnabled(true);
	        wv.setVerticalScrollBarEnabled(false);
	        wv.setHorizontalScrollBarEnabled(false);
	        
	        setContentView(wv);

	        wv.setWebViewClient(new WebViewClient() {
	            @Override
	            public void onPageFinished(WebView view, String url) {
	                if (url.contains("AUTH_CODE"))
	                {
	                	
	                }
	                
	                super.onPageFinished(view, url);
	            }
	        });
	        wv.loadUrl("http://alpha.app.net");
	    }
		
		public boolean onKeyDown(int keyCode, KeyEvent event)
		{
			boolean result = false;
			
			if (keyCode != KeyEvent.KEYCODE_HOME)
			{
				result = true;
			}
			
			return result;
		}
	}

}
