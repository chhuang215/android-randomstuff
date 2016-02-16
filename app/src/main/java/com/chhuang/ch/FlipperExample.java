package com.chhuang.ch;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ViewFlipper;

public class FlipperExample extends Activity implements OnClickListener{
	private ViewFlipper vf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flipper);
		vf = (ViewFlipper) findViewById(R.id.vfFlipper);
		vf.setOnClickListener(this);
		vf.setFlipInterval(500);
		vf.startFlipping();
	}

	public void onClick(View v) {
		vf.showNext();
	}
}
