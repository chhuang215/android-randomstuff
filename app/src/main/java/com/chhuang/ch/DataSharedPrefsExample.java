package com.chhuang.ch;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DataSharedPrefsExample extends Activity implements OnClickListener {

	public static final String filename = "PrefsData";
	private Button save, load;
	private EditText inputData;
	private TextView displayLoad;
	private SharedPreferences data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.saveandload);
		initialize();
		data = getSharedPreferences(filename, 0);
	}

	private void initialize() {
		save = (Button) findViewById(R.id.btnSaveSharedPrefs);
		load = (Button) findViewById(R.id.btnLoadSharedPrefs);
		inputData = (EditText) findViewById(R.id.etInputData);
		displayLoad = (TextView) findViewById(R.id.tvLoadDataResult);

		save.setOnClickListener(this);
		load.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnSaveSharedPrefs:
				String inputtedData = inputData.getText().toString();
				Editor editor = data.edit();
				editor.putString("sharedString", inputtedData);
				
				editor.commit();
				break;
			case R.id.btnLoadSharedPrefs:
				data = getSharedPreferences(filename, 0);
				String loadedData = data.getString("sharedString", "COULDN'T LOAD DATA");
				displayLoad.setText(loadedData);
				break;
		}

	}

}
