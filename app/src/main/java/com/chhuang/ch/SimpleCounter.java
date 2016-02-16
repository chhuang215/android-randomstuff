package com.chhuang.ch;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class SimpleCounter extends Activity implements OnClickListener {

	private int counter;
	private Button add1;
	private Button subtract1;
	private Button reset;
	private Button save;
	private Button clear_data;
	private TextView display;
	private TabHost tabhost;
	private ListView savedResults;
	private ArrayAdapter<String> savedListAdapter;
	private ArrayList<String> data;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.counter);
		initialize();
	}

	/** Initialize all attributes */
	public void initialize() {
		data = new ArrayList<String>();
		savedListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);

		// Counter
		counter = 0;

		// Buttons
		add1 = (Button) findViewById(R.id.btnAdd);
		subtract1 = (Button) findViewById(R.id.btnSubtract);
		reset = (Button) findViewById(R.id.btnReset);
		save = (Button) findViewById(R.id.btnSaveCounterResult);
		clear_data = (Button) findViewById(R.id.btnClearData);
		add1.setOnClickListener(this);
		subtract1.setOnClickListener(this);
		save.setOnClickListener(this);
		reset.setOnClickListener(this);
		clear_data.setOnClickListener(this);

		// Display textview
		display = (TextView) findViewById(R.id.tvDisplayCounts);
		display.setText("0");

		// Tabs
		tabhost = (TabHost) findViewById(R.id.tabhost_simplecounter);
		tabSetup();

		// Listview
		savedResults = (ListView) findViewById(R.id.lsSavedResults);
		savedResults.setAdapter(savedListAdapter);
	}

	private void tabSetup() {
		tabhost.setup();
		TabSpec counterSpec;
		counterSpec = tabhost.newTabSpec("counterTag");
		counterSpec.setContent(R.id.tab_counter);
		counterSpec.setIndicator("Counter");
		tabhost.addTab(counterSpec);

		TabSpec savedSpec;
		savedSpec = tabhost.newTabSpec("savedTag");
		savedSpec.setContent(R.id.tab_saved);
		savedSpec.setIndicator("Saved results");
		tabhost.addTab(savedSpec);

	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnAdd:
				display.setText(String.valueOf(++counter));
				break;
			case R.id.btnSubtract:
				display.setText(String.valueOf(--counter));
				break;
			case R.id.btnReset:
				counter = 0;
				display.setText(String.valueOf(counter));
				break;
			case R.id.btnSaveCounterResult:
				data.add(String.valueOf(counter));
				savedListAdapter.notifyDataSetChanged();
				break;
			case R.id.btnClearData:
				data.clear();
				savedListAdapter.notifyDataSetChanged();
				break;

		}
	}
}