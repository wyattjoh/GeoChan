package ca.ualberta.cs.views;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import ca.ualberta.cs.R;

public class LocationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//Broke this code because it was broken, used to be R.menu.location
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}