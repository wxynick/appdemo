/**
 * 
 */
package com.example.imageration.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.imageration.R;

/**
 * @author wangxuyang
 * 
 */
public class LayoutMenu extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ly_menu);
		findView();
	}

	private void findView() {
		Button btn_lly = (Button) findViewById(R.id.lly);
		btn_lly.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LayoutMenu.this,
						CalculatorActivity.class));
			}
		});
		Button btn_tly = (Button) findViewById(R.id.tly);
		btn_tly.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LayoutMenu.this,
						CalculatorActivity.class));
			}
		});
		Button btn_fly = (Button) findViewById(R.id.fly);
		btn_fly.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LayoutMenu.this,
						CalculatorActivity.class));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 	super.onCreateOptionsMenu(menu); 
	        MenuItem add = menu.add(0, 1, 0, "Save"); 
	        MenuItem open = menu.add(0, 2, 1, "Open"); 
	        MenuItem close = menu.add(0, 3, 2, "Close"); 
	        add.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM); 
	        open.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM); 
	        close.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM); 
	        return true; 
	}

}
