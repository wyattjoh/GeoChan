package ca.ualberta.cs.test;
import ca.ualberta.cs.models.CommentModel;
import ca.ualberta.cs.models.UserModel;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;


public class CommentTest extends ActivityInstrumentationTestCase2<Activity> {
	public CommentTest(){
		super(Activity.class);
	}

	/**
	 * TestCase 21.1
	 * Test whether comment takes user and allows for username retreival
	 */
	public void testCommentUser() {
		String theUserName = "Testuser";
		UserModel user = new UserModel(theUserName);
		
		CommentModel comment = new CommentModel();
		comment.setPostedBy(user);
		
		assertEquals(theUserName, comment.getPostedBy().getUserName());
	}
}
