/**
 * 
 */
package com.example.imageration.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.imageration.R;

/**
 * @author wangxuyang
 * 
 */
public class CalculatorActivity extends Activity {
	Button[] btnArray = new Button[10];
	int[] btn_ids = { R.id.btn_key_0, R.id.btn_key_1, R.id.btn_key_2, R.id.btn_key_3,
			R.id.btn_key_4, R.id.btn_key_5, R.id.btn_key_6, R.id.btn_key_7, R.id.btn_key_8,
			R.id.btn_key_9 };
	TextView screen;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calc_layout);
		final TextView screen = (TextView) findViewById(R.id.screen);
		this.screen = screen;
		final Operation op = new Operation();
		for (int i = 0; i < 10; i++) {
			btnArray[i] = (Button) findViewById(btn_ids[i]);
			final Button b = btnArray[i];
			b.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					op.str1 += b.getText();
					screen.setText(op.str1);
				}
			});
		}
		Button plus = (Button) findViewById(R.id.plus);
		plus.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				op.setOp('+');
			}
		});
		Button minus = (Button) findViewById(R.id.minus);
		minus.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if ("".equals(op.str1)) {
					op.str1 += "-";
					screen.setText(op.str1);
				}else{
					op.setOp('-');
				}
				
			}
		});
		Button multi = (Button) findViewById(R.id.multi);
		multi.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				op.setOp('*');
			}
		});
		Button div = (Button) findViewById(R.id.div);
		div.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				op.setOp('/');
			}
		});
		Button reset = (Button) findViewById(R.id.clear);
		reset.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				op.reset();
				screen.setText(op.str1);
			}
		});
		Button equal = (Button) findViewById(R.id.equals);
		equal.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				screen.setText(op.calcResult());
			}
		});
	}

	private class Operation {
		String str1 = "";
		String str2 = "";
		char op = '+';

		String calcResult() {
			String result = "";
			if (str1.equals("") || str2.equals("")) {
				return result;
			}
			Integer ret = 0;
			switch (op) {
			case '+':
				ret = Integer.parseInt(str2) + Integer.parseInt(str1);
				break;
			case '-':
				ret = Integer.parseInt(str2) - Integer.parseInt(str1);
				break;
			case '*':
				ret = Integer.parseInt(str2) * Integer.parseInt(str1);
				break;
			case '/':
				if (str1.equals("0")) {
					result ="除数为0";
					return result;
				}else{
					ret = Integer.parseInt(str2) / Integer.parseInt(str1);
				}
				
				break;
				
			}
			result = ret.toString();
			return result;
		}

		void setOp(char c) {
			if (str2.equals("")) {
				op = c;
				str2 = str1;
				str1 = "";
			}else{
				str1=this.calcResult();
				screen.setText(str1);
				op = c;
				str2 = str1;
				str1 = "";
			}
			
		}

		void reset() {
			op = '+';
			str2 = "";
			str1 = "";
		}
	}

}
