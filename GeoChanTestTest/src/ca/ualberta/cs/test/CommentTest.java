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
	 * TestCase 5.1
	 * Test whether a topic's comments are displayed
	 */
	public void testCommentDisplay() {
		fail();
	}
	
	/**
	 * TestCase 6.1
	 * Test whether an error message is displayed if no text is entered when posting a comment
	 */
	public void testNoTextComment() {
		fail();
	}
	
	/**
	 * TestCase 6.2
	 * Test whether a new comment can be created
	 */
	public void testCommentCreation() {
		fail();
	}
	
	/**
	 * TestCase 6.3
	 * Test whether a new comment is correctly attached to its parent
	 */
	public void testCommentAssociation() {
		fail();
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
