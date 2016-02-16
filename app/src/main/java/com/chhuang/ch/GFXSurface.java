package com.chhuang.ch;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener {

	private MyGraphicObjectSurface surfaceView;
	private float clickedX, clickedY;
	private float indicatorStartX, indicatorStartY, indicatorFinishX, indicatorFinishY;
	private float animatedX, animatedY, scaledX, scaledY;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		resetClickedPosition();
		resetStartIndicatorPositon();
		resetFinishIndicatorPosition();
		resetAnimatedPosition();
		resetScaledPosition();
		surfaceView = new MyGraphicObjectSurface(this);
		surfaceView.setOnTouchListener(this);
		setContentView(surfaceView);
	}

	@Override
	protected void onPause() {
		super.onPause();
		surfaceView.pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		surfaceView.resume();
	}

	public boolean onTouch(View v, MotionEvent event) {

		clickedX = event.getX();
		clickedY = event.getY();

		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				indicatorStartX = event.getX();
				indicatorStartY = event.getY();

				resetAnimatedPosition();
				resetScaledPosition();
				resetFinishIndicatorPosition();

				surfaceView.setAnimatedX(animatedX);
				surfaceView.setAnimatedY(animatedY);
				surfaceView.setIndicatorStartX(indicatorStartX);
				surfaceView.setIndicatorStartY(indicatorStartY);

				break;

			case MotionEvent.ACTION_UP:
				indicatorFinishX = event.getX();
				indicatorFinishY = event.getY();

				scaledX = (indicatorFinishX - indicatorStartX) / 20;
				scaledY = (indicatorFinishY - indicatorStartY) / 20;

				resetClickedPosition();

				break;
		}

		surfaceView.setIndicatorFinishX(indicatorFinishX);
		surfaceView.setIndicatorFinishY(indicatorFinishY);

		surfaceView.setScaledX(scaledX);
		surfaceView.setScaledY(scaledY);
		surfaceView.setClickedX(clickedX);
		surfaceView.setClickedY(clickedY);

		return true;
	}

	public void resetScaledPosition() {
		scaledX = scaledY = 0;
	}

	public void resetClickedPosition() {
		clickedX = clickedY = 0;
	}

	public void resetAnimatedPosition() {
		animatedX = animatedY = 0;
	}

	public void resetStartIndicatorPositon() {
		indicatorStartX = 0;
		indicatorStartY = 0;
	}

	public void resetFinishIndicatorPosition() {
		indicatorFinishX = 0;
		indicatorFinishY = 0;
	}

}
