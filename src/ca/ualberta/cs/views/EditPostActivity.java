package ca.ualberta.cs.views;

import java.io.ByteArrayOutputStream;

import ca.ualberta.cs.R;
import ca.ualberta.cs.models.PostModel;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.MediaColumns;
import android.view.Menu;
import android.widget.ImageView;

public abstract class EditPostActivity<T extends PostModel> extends Activity {

	// image vars
	private static final int SELECT_PICTURE = 1;
	protected byte[] imageByteArray;
	protected Bitmap imageBitmap;

	protected T theModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_post);

		getSelectedModel();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_post, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onStart()
	 */

	protected abstract void populateView();

	abstract protected void getSelectedModel();

	/**
	 * github http://stackoverflow.com/questions/2169649/get-pick-an-image-from-
	 * androids-built-in-gallery-app-programmatically
	 */
	public void getPictureIntent() {
		// select a file
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "Select Picture"),
				SELECT_PICTURE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (requestCode == SELECT_PICTURE) {
				// get picture path from intent
				Uri selectedImageUri = data.getData();
				String selectedImagePath = getPath(selectedImageUri);

				// get picture object from path
				imageBitmap = BitmapFactory.decodeFile(selectedImagePath);

				// get and set image view
				ImageView galleryThumbnail = (ImageView) findViewById(R.id.imageThumbnail);
				// galleryThumbnail.setVisibility(View.VISIBLE);

				// create scaled image for display
				Bitmap scaledBitmap = scaleBitMapToFit(imageBitmap,
						galleryThumbnail);

				// set the view image o the selected image
				galleryThumbnail.setImageBitmap(scaledBitmap);

				// compress and output to class variable
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
				imageByteArray = outStream.toByteArray();
			}
		}
	}

	/**
	 * returns a scaled image where the image will be sized so that it will fit
	 * the image view by scaling to the largest length, either width or height
	 * and setting that to the size of the image view
	 * 
	 * @param bitmapImage
	 * @param imageViewScale
	 * @return
	 */
	public Bitmap scaleBitMapToFit(Bitmap bitmapImage,
			ImageView imageViewScale) {
		Bitmap scaledBitmap = null;

		if (bitmapImage.getWidth() > bitmapImage.getHeight()) {
			// if the image is bigger horizontally scale vertically
			scaledBitmap = Bitmap.createScaledBitmap(bitmapImage,
					bitmapImage.getWidth(), imageViewScale.getHeight(),
					imageViewScale.getFilterTouchesWhenObscured());

		} else {
			// otherwise scale horizontally
			scaledBitmap = Bitmap.createScaledBitmap(bitmapImage,
					imageViewScale.getWidth(), bitmapImage.getHeight(),
					imageViewScale.getFilterTouchesWhenObscured());
		}

		return scaledBitmap;
	}

	/**
	 * helper to retrieve the path of an image URI
	 */
	public String getPath(Uri uri) {
		// just some safety built in
		if (uri == null) {
			// TODO perform some logging or show user feedback
			return null;
		}
		// try to retrieve the image from the media store first
		// this will only work for images selected from gallery
		String[] projection = { MediaColumns.DATA };
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		if (cursor != null) {
			int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
			cursor.moveToFirst();
			return cursor.getString(column_index);
		}
		// this is our fallback here
		return uri.getPath();
	}
}
