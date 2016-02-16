package com.chhuang.ch;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SlidingDrawer;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

public class SliderExample extends Activity implements OnClickListener, OnCheckedChangeListener, OnDrawerOpenListener, OnDrawerCloseListener {

	private SlidingDrawer sd;
	Button handle1;
	Button handle2;
	Button handle3;
	Button handle4;
	private CheckBox chbxSlidable;
	private MediaPlayer music;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sliding);
		initialize();
		setListeners();
	}

	public void setListeners() {

		sd.setOnDrawerOpenListener(this);
		chbxSlidable.setOnCheckedChangeListener(this);
		handle1.setOnClickListener(this);
		handle2.setOnClickListener(this);
		handle3.setOnClickListener(this);
		handle4.setOnClickListener(this);
	}

	public void initialize() {
		sd = (SlidingDrawer) findViewById(R.id.slidingD);
		chbxSlidable = (CheckBox) findViewById(R.id.cbSlidable);
		handle1 = (Button) findViewById(R.id.btnSlider1);
		handle2 = (Button) findViewById(R.id.btnSlider2);
		handle3 = (Button) findViewById(R.id.btnSlider3);
		handle4 = (Button) findViewById(R.id.btnSlider4);
		music = MediaPlayer.create(this, R.raw.hole_punch);
	}

	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (buttonView.isChecked()) {
			sd.lock();
		} else {
			sd.unlock();
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnSlider1:
				sd.open();
				break;
			case R.id.btnSlider2:
				break;
			case R.id.btnSlider3:
				sd.toggle();
				break;
			case R.id.btnSlider4:
				sd.close();
				break;
		}
	}

	public void onDrawerOpened() {
		music.start();
	}

	public void onDrawerClosed() {
		music.release();
	}
}
