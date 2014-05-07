/**
 * 
 */
package com.example.imageration.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.imageration.R;

/**
 * @author wangxuyang
 * 
 */
public class MyMenu extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		findView();
	}

	@Override
	protected void onStart() {
		super.onStart();
		ActionBar actionBar = this.getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);
	}

	private void findView() {
		Button b1 = (Button) findViewById(R.id.calc);
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MyMenu.this, CalculatorActivity.class));
			}
		});
		Button b2 = (Button) findViewById(R.id.channel);
		b2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MyMenu.this, CalculatorActivity.class));
			}
		});
		Button b3 = (Button) findViewById(R.id.ly);
		b3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MyMenu.this, LayoutMenu.class));
			}
		});
		Button b4 = (Button) findViewById(R.id.eg2);
		b4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (getActionBar().isShowing()) {
					getActionBar().hide();
				} else {
					getActionBar().show();
				}

			}
		});
		Button b5 = (Button) findViewById(R.id.nm);
		b5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MyMenu.this, NodeManagerMenuActivity.class));
			}
		});
	} 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_options, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.item1:
		case R.id.item2:
		case R.id.item3:
			Log.d("info", "xxxx" + item.getTitle().toString());
			return true;
		case R.id.item4:
		case R.id.item5:
		case R.id.item6:
			Log.d("info", "===" + item.getTitle().toString());
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
