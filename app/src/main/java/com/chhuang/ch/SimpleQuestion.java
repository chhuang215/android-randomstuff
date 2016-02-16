package com.chhuang.ch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SimpleQuestion extends Activity implements OnClickListener{
	public static final int ASKQUESTION = 1;
	public static final String KEY_USER_INPUT = "input";

	EditText etUserInputName;
	Button btnStart;
	Button btnResult;
	TextView gotAnswer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplequestion_main);
		initialize();
	}

	private void initialize() {
		etUserInputName = (EditText) findViewById(R.id.etSend);
		btnStart = (Button) findViewById(R.id.btnStartActivity);
		btnResult = (Button) findViewById(R.id.btnStartActivityForResult);
		gotAnswer = (TextView) findViewById(R.id.tvResult);

		btnStart.setOnClickListener(this);
		btnResult.setOnClickListener(this);
	}

	public void onClick(View v) {

		Intent askQuestion = new Intent(SimpleQuestion.this, Questions.class);
		String userInput = etUserInputName.getText().toString();
		Bundle questionBundle = new Bundle();
		questionBundle.putString(KEY_USER_INPUT, userInput);
		askQuestion.putExtras(questionBundle);

		switch (v.getId()) {
		case R.id.btnStartActivity:
			startActivity(askQuestion);
			break;
		case R.id.btnStartActivityForResult:
			startActivityForResult(askQuestion, ASKQUESTION);
			break;
		}
	}
	 

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case ASKQUESTION:
			if (resultCode == RESULT_OK) {
				Bundle questionBundle = data.getExtras();
				String answer = questionBundle.getString(Questions.KEY_ANSWER);
				gotAnswer.setText(answer);

			}
			break;
		
		}
	}



}
