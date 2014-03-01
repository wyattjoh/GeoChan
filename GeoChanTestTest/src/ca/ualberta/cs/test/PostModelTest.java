package ca.ualberta.cs.test;

import ca.ualberta.cs.MainActivity;
import ca.ualberta.cs.PostModel;
import android.test.ActivityInstrumentationTestCase2;

public class PostModelTest extends ActivityInstrumentationTestCase2<MainActivity> {

	public PostModelTest() {
		super(MainActivity.class);
	}

	public void testPostModel(){
		PostModel model = new PostModel();
		assertNotNull(model);
	}
}
