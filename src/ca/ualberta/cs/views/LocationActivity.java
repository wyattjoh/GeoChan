package ca.ualberta.cs.views;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import ca.ualberta.cs.R;
import ca.ualberta.cs.providers.LocationProvider;
/**
 * 
 * @author troy
 *
 */
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
	protected boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
	public void onClick_SubmitLocation(View theView) {
		/**
		 * Make sure that a Latitude and Longitude have been given, then create the points
		 */
		EditText etLat = (EditText)findViewById(R.id.editTextLatitude);
		EditText etLng = (EditText)findViewById(R.id.editTextLongitude);
				
		if (isEmpty(etLat) || isEmpty(etLng)) {
			Toast.makeText(this, "Location failed to create", Toast.LENGTH_SHORT).show();
		} else {
			String strLatitude = etLat.getText().toString();
			Double doubLatitude = Double.parseDouble(strLatitude);
			

			String strLongitude = etLng.getText().toString();
			Double doubLongitude = Double.parseDouble(strLongitude);
			
			Location theCommentLocation = LocationProvider.getInstance(this).getLocation();
			theCommentLocation.setLatitude(doubLatitude);
			theCommentLocation.setLongitude(doubLongitude);
			
			Toast.makeText(this, theCommentLocation.toString(), Toast.LENGTH_SHORT).show();
		}
	}
}