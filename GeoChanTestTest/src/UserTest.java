import ca.ualberta.cs.models.ActiveUserModel;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

public class UserTest extends ActivityInstrumentationTestCase2<Activity> {

	public UserTest() {
		super(Activity.class);
	}

	/**
	 * TestCase 20.1 
	 * Test proper user names
	 */
	public void testChangingUserName() {
		String theUserName = "TestUser";
		String theNewuserName = "";
		ActiveUserModel userController = ActiveUserModel
				.createInstance(getActivity());
		userController.performLogin(theUserName);
		
		assertEquals("Test the username is set",theUserName, userController.getUser().getUserName());
		
		userController.performLogout();
		userController.performLogin(theNewuserName);
		
		assertNotSame("Make sure we changed usernames",theUserName, userController.getUser().getUserName());		
	}

}
