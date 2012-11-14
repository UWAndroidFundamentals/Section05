package com.example.lab0502;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.graphics.Point;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.TextView;

public class Lab0502 extends Activity {

	// message that reflects thread status
	private TextView message;
	
	// property that can be modified from thread
	// since this is NOT part of the UI, we can do this
	private int threadModifiedInt = 4;
	
	// used to position the message
	private Point threadModifiedPoint = new Point(20, 10);
	
	// determines if the thread will run or not
	private boolean activeThread = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen001);
		
		// pull the message definition from the XML
		message = (TextView) findViewById(R.id.message);
		
		// creating a new thread that has its own "run" method
		Thread myTread = new Thread() {
			@Override
			public void run() {
				try {
					// we can change these values cross-thread since they are not UI
					threadModifiedInt = 20;
					threadModifiedPoint.set(30, 40);
					
					// general counter
					int timeCounter = 100;
					
					// if not turned off and counter is still positive
					while (activeThread && (timeCounter > 0)) {

						// wait 1/10th of a second
						sleep(100);
						
						// every tenth time through the loop
						if (timeCounter % 10 == 0) {
						
							// send a message back to the main thread
							threadHandler
									.sendEmptyMessage((int) timeCounter / 10);
						}
						
						// count down from 100
						timeCounter--;
					}
				} catch (InterruptedException e) {
					// if the thread is interrupted
				} finally {
					
					// end the thread, activity, and app
					finish();
				}
			}
		};
	
		// starting thread
		myTread.start();
	}

	// detect a touch event - a type of listener we get from the activity
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		// user initiates touch with a "touch down" event
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			
			// toggle the thread to end next 1/10 of a second
			activeThread = false;
		}
		// complete the touch event by returning the parent event state
		return super.onTouchEvent(event);
	}

	// responds to message
	private Handler threadHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			message.setText("test int is " + threadModifiedInt
					+ "\ntest Point is " + threadModifiedPoint.toString()
					+ "\ncounter is " + Integer.toString(msg.what));
		}
	};

}
