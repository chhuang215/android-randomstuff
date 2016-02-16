package com.chhuang.ch;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TextPlay extends Activity implements View.OnClickListener{

	private ToggleButton passToggle;
	private EditText input;
	private TextView display;
	private Button chkCommand;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.text);

		passToggle = (ToggleButton) findViewById(R.id.tbtnPassword);
		input = (EditText) findViewById(R.id.etCommand);
		display = (TextView) findViewById(R.id.tvResult);
		chkCommand = (Button) findViewById(R.id.btnResult);

		passToggle.setOnClickListener(this);

		chkCommand.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()){
		case R.id.btnResult:
			String check = input.getText().toString();

			display.setText(check);

			if (check.contains("left")) {
				display.setGravity(Gravity.LEFT);
			} else if (check.contains("center")) {
				display.setGravity(Gravity.CENTER);
			} else if (check.contains("right")) {
				display.setGravity(Gravity.RIGHT);
			} else if (check.contains("blue")) {
				display.setTextColor(Color.BLUE);
			} else {
				display.setText("invalid");
				display.setGravity(Gravity.CENTER);
				display.setTextColor(Color.WHITE);
			}
			
			if (check.contains("WTF")) {
				Random crazy = new Random();
				display.setText("WTF!");
				display.setTextSize((crazy.nextInt(75) + 1));
				display.setTextColor(Color.rgb(crazy.nextInt(255), crazy.nextInt(255), crazy.nextInt(255)));
				switch(crazy.nextInt(3)){
				case 0 :
					display.setGravity(Gravity.LEFT);
					break;
				case 1 :
					display.setGravity(Gravity.CENTER);
					break;
				case 2 :
					display.setGravity(Gravity.RIGHT);
					break;
				}
			}
			break;
		case R.id.tbtnPassword:
			if (passToggle.isChecked()) {
				input.setInputType(InputType.TYPE_CLASS_TEXT
						| InputType.TYPE_TEXT_VARIATION_PASSWORD);
			} else {
				input.setInputType(InputType.TYPE_CLASS_TEXT);
			}
			break;		
		}
		
	}
}
