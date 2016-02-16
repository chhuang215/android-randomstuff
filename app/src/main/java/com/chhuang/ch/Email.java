package com.chhuang.ch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Email extends Activity implements View.OnClickListener {
	EditText personsEmail, intro, personsName, stupidThings, hatefulAction, outro;
	String emailAdd, beginning, name, stupidAction, hatefulAct, out;
	Button sendEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email);
		initializeVars();
		sendEmail.setOnClickListener(this);
	}

	private void initializeVars() {
		personsEmail = (EditText) findViewById(R.id.etEmail);
		intro = (EditText) findViewById(R.id.etIntro);
		personsName = (EditText) findViewById(R.id.etName);
		stupidThings = (EditText) findViewById(R.id.etThings);
		hatefulAction = (EditText) findViewById(R.id.etAction);
		outro = (EditText) findViewById(R.id.etOutro);
		sendEmail = (Button) findViewById(R.id.btnSendEmail);
	}

	public void onClick(View v) {
		convertEditTextVarsIntoStrings();
		String emailaddress[] = { emailAdd };
		String message = "Well, hello "
				+ name
				+ ". \nI just wanted to say "
				+ beginning
				+ ". Not only that, but I hate it when you "
				+ stupidAction
				+ ", that's making me crazy.  I just want to make you "
				+ hatefulAct
				+ ".\nWelp, thats all I wanted to chit-chatter about, oh and..\n"
				+ out + "!"
				+ '\n' + "PS. Nah, you are awesome";
	
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, emailaddress);
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "I hate you, " + name);
		emailIntent.setType("plain/text");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
		startActivity(emailIntent);
		
	}

	private void convertEditTextVarsIntoStrings() {

		emailAdd = personsEmail.getText().toString();
		beginning = intro.getText().toString();
		name = personsName.getText().toString();
		stupidAction = stupidThings.getText().toString();
		hatefulAct = hatefulAction.getText().toString();
		out = outro.getText().toString();
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
}
