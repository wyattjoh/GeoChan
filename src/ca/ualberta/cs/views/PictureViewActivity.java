package ca.ualberta.cs.views;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import ca.ualberta.cs.R;

/**
 * Simple activity for viewing a picture
 * 
 * @author wyatt
 *
 */
public class PictureViewActivity extends Activity {
	public final static String TITLE_KEY = "TITLE_KEY";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picture_view);

		Bitmap bitmap = PostViewActivity.getCurrentBitmap();

		// Get the intent and extras
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();

		// Update the title
		if (extras != null) {
			String titleText = extras.getString(TITLE_KEY);
			getActionBar().setTitle(titleText);
		}

		if (bitmap != null) {
			setupBitmap(bitmap);
		} else {
			finish();
		}
	}

	/**
	 * Inserts the bitmap into the imageView
	 * 
	 * @param bitmap
	 */
	private void setupBitmap(Bitmap bitmap) {
		ImageView imageView = (ImageView) findViewById(R.id.imageView);
		imageView.setImageBitmap(bitmap);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.picture_view, menu);
		return true;
	}

	/**
	 * Finishes the activity
	 * 
	 * @param v
	 */
	public void goBack(View v) {
		finish();
	}

}
