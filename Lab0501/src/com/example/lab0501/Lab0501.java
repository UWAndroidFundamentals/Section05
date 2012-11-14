package com.example.lab0501;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class Lab0501 extends Activity implements Runnable {

	// text view influenced by the Thread
	private TextView message;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen001);

		// pull in definition of text view
		message = (TextView) findViewById(R.id.message);

		// create a new thread object
		Thread currentThread = new Thread(this);

		// invoke the start method for the thread
		currentThread.start();

	}

	// this method runs in its own thread
	@Override
	public void run() {
		try {
			// normally the work we want done goes here
			// for now we will just sleep for ten seconds (3000 milliseconds)
			Thread.sleep(10000);

			// at the end of the sleep, send an empty message to trigger the
			// original activity thread
			threadHandler.sendEmptyMessage(0);
		} catch (InterruptedException e) {
			// in case the thread ends unexpectedly
		}
	}

	// this method runs in the original activity thread
	private Handler threadHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			// we are back in the original thread, so we can modify the UI
			message.setText("Thread complete!");
		}
	};

}
