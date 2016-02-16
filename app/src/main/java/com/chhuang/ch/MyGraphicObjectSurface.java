package com.chhuang.ch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MyGraphicObjectSurface extends SurfaceView implements Runnable {

	private SurfaceHolder holder;
	private Thread thread;
	private boolean isRunning;
	private Bitmap smileyFace, androidRobot;
	private float clickedX, clickedY;
	private float indicatorStartX, indicatorStartY, indicatorFinishX, indicatorFinishY;
	private float animatedX, animatedY, scaledX, scaledY;

	public MyGraphicObjectSurface(Context context) {
		super(context);
		isRunning = false;
		holder = getHolder();
		smileyFace = BitmapFactory.decodeResource(getResources(), R.drawable.my_smiley);
		androidRobot = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);

	}

	public void pause() {
		isRunning = false;
		boolean flag = true;
		while (flag) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			flag = false;
		}
		thread = null;
	}

	public void resume() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		Canvas canvas = null;
		float smileyPositionX = 0;
		float smileyPositionY = 0;
		float smileyMovingX = 0;
		float smileyMovingY = 0;
		float imageSX = 0;
		float imageSY = 0;
		float imageFX = 0;
		float imageFY = 0;

		while (isRunning) {
			
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (!holder.getSurface().isValid()) {
				continue;
			}
			canvas = holder.lockCanvas();

			canvas.drawRGB(2, 2, 150);

			if (clickedX != 0 && clickedY != 0) {
				smileyPositionX = clickedX - (smileyFace.getWidth() / 2);
				smileyPositionY = clickedY - (smileyFace.getHeight() / 2);
				canvas.drawBitmap(smileyFace, smileyPositionX, smileyPositionY, null);
			}

			if (indicatorStartX != 0 && indicatorStartY != 0) {
				imageSX = indicatorStartX - (androidRobot.getWidth() / 2);
				imageSY = indicatorStartY - (androidRobot.getHeight() / 2);
				canvas.drawBitmap(androidRobot, imageSX, imageSY, null);
			}

			if (indicatorFinishX != 0 && indicatorFinishY != 0) {
				imageFX = indicatorFinishX - (androidRobot.getWidth() / 2);
				imageFY = indicatorFinishY - (androidRobot.getHeight() / 2);
				smileyMovingX = indicatorFinishX - (smileyFace.getWidth() / 2) - animatedX;
				smileyMovingY = indicatorFinishY - (smileyFace.getHeight() / 2) - animatedY;

				canvas.drawBitmap(smileyFace, smileyMovingX, smileyMovingY, null);
				canvas.drawBitmap(androidRobot, imageFX, imageFY, null);
			}

			animatedX += scaledX;
			animatedY += scaledY;

			holder.unlockCanvasAndPost(canvas);
		}
	}

	public void setClickedX(float x) {
		this.clickedX = x;
	}

	public void setClickedY(float y) {
		this.clickedY = y;
	}

	public void setIndicatorStartX(float indicatorStartX) {
		this.indicatorStartX = indicatorStartX;
	}

	public void setIndicatorStartY(float indicatorStartY) {
		this.indicatorStartY = indicatorStartY;
	}

	public void setIndicatorFinishX(float indicatorFinishX) {
		this.indicatorFinishX = indicatorFinishX;
	}

	public void setIndicatorFinishY(float indicatorFinishY) {
		this.indicatorFinishY = indicatorFinishY;
	}

	public void setAnimatedX(float animatedX) {
		this.animatedX = animatedX;
	}

	public void setAnimatedY(float animatedY) {
		this.animatedY = animatedY;
	}

	public void setScaledX(float scaledX) {
		this.scaledX = scaledX;
	}

	public void setScaledY(float scaledY) {
		this.scaledY = scaledY;
	}
}
