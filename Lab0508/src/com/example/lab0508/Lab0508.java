package com.example.lab0508;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;


/* This demos a single JSON array and how to parse it.
 * 
 * [{"code": 1},{"code": 2}]
 * 
 * The name of the array is myArray
 * The values of the array are 1 and 2
 */

public class Lab0508 extends Activity {
	
	// create a text string that has a simple list 
	// of JSON integers 
	String simpleArray = "[{\"code\": 1},{\"code\": 2}]";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen001);

        String myValue = "";
        String message = "";
        
		try {
			
			// create the JSON object
			JSONObject json = new JSONObject();
			
			JSONArray ja = new JSONArray(simpleArray);
			
			// cycle through the array
			for (int i = 0; i < ja.length(); i++) {
				
			// set the json object to the current object in the array	
			json = ja.getJSONObject(i);
		
			// build a string to describe the value
			myValue = "The " + i + " item is " + json.getInt("code") + "\n";
			
			// write a log entry of the value
			Log.v("log",myValue);
			
			message = message + myValue;
			}
			
			// change the text view to reflect the value
			TextView result = (TextView) findViewById(R.id.result);
			result.setText(message);
	
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
    
    
    }
}
