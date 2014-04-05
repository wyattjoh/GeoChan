package ca.ualberta.cs.views;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import ca.ualberta.cs.R;

public class PictureViewActivity extends Activity {
	public final static String BITMAP_KEY = "BITMAP_KEY";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picture_view);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		Bitmap bitmap = PostViewActivity.getCurrentBitmap();
		
		if (bitmap != null) {
			setupBitmap(bitmap);
		}
		else {
			finish();
		}
	}

	/**
	 * Inserts the bitmap into the imageView
	 * @param bitmap
	 */
	private void setupBitmap(Bitmap bitmap) {
		ImageView imageView = (ImageView) findViewById(R.id.imageView);
		imageView.setImageBitmap(bitmap);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.picture_view, menu);
		return true;
	}
	
	/**
	 * Finishes the activity
	 * @param v
	 */
	public void goBack(View v) {
		finish();
	}

}
