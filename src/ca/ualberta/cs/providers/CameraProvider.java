package ca.ualberta.cs.providers;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

/**
 * gets and starts camera activity
 * @author Ben
 *
 */
public class CameraProvider extends Activity {
	// local vars
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	
	public void startCamera(){
	    // create Intent to take a picture and return control to the calling application
	       Intent imageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	       File imagesFolder = new File(Environment.getExternalStorageDirectory(), "MyImages");
	       imagesFolder.mkdirs(); // <----
	       File image = new File(imagesFolder, "image_001.jpg");
	       Uri uriSavedImage = Uri.fromFile(image);
	       imageIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
	       startActivityForResult(imageIntent,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		  if (requestCode == 1) {

		     if(resultCode == RESULT_OK){      
		         String result=data.getStringExtra("result");          
		     }
		     if (resultCode == RESULT_CANCELED) {    
		         //Write your code if there's no result
		     }
		  }
		}//onActivityResult
}
