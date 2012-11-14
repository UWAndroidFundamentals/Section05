package com.example.solution0501;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Solution0501 extends Activity implements Runnable {

	// reference three UI elements
	private TextView total;
	private EditText first;
	private EditText second;
	private Button calc;

	private int one = 0;
	private int two = 0;
	private int sum = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen001);

		// pull in definition from XML
		total = (TextView) findViewById(R.id.total);
		first = (EditText) findViewById(R.id.first); 
		second = (EditText) findViewById(R.id.second);
		calc = (Button) findViewById(R.id.calc);
		
		calc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				one = Integer.parseInt(first.getText().toString());
				two = Integer.parseInt(second.getText().toString());
				
				// create a new thread object
				Thread currentThread = new Thread(Solution0501.this);

				// invoke the start method for the thread
				currentThread.start();

			}
		});

	}

	// this method runs in its own thread
	@Override
	public void run() {
		try {
			sum = one + two;
			threadHandler.sendEmptyMessage(0);
		} catch (Exception e) {
			// in case the thread ends unexpectedly
		}
	}

	// this method runs in the original activity thread
	private Handler threadHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			// we are back in the original thread, so we can modify the UI
			total.setText(Integer.toString(sum));
		}
	};

}
