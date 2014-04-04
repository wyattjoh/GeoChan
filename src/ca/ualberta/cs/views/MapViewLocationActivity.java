package ca.ualberta.cs.views;

import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import ca.ualberta.cs.R;

public class MapViewLocationActivity extends Activity {
	 private MapView myOpenMapView;
	 private MapController myMapController;
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
	