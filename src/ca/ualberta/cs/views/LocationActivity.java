package ca.ualberta.cs.views;

import java.util.ArrayList;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.MapView.Projection;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedIconOverlay.OnItemGestureListener;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import ca.ualberta.cs.R;

/**
 * 
 * @author troy
 * 
 */
public class LocationActivity extends Activity {

	private MapView lMapView;
	private MapController lMapController;

	LocationManager locationManager;
	ArrayList<OverlayItem> theOverlayItemArray;
	MyLocationNewOverlay myLocationOverlay = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);

		this.lMapView = (MapView) findViewById(R.id.locationmapview);
		this.lMapView.setTileSource(TileSourceFactory.MAPNIK);
		this.lMapView.setBuiltInZoomControls(true);
		this.lMapController = (MapController) this.lMapView.getController();
		this.lMapController.setZoom(2);

		setToCurrentLocation();
	}

	OnItemGestureListener<OverlayItem> myOnItemGestureListener = new OnItemGestureListener<OverlayItem>() {
		@Override
		public boolean onItemLongPress(int arg0, OverlayItem arg1) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onItemSingleTapUp(int index, OverlayItem item) {
			Toast.makeText(LocationActivity.this,
					item.getTitle() + "\n" + item.getSnippet() + "\n",
					Toast.LENGTH_SHORT).show();
			return true;
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// Broke this code because it was broken, used to be R.menu.location
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	protected boolean isEmpty(EditText etText) {
		return etText.getText().toString().trim().length() == 0;
	}

	public void onClick_SubmitLocation(View theView) {
		/**
		 * Make sure that a Latitude and Longitude have been given, then create
		 * the points Note that comp sci building is 53.526617,-113.52702
		 */
		EditText etLat = (EditText) findViewById(R.id.editTextLatitude);
		EditText etLng = (EditText) findViewById(R.id.editTextLongitude);

		if (isEmpty(etLat) || isEmpty(etLng)) {
			Toast.makeText(this, "Location Information Missing",
					Toast.LENGTH_SHORT).show();
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

	public void setToCurrentLocation() {
		Location previousLocation = (Location) getIntent().getExtras()
				.getParcelable("previousLocation");
		if (previousLocation == null) {
			previousLocation = new Location("");
			this.lMapController.setZoom(1);
		} else {
			this.lMapController.setZoom(14);
		}
		GeoPoint gp_prevlocation = new GeoPoint(previousLocation);
		this.lMapController.setCenter(gp_prevlocation);

		TextView locationDisplay = (TextView) findViewById(R.id.tvPreviousLocation);
		locationDisplay.setText("("
				+ String.valueOf(previousLocation.getLatitude()) + " , "
				+ String.valueOf(previousLocation.getLongitude()) + ")");

		theOverlayItemArray = new ArrayList<OverlayItem>();

		theOverlayItemArray.add(new OverlayItem("Post Location", String
				.valueOf(gp_prevlocation.getLatitude())
				+ " , "
				+ String.valueOf(gp_prevlocation.getLongitude()),
				gp_prevlocation));
		ItemizedIconOverlay<OverlayItem> theItemizedIconOverlay = new ItemizedIconOverlay<OverlayItem>(
				this, theOverlayItemArray, myOnItemGestureListener) {
		};
		this.lMapView.getOverlays().add(theItemizedIconOverlay);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		int actionType = ev.getAction();
		switch (actionType) {
		case MotionEvent.ACTION_UP:
			Projection projection = lMapView.getProjection();
			GeoPoint gp_loc = (GeoPoint) projection.fromPixels((int) ev.getX(),
					(int) ev.getY());
			Integer topBound = lMapView.getTop();
			Log.i("Coordinates", "evY:" + String.valueOf(ev.getY())
					+ "TopBound" + String.valueOf(topBound));
			if ((int) ev.getY() > topBound + 100) {
				String latitude = Double.toString(((double) gp_loc
						.getLatitudeE6()) / 1000000);
				String longitude = Double.toString(((double) gp_loc
						.getLongitudeE6()) / 1000000);
				setLatLong(latitude, longitude);
			}
		}
		return super.dispatchTouchEvent(ev);
	}

	public void setLatLong(String latitude, String longitude) {
		EditText etLat = (EditText) findViewById(R.id.editTextLatitude);
		EditText etLon = (EditText) findViewById(R.id.editTextLongitude);

		etLat.setText(latitude);
		etLon.setText(longitude);
	}
}