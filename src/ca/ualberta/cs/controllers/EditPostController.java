package ca.ualberta.cs.controllers;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;

public class EditPostController {
	/**
	 * Compresses the bitmap to a WEBP formatted image
	 * 
	 * @param theBitmap
	 * @return the compressed bitmap byte array
	 */
	public static byte[] compressBitmap(Bitmap theBitmap) {
		theBitmap = theBitmap(theBitmap);
		ByteArrayOutputStream blob = new ByteArrayOutputStream();

		theBitmap.compress(CompressFormat.WEBP, 20, blob);

		return blob.toByteArray();
	}

	private static Bitmap theBitmap(Bitmap theBitmap) {
		int height = 500;
		if (theBitmap.getHeight() > height) {
			float factor = height / (float) theBitmap.getHeight();
			Bitmap bm = Bitmap.createScaledBitmap(theBitmap,
					(int) (theBitmap.getWidth() * factor), height, false);
			theBitmap = bm;
		}
		return theBitmap;
	}
}