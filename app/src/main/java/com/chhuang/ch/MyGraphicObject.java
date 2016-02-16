package com.chhuang.ch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

public class MyGraphicObject extends View {

	private static float xPosition;

	private Bitmap image;
	private float iconPositionY;
	private float iconPositionX;
	private float yPosition;

	private Typeface font;

	public MyGraphicObject(Context context) {
		super(context);
		image = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);

		yPosition = 0;
		font = Typeface.createFromAsset(context.getAssets(), "BROADW.TTF");
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		xPosition = canvas.getWidth() / 2;
		iconPositionY = yPosition - image.getHeight() / 2;
		iconPositionX = xPosition - image.getWidth() / 2;

		canvas.drawColor(Color.WHITE);

		Paint textPaint = new Paint();
		textPaint.setARGB(50, 250, 10, 50);
		textPaint.setTextAlign(Align.CENTER);
		textPaint.setTextSize(30);
		textPaint.setTypeface(font);
		canvas.drawText("GRAPHIC", xPosition, 70, textPaint);

		canvas.drawBitmap(image, iconPositionX, iconPositionY, new Paint());

		if (yPosition < canvas.getHeight()) {
			yPosition += 3;
		} else {
			yPosition = 0;
		}
		Rect middleRect = new Rect();
		middleRect.set(0, 150, canvas.getWidth(), 200);
		Paint paintBlue = new Paint();
		paintBlue.setColor(Color.BLUE);
		canvas.drawRect(middleRect, paintBlue);

		invalidate();

	}

}
