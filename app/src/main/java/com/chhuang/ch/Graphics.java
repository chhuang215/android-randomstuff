package com.chhuang.ch;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class Graphics extends Activity {

	MyGraphicObject view;
	WakeLock wakeLock;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Wake-lock
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "wake");
		//--
		wakeLock.acquire();
		
		view = new MyGraphicObject(this);
		setContentView(view);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		wakeLock.release();
	}
}
