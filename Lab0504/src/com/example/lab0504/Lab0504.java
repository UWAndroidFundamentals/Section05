package com.example.lab0504;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Lab0504 extends Activity {

	myAsyncTask sumTask;


	// reference three UI elements
	private TextView total;
	private EditText first;
	private EditText second;
	private Button calc;

	private int one = 0;
	private int two = 0;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen001);

		// pull in definition from XML
		first = (EditText) findViewById(R.id.first); 
		second = (EditText) findViewById(R.id.second);
		calc = (Button) findViewById(R.id.calc);
		
		calc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				one = Integer.parseInt(first.getText().toString());
				two = Integer.parseInt(second.getText().toString());

				sumTask = new myAsyncTask();
				sumTask.execute(one, two);

			}
		});
		
		
	}

	public class myAsyncTask extends AsyncTask<Integer, Void, Integer> {

		int sum = 0;

		@Override
		protected Integer doInBackground(Integer... parms) {

			sum = parms[0] + parms[1];

			return sum;
		}

		@Override
		// notify user of upload of adjustments
		protected void onPreExecute() {
		}

		@Override
		// close dialog at end of process
		protected void onPostExecute(Integer result) {

			total = (TextView) findViewById(R.id.total);
			total.setText(Integer.toString(result));
		}

	}

}
