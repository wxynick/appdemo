package com.example.imageration.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.imageration.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final EditText ev_uid = (EditText)findViewById(R.id.ev_uid);
		final EditText ev_pwd = (EditText)findViewById(R.id.ev_pwd);
		Button btn_login = (Button)findViewById(R.id.btn_login);
		Button btn_clean = (Button)findViewById(R.id.btn_clean);
		btn_login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String uid = ev_uid.getText().toString();
				String pwd = ev_pwd.getText().toString();
				if ("".equalsIgnoreCase(uid)||"".equalsIgnoreCase(pwd)) {
					Toast.makeText(MainActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
				}else if ("nick".equalsIgnoreCase(uid)&&"123456".equalsIgnoreCase(pwd)) {
					startActivity(new Intent(MainActivity.this,MyMenu.class));
					Toast.makeText(MainActivity.this, "恭喜你登录成功", Toast.LENGTH_LONG).show();
				}else{
					Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
				}
			}
		});
		btn_clean.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ev_uid.getText().clear();
				ev_pwd.setText("");
			}
		});
	}
}
