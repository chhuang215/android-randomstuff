package com.chhuang.ch;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class SimpleBrowser extends Activity implements OnClickListener {

	private Button go, back, forward, refresh, clear_history;
	private EditText url;
	private WebView browser;

	private class MyWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplebrowser);
		initialize();

		browser.getSettings().setJavaScriptEnabled(true);
		browser.getSettings().setLoadWithOverviewMode(true);
		browser.getSettings().setUseWideViewPort(true);
		browser.getSettings().setBuiltInZoomControls(true);
		browser.setWebViewClient(new MyWebViewClient());

		try {
			browser.loadUrl("http://www.google.com");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void initialize() {
		browser = (WebView) findViewById(R.id.wvBrowser);
		go = (Button) findViewById(R.id.btnBrowserGo);
		back = (Button) findViewById(R.id.btnBrowserBack);
		forward = (Button) findViewById(R.id.btnBrowserForward);
		refresh = (Button) findViewById(R.id.btnBrowserRefresh);
		clear_history = (Button) findViewById(R.id.btnBrowserClearHistory);
		url = (EditText) findViewById(R.id.etBrowserUrl);

		go.setOnClickListener(this);
		back.setOnClickListener(this);
		forward.setOnClickListener(this);
		refresh.setOnClickListener(this);
		clear_history.setOnClickListener(this);

	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnBrowserGo:

				// Hide keyboard
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(url.getWindowToken(), 0);

				String website = url.getText().toString();
				browser.loadUrl(website);

				break;

			case R.id.btnBrowserBack:
				if (browser.canGoBack())
					browser.goBack();
				break;
			case R.id.btnBrowserForward:
				if (browser.canGoForward())
					browser.goForward();
				break;
			case R.id.btnBrowserRefresh:
				browser.reload();
				break;
			case R.id.btnBrowserClearHistory:
				browser.clearHistory();
				break;
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
			case KeyEvent.KEYCODE_BACK:
				if (browser.canGoBack()) {
					browser.goBack();
					return true;
				}
				break;
		}
		return super.onKeyDown(keyCode, event);
	}

}
