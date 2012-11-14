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

public class Lab0505 extends Activity {

	myAsyncTask stockTask;

	// reference three UI elements
	private TextView stockValue;
	private EditText stockId;
	private Button getValue;

	String symbol = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen001);

		// pull in definition from XML
		stockId = (EditText) findViewById(R.id.stockid);
		getValue = (Button) findViewById(R.id.getvalue);

		getValue.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				symbol = stockId.getText().toString();
				stockTask = new myAsyncTask();
				stockTask.execute(symbol);

			}
		});

	}

	public class myAsyncTask extends AsyncTask<String, Void, String> {

		String returnedValue = "";

		@Override
		protected String doInBackground(String... parms) {
			
			// build the current URL from the API and the stock symbol
			String currentURL = "http://finance.google.com/finance/info?client=iq&q=NASDAQ:" + parms[0];
			
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
					
					// drop the first couple charaters - bug in web service
					responseText = responseText.substring(3);
					Log.v("log",responseText);
					
					// create a JSON object and an array
					JSONObject json = new JSONObject();
					JSONArray ja;
					ja = new JSONArray(responseText);
					
					// cycle through the one element of the array
					for (int i = 0; i < ja.length(); i++) {
						json = ja.getJSONObject(i);
						
						// get the price of the stock
						returnedValue = json.get("l_cur").toString();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return returnedValue;
		}

		@Override
		// close dialog at end of process
		protected void onPostExecute(String result) {

			stockValue = (TextView) findViewById(R.id.stockvalue);
			stockValue.setText(result);
		}

	}

}
