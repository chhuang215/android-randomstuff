package com.chhuang.ch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ExternalDataExample extends Activity implements OnItemSelectedListener, OnClickListener {
	private String state;
	private Spinner spinner;
	private String[] paths = { "Music", "Pictures", "Download" };
	private boolean canW, canR;
		
	private TextView canWrite, canRead;
	private File path;
	private File file;
	private EditText saveFile;
	private Button confirm, save;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.externaldata);

		initialize();

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(ExternalDataExample.this,
				android.R.layout.simple_spinner_dropdown_item, paths);
		spinner = (Spinner) findViewById(R.id.spinner);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);

	}

	private void checkState() {
		state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			// read and write
			canWrite.setText("true");
			canRead.setText("true");
			canR = canW = true;
		} else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {

			// read only
			canWrite.setText("false");
			canRead.setText("true");
			canR = true;
			canW = false;

		} else {
			// can't read or write
			canWrite.setText("false");
			canRead.setText("false");
		}

	}

	private void initialize() {
		path = file = null;
		canWrite = (TextView) findViewById(R.id.tvCanWrite);
		canRead = (TextView) findViewById(R.id.tvCanRead);
		confirm = (Button) findViewById(R.id.btnConfirmSaveAs);
		save = (Button) findViewById(R.id.btnSaveFileExternal);
		saveFile = (EditText) findViewById(R.id.etSaveAs);
		
		canR = canW = false;

		confirm.setOnClickListener(this);
		save.setOnClickListener(this);

	}

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		int position = spinner.getSelectedItemPosition();
		switch (position) {
			case 0:
				path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
				break;
			case 1:
				path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
				break;
			case 2:
				path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
				break;

		}

	}

	public void onNothingSelected(AdapterView<?> arg0) {

	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnConfirmSaveAs:
				save.setVisibility(View.VISIBLE);
				break;
			case R.id.btnSaveFileExternal:
				String fileName = saveFile.getText().toString();
				file = new File(path, fileName);

				checkState();

				if (canW && canR) {
					
					path.mkdirs();
					
					try {
						InputStream inputStream = getResources().openRawResource(+ R.drawable.my_smiley);
						OutputStream outputStream = new FileOutputStream(file);
						byte[] data = new byte[inputStream.available()];
						
						inputStream.read(data);
						outputStream.write(data);
						inputStream.close();
						outputStream.close();
						
						Toast t= Toast.makeText(ExternalDataExample.this, "File has been Saved", Toast.LENGTH_LONG);
						t.show();
						
						
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				break;

		}

	}
}
