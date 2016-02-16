package com.chhuang.ch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InternalDataExample extends Activity implements OnClickListener {

	public static final String FILENAME = "InternalString";
	private Button save, load;
	private EditText inputData;
	private TextView displayLoad;
	private FileOutputStream fileOutputStream;

	private class LoadSomeStuff extends AsyncTask<String, Integer, String> {

		private ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			// super.onPreExecute();
			dialog = new ProgressDialog(InternalDataExample.this);
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setMax(100);
			dialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

			FileInputStream fileInputStream = null;
			String collectedData = null;

			for (int i = 0; i < 20; i++) {
				publishProgress(5);
				try {
					Thread.sleep(88);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			dialog.dismiss();			
			

			try {
				fileInputStream = openFileInput(FILENAME);
				byte[] dataArray = new byte[fileInputStream.available()];
				while (fileInputStream.read(dataArray) != -1) {
					collectedData = new String(dataArray);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fileInputStream.close();
					return collectedData;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			dialog.incrementProgressBy(values[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			// super.onPostExecute(result);
			displayLoad.setText(result);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.saveandload);
		initialize();
	}

	private void initialize() {
		save = (Button) findViewById(R.id.btnSaveSharedPrefs);
		load = (Button) findViewById(R.id.btnLoadSharedPrefs);
		inputData = (EditText) findViewById(R.id.etInputData);
		displayLoad = (TextView) findViewById(R.id.tvLoadDataResult);

		save.setOnClickListener(this);
		load.setOnClickListener(this);

		try {
			fileOutputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnSaveSharedPrefs:
				String data = inputData.getText().toString();

				// Saving data via File
				/*
				 * File file = new File(FILENAME);
				 * 
				 * fileOutputStream = new FileOutputStream(file); //write some
				 * data
				 * 
				 * fileOutputStream.close();
				 */

				try {
					fileOutputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
					fileOutputStream.write(data.getBytes());
					fileOutputStream.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				break;
			case R.id.btnLoadSharedPrefs:

				new LoadSomeStuff().execute(FILENAME);

				break;
		}

	}

}
