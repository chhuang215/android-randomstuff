package com.chhuang.ch;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Welcome extends Activity {

	private SoundPool openingMusic;
	private int clap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
		clap = 0;
		
		openingMusic = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		clap = openingMusic.load(this, R.raw.sound_clap, 1);
		
		SharedPreferences getPreference = PreferenceManager.getDefaultSharedPreferences(super.getBaseContext());
		boolean playMusic = getPreference.getBoolean("checkbox_music", true);
		if (playMusic && clap != 0) {
			openingMusic.play(clap, 1, 1, 0, 0, 1);
		}
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(2700);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					startActivity(new Intent("com.chhuang.ch.MENU"));
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		openingMusic.release();
		finish();
	}

}
