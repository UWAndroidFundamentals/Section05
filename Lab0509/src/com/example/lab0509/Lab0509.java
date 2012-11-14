package com.example.lab0509;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;

/*
 * This lab shows a complex JSON string and how to parse it. 
 * 
 * single is a single integer item
 * array is a two element array
 * list is a two element list
 * 
 * All three elements are part of a list
 * 
 * {
    "single": {
        "myItem": 1
    },
    "array": [
        {
            "code": "A"
        },
        {
            "code": "B"
        }
    ],
    "list": {
        "item1": 44,
        "item2": "Bob"
    }
}
 * 
 */

public class Lab0509 extends Activity {
	
	// create multiple item list
	String complexList = "{\"single\": {\"myItem\": 1},\"array\": [{\"code\": \"A\"},{\"code\": \"B\"}],\"list\": {\"item1\": 44,\"item2\": \"Bob\"}}";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen001);

        int myItem = 0;
        String myArrayTwo = "";
        int myListOne = 0;
        
		try {
			
			Log.v("log","test1");
			// create the JSON object and pass it the string to process
			JSONObject json = new JSONObject(complexList);
			Log.v("log","test2");

			JSONObject jsonSingle = json.getJSONObject("single");
			Log.v("log","test3");
			JSONArray jsonArray = json.getJSONArray("array");
			Log.v("log","test4");
			JSONObject jsonList = json.getJSONObject("list"); 
			Log.v("log","test5");

			myItem = jsonSingle.getInt("myItem");
			Log.v("log","test6");
			myArrayTwo = jsonArray.getJSONObject(1).getString("code");
			Log.v("log","test7");
			myListOne = jsonList.getInt("item1"); 
			Log.v("log","test8");
	
			// write a log entry of the value
			Log.v("log","Single object value = " + myItem);
			Log.v("log","Second item in array = " + myArrayTwo);
			Log.v("log","First item in list = " + myListOne);
	
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
    
    
    }
}
