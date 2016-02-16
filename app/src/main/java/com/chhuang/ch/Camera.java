package com.chhuang.ch;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Camera extends Activity implements View.OnClickListener{

	final static int cameraData = 0;
	
	ImageButton ib;
	Button b;
	ImageView pic;
	Intent intent;
	Bitmap bmp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.photo);
		initialize();
		InputStream is = getResources().openRawResource(+ R.drawable.ic_launcher);
		bmp = BitmapFactory.decodeStream(is);
	}
	
	private void initialize(){
		pic = (ImageView) findViewById(R.id.ivReturnedPicture);
		ib = (ImageButton) findViewById(R.id.ibTakePic);
		b = (Button) findViewById(R.id.btnSetWallpaper);
		b.setOnClickListener(this);
		ib.setOnClickListener(this);
	}
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			Bundle extras = data.getExtras();
			bmp = (Bitmap) extras.get("data");
			pic.setImageBitmap(bmp);
		}
	}

	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btnSetWallpaper:
			try {
				getApplicationContext().setWallpaper(bmp);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case R.id.ibTakePic:
			intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			super.startActivityForResult(intent, cameraData);
			break;
		}
	}
}
