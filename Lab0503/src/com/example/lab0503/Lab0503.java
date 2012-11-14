package com.example.lab0503;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.TextView;

public class Lab0503 extends Activity {

	// loading MyThread type object from separate class definition
	private MyThread myThread;
	
	// displays the message changed by the thread
	private TextView threadModifiedText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen001);
		threadModifiedText = (TextView) findViewById(R.id.text);

		// initializing and starting our Thread
		myThread = new MyThread(mainHandler);
		myThread.start();
	}

	// manages screen touch events
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
		
			// prepare a message with touch location data
			Message messageToThread = new Message();
			Bundle messageData = new Bundle();
			messageToThread.what = 0;
			messageData.putFloat("location_x", event.getX());
			messageData.putFloat("location_y", event.getY());
			messageToThread.setData(messageData);
			
			// sending message to MyThread
			myThread.getHandler().sendMessage(messageToThread);
		}
		return super.onTouchEvent(event);
	}

	// manages messages for current Thread (main)
	// received from our Thread
	public Handler mainHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
				// updates the TextView with the received text
				threadModifiedText.setText(msg.getData().getString("text"));
			}
		};
	};

}
