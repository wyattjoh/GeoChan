package ca.ualberta.cs;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MapViewLocationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_view_location);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_view_location, menu);
		return true;
	}

}
