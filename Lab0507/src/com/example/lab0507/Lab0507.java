package com.example.lab0507;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.TextView;


/* This demos a single JSON list of objects and how to parse it.
 * 
 * {"obj1": 1,"obj2": 2,"obj3": 3}
 * 
 * The name of the objects are "obj1", "obj2", and "obj3"
 * The values of the objects are 1, 2, and 3
 */

public class Lab0507 extends Activity {

	// create a text string that has a simple list 
	// of JSON integers 
	String simpleList = "{\"obj1\": 1,\"obj2\": 2,\"obj3\": 3}";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen001);

		try {
			
			// create the JSON object and pass it the string to process
			JSONObject json = new JSONObject(simpleList);
			
			// get the second item in the list
			int myValue = json.getInt("obj2");
			
			// write a log entry of the value
			Log.v("log","the value of the second item in the list is = " + myValue);
			
			// change the text view to reflect the value
			TextView result = (TextView) findViewById(R.id.result);
			result.setText("the value of the second item in the list is = " + myValue);
	
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
    
    
    }

}
