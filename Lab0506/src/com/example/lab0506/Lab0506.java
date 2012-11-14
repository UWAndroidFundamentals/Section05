package com.example.lab0506;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

/* This demos a single JSON object and how to parse it.
 * 
 * {"myobject":1}
 * 
 * The name of the object is "myobject"
 * The value of the object is an integer of value = 1
 * 
 */


public class Lab0506 extends Activity {

	// create a text string that has a single integer JSON object 
	// called "myobject" with a value of 1
	String singleObject = "{\"myobject\":1}";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen001);

		try {
			
			// create the JSON object and pass it the string to process
			JSONObject json = new JSONObject(singleObject);
			
			// extract the integer value of "myobject"
			int myvalue = json.getInt("myobject");
			
			// write a log entry of the value
			Log.v("log","the value of myobject is = " + myvalue);
			
			// change the text view to reflect the value
			TextView result = (TextView) findViewById(R.id.result);
			result.setText("The value of myobject is " + myvalue);
	
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
    
    
    }
}
