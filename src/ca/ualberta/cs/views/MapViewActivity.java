package ca.ualberta.cs.views;

import java.util.ArrayList;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedIconOverlay.OnItemGestureListener;
import org.osmdroid.views.overlay.OverlayItem;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;
import ca.ualberta.cs.R;

public class MapViewActivity extends Activity {

	private MapView mMapView;
	private MapController mMapController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_view);
		this.mMapView = (MapView) findViewById(R.id.mapview);
		this.mMapView.setTileSource(TileSourceFactory.MAPNIK);
		this.mMapView.setBuiltInZoomControls(true);
		this.mMapController = (MapController) this.mMapView.getController();

		Integer postType = getIntent().getExtras().getInt("postType");

		if (postType == 0) {
			// topic
			this.mMapController.setZoom(8);
			markSelfOnMap();
			markThreadOnMap();
		} else if (postType == 1) {
			// comment
			this.mMapController.setZoom(14);

			markSelfOnMap();
		} else {
			// Throw error
		}

		printLocation();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

	OnItemGestureListener<OverlayItem> myOnItemGestureListener = new OnItemGestureListener<OverlayItem>() {
		@Override
		public boolean onItemLongPress(int arg0, OverlayItem arg1) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onItemSingleTapUp(int index, OverlayItem item) {
			Toast.makeText(MapViewActivity.this,
					item.getTitle() + "\n" + item.getSnippet() + "\n",
					Toast.LENGTH_SHORT).show();
			return true;
		}
	};

	public void markSelfOnMap() {
		Location thisLocation = (Location) getIntent().getExtras()
				.getParcelable("selfLocation");
		GeoPoint gp_postLocation = new GeoPoint(thisLocation.getLatitude(),
				thisLocation.getLongitude());

		this.mMapController.setCenter(gp_postLocation);

		ArrayList<OverlayItem> theOverlayItemArray = new ArrayList<OverlayItem>();

		theOverlayItemArray.add(new OverlayItem("Post Location", String
				.valueOf(gp_postLocation.getLatitude())
				+ " , "
				+ String.valueOf(gp_postLocation.getLongitude()),
				gp_postLocation));
		ItemizedIconOverlay<OverlayItem> theItemizedIconOverlay = new ItemizedIconOverlay<OverlayItem>(
				this, theOverlayItemArray, myOnItemGestureListener);
		this.mMapView.getOverlays().add(theItemizedIconOverlay);
	}

	public void markThreadOnMap() {

		Bundle locationBundle = getIntent().getExtras().getBundle(
				"locationBundle");
		ArrayList<Location> postLocations = locationBundle
				.getParcelableArrayList("allPostLocations");

		ArrayList<OverlayItem> theThreadOverlayItemArray = new ArrayList<OverlayItem>();

		if (postLocations.size() == 0) {
			Toast.makeText(this, "No Other Thread Locations To Display",
					Toast.LENGTH_SHORT).show();
		}
		for (Location loc : postLocations) {
			Log.i("Received Locations", String.valueOf(loc.getLatitude()) + ","
					+ String.valueOf(loc.getLongitude()));
			GeoPoint gp_loc = new GeoPoint(loc.getLatitude(),
					loc.getLongitude());
			theThreadOverlayItemArray.add(new OverlayItem("Post Location",
					String.valueOf(gp_loc.getLatitude()) + " , "
							+ String.valueOf(gp_loc.getLongitude()), gp_loc));
		}
		ItemizedIconOverlay<OverlayItem> theItemizedIconOverlay = new ItemizedIconOverlay<OverlayItem>(
				this, theThreadOverlayItemArray, myOnItemGestureListener);
		mMapView.getOverlays().add(theItemizedIconOverlay);
	}

	public void printLocation() {
		Location goToLocation = (Location) getIntent().getExtras()
				.getParcelable("selfLocation");
		try {
			Toast.makeText(
					this,
					String.valueOf(goToLocation.getLatitude()) + " , "
							+ String.valueOf(goToLocation.getLongitude()),
					Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(this, "Failed to load location", Toast.LENGTH_SHORT)
					.show();
		}
	}

}
