package com.chhuang.ch;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainList extends ListActivity {

	private String classes[] = { "SimpleCounter", "TextPlay", "Email", "Camera", "SimpleQuestion", "Graphics",
			"GFXSurface", "SoundStuff", "SimpleBrowser","ExternalDataExample", "InternalDataExample","DataSharedPrefsExample","SliderExample", "TabsExample", "FlipperExample" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Full screen
		/*
		 * requestWindowFeature(Window.FEATURE_NO_TITLE);
		 * getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		 * WindowManager.LayoutParams.FLAG_FULLSCREEN);
		 */
		setListAdapter(new ArrayAdapter<String>(MainList.this, android.R.layout.simple_list_item_1, classes));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		try {
			Class<?> myClass = Class.forName("com.chhuang.ch." + classes[position]);
			Intent myIntent = new Intent(MainList.this, myClass);
			startActivity(myIntent);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.mainlist_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_aboutUs:
				Intent aboutIntent = new Intent("com.chhuang.ch.ABOUT");
				startActivity(aboutIntent);
				break;

			case R.id.menu_preferences:
				Intent prefIntent = new Intent("com.chhuang.ch.PREFS");
				startActivity(prefIntent);
				break;

			case R.id.menu_exit:
				finish();
				break;

		}
		return false;
	}

}
