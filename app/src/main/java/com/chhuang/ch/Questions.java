package com.chhuang.ch;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class Questions extends Activity implements OnClickListener, OnCheckedChangeListener {

	public static final String KEY_ANSWER = "answer";
	TextView question, text;
	Button returnData;
	RadioGroup selectionList;
	String userInputName, setData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questions);
		initialize();
		SharedPreferences getPreference = PreferenceManager.getDefaultSharedPreferences(super.getBaseContext());
		String et = getPreference.getString("name", "CHHUANG");
		String values = getPreference.getString("list", "1");
		Bundle mainBundle = getIntent().getExtras();
		userInputName = mainBundle.getString(SimpleQuestion.KEY_USER_INPUT);

		if (userInputName.trim().equals(""))
			question.setText("You are ");
		else
			question.setText(userInputName + " is...");

		if (values.equals("1"))
			question.setText(et);

	}

	private void initialize() {
		question = (TextView) findViewById(R.id.tvQuestion);
		text = (TextView) findViewById(R.id.tvText);
		returnData = (Button) findViewById(R.id.btnReturn);
		selectionList = (RadioGroup) findViewById(R.id.rgAnswers);

		selectionList.setOnCheckedChangeListener(this);
		returnData.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent intent = new Intent();
		Bundle b = new Bundle();
		b.putString(KEY_ANSWER, setData);
		intent.putExtras(b);
		setResult(RESULT_OK, intent);
		finish();
	}

	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		if (arg0.getId() == R.id.rgAnswers) {
			RadioButton rbSelection = (RadioButton) findViewById(arg1);
			switch (arg1) {

				case R.id.rCrazy:
					setData = "Probably right.";
					break;
				case R.id.rSexy:
					setData = "Definitely right!";
					break;
				case R.id.rBoth:
					setData = "Can't agree with you any more!";
					break;
			}
			String s = rbSelection.getText().toString() + "?";
			text.setText(s);
		} else {

			text.setText("NO!");
		}
	}

}
