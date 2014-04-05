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
import android.view.Menu;
import android.widget.Toast;
import ca.ualberta.cs.R;

public class MapViewActivity extends Activity {

    private MapView         mMapView;
    private MapController   mMapController;
    
    ArrayList<OverlayItem> items;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
	    this.mMapView = (MapView) findViewById(R.id.mapview);
	    this.mMapView.setTileSource(TileSourceFactory.MAPNIK);
	    this.mMapView.setBuiltInZoomControls(true);
	    this.mMapController = (MapController) this.mMapView.getController();
	    this.mMapController.setZoom(14);
	    
		Location goToLocation = (Location) getIntent().getExtras().getParcelable("postLocation");
	    GeoPoint gp_postLocation = new GeoPoint(goToLocation.getLatitude(), goToLocation.getLongitude());
	    
	    this.mMapController.setCenter(gp_postLocation);
	    
	    //Add Scale Bar
	    ArrayList<OverlayItem> theOverlayItemArray = new ArrayList<OverlayItem>();
	    theOverlayItemArray.add(new OverlayItem(
	    		"Post Location",
	    		String.valueOf(gp_postLocation.getLatitude()) + " , " +String.valueOf(gp_postLocation.getLongitude()),
	    		gp_postLocation));
	    ItemizedIconOverlay<OverlayItem> theItemizedIconOverlay = new ItemizedIconOverlay<OverlayItem>(
	    		this, theOverlayItemArray, myOnItemGestureListener);
	    this.mMapView.getOverlays().add(theItemizedIconOverlay);
        printLocation();
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
		     item.getTitle() + "\n"
		     + item.getSnippet()+ "\n",
		     Toast.LENGTH_LONG).show();
		   return true;
		  }
	};
	
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
