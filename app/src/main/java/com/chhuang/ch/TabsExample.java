package com.chhuang.ch;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class TabsExample extends Activity implements OnClickListener, TabContentFactory {
	private Button addNewTab;
	private Button startWatch;
	private Button stopWatch;
	private TabHost th;
	private TextView showTime;
	private long start, stop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);

		initialize();

		th.setup();
		TabSpec specs;

		specs = th.newTabSpec("tag1");
		specs.setContent(R.id.tab1);
		specs.setIndicator("Stopwatch");
		th.addTab(specs);

		specs = th.newTabSpec("tag2");
		specs.setContent(R.id.tab2);
		specs.setIndicator("Add a tab");
		th.addTab(specs);

		specs = th.newTabSpec("tag3");
		specs.setContent(R.id.tab3);
		specs.setIndicator("Temp");
		th.addTab(specs);

	}

	private void initialize() {
		th = (TabHost) findViewById(R.id.tabhost);
		addNewTab = (Button) findViewById(R.id.btnAddTab);
		startWatch = (Button) findViewById(R.id.btnStartWatch);
		stopWatch = (Button) findViewById(R.id.btnStopWatch);
		showTime = (TextView) findViewById(R.id.tvShowWatch);

		start = stop = 0;

		showTime.setText("0:00:00");
		addNewTab.setOnClickListener(this);
		startWatch.setOnClickListener(this);
		stopWatch.setOnClickListener(this);

	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnAddTab:
				TabSpec newSpec = th.newTabSpec("NewTag");
				newSpec.setContent(this);

				newSpec.setIndicator("NEW");
				th.addTab(newSpec);

				break;
			case R.id.btnStartWatch:
				start = System.currentTimeMillis();
				
				break;
			case R.id.btnStopWatch:
				stop = System.currentTimeMillis();

				long result = stop - start;

				int millis = (int) result % 100;
				int seconds = (int) (result / 1000) % 60;
				int minutes = seconds / 60;

				if (start != 0) {
					showTime.setText(String.format("%d:%02d:%02d", minutes, seconds, millis));
				}

				break;
		}
	}

	public View createTabContent(String tag) {
		TextView tv = new TextView(TabsExample.this);
		tv.setText("You've created a new tab");
		return tv;

	}
}
