package com.example.lab0505;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/* This project uses the yahoo geo places API.  It accepts a city name,
 * parses through a JSON list of possible locations, and returns the 
 * county where that city is located.
 * 
 * For example:
 * 
 * 	Seattle = King county
 *  Auburn = Lee county (since it finds Auburn in Alabama)
 * 
 *  Your job is to fill in the missing JSON parsing entries as 
 *  described in the comments 
 * 
 */


public class Lab0505 extends Activity {

	myAsyncTask cityTask;

	// reference three UI elements
	private TextView county;
	private EditText city;
	private Button getValue;

	String loc = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen001);

		// pull in definition from XML
		city = (EditText) findViewById(R.id.city);
		getValue = (Button) findViewById(R.id.getvalue);

		getValue.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				loc = city.getText().toString();

				cityTask = new myAsyncTask();
				cityTask.execute(loc);

			}
		});

	}

	public class myAsyncTask extends AsyncTask<String, Void, String> {

		String returnedValue = "";

		@Override
		protected String doInBackground(String... parms) {

			// build the current URL from the API and the city name that was passed
			String currentURL = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20geo.places%20where%20text%3D%22"
					+ parms[0] 
					+ "%22&format=json&diagnostics=true&callback=cbfunc";
			
			// build an HTTP client
			HttpClient client = new DefaultHttpClient();

			// set the time out value
			HttpConnectionParams
					.setConnectionTimeout(client.getParams(), 30000);

			// create a response object
			HttpResponse response = null;
			String responseText = "";

			// invoke the get
			HttpGet get = new HttpGet(currentURL);
			try {
				response = client.execute(get);
			} catch (Exception e) {
			}

			// process the result
			if (response != null) {

				// factor out the response from all of the HTTP information
				HttpEntity entity = response.getEntity();
				try {

					// convert the response into a string value
					responseText = EntityUtils.toString(entity);

					// drop the first couple characters - bug in web service
					responseText = responseText.substring(7,
							responseText.length() - 2);
					Log.v("log", responseText);

					// create a JSON object for the root element
					JSONObject json = new JSONObject();
					
					// create JSON object for one level in
					JSONObject json1 = new JSONObject();
	
					// create JSON object for two levels in
					JSONObject json2 = new JSONObject();
					
					// create JSON object for three levels in
					JSONObject json3 = new JSONObject();

					// create JSON object for four levels in
					JSONObject json4 = new JSONObject();
				
					// create JSON array 
					JSONArray jsonArray1 = new JSONArray();
					
					// create the root JSON object and load the data
					json = new JSONObject(responseText);
					
					// derive json1 object from json at the "query" entry
					
					// derive json2 object from json1 at the "results" entry
					
					// derive jsonArray1 array from json2 at the "place" entry

					// derive json3 object from the first entry in the jsonArray1 array
					
					// derive json4 object from json3 at the "admin2" entry
					
					// set returnedValue to the string "content" in json4
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return returnedValue;
		}

		@Override
		// close dialog at end of process
		protected void onPostExecute(String result) {

			county = (TextView) findViewById(R.id.county);
			county.setText(result);
		}

	}

}
