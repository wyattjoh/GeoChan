package ca.ualberta.cs.views;

import android.app.Activity;
import android.content.Intent;
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
		try {
			Toast.makeText(this, String.valueOf(LocationProvider.getInstance(null).getLastKnownLocation().getLatitude()) +
					String.valueOf(LocationProvider.getInstance(null).getLastKnownLocation().getLatitude()),
					Toast.LENGTH_SHORT).show();
		}catch (Exception e) {
			Toast.makeText(this, "No last known location", Toast.LENGTH_SHORT).show();
		}
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
		 * Note that comp sci building is 53.526617,-113.52702
		 */
		EditText etLat = (EditText)findViewById(R.id.editTextLatitude);
		EditText etLng = (EditText)findViewById(R.id.editTextLongitude);
				
		if (isEmpty(etLat) || isEmpty(etLng)) {
			Toast.makeText(this, "Location Information Missing", Toast.LENGTH_SHORT).show();
		} else {
			String strLatitude = etLat.getText().toString();
			Double doubLatitude = Double.parseDouble(strLatitude);
			etLat.setText("");
			
			String strLongitude = etLng.getText().toString();
			Double doubLongitude = Double.parseDouble(strLongitude);
			etLng.setText("");
			
			Intent returnIntent = new Intent();
			returnIntent.putExtra("extLatitude", doubLatitude);
			returnIntent.putExtra("extLongitude", doubLongitude);
			
			setResult(RESULT_OK, returnIntent);
			finish();
		}
	}
	public void onClick_CancelLocation(View theView) {
		Intent returnIntent = new Intent();
		setResult(RESULT_CANCELED, returnIntent);
		finish();
	}
}