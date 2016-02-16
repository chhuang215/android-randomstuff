package com.chhuang.ch;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

public class SoundStuff extends Activity implements OnClickListener, OnLongClickListener {

	private SoundPool sp;
	private MediaPlayer mp;
	private int laugh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View v = new View(this);
		v.setOnClickListener(this);
		v.setOnLongClickListener(this);
		setContentView(v);
		laugh = 0;
		sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		laugh = sp.load(this, R.raw.evil_laugh, 1);
		mp = MediaPlayer.create(this, R.raw.victory);
	}

	public void onClick(View arg0) {
		if (laugh != 0)
			sp.play(laugh, 1, 1, 0, 0, 1);
	}

	public boolean onLongClick(View arg0) {
		mp.start();
		return false;
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mp.release();
	}

}
