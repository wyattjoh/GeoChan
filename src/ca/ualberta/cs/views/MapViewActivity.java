package ca.ualberta.cs.views;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;
import ca.ualberta.cs.R;

public class MapViewActivity extends Activity {

    private MapView         mMapView;
    private MapController   mMapController;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
	    mMapView = (MapView) findViewById(R.id.mapview);
	    mMapView.setTileSource(TileSourceFactory.MAPNIK);
	    mMapView.setBuiltInZoomControls(true);
	    mMapController = (MapController) mMapView.getController();
	    mMapController.setZoom(13);
	    GeoPoint gPt = new GeoPoint(51500000, -150000);
	    //Center map near to Hyde Park Corner, London
	    mMapController.setCenter(gPt);
        printLocation();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

	public void printLocation() {
		Location goToLocation = (Location) getIntent().getExtras().getParcelable("postLocation");
		try {
			Toast.makeText(this, String.valueOf(goToLocation.getLatitude()) + " , " + 
				String.valueOf(goToLocation.getLongitude()), Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(this, "Failed to load location", Toast.LENGTH_SHORT).show();
		}
	}
}
