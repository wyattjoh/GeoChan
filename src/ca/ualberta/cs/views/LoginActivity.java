package ca.ualberta.cs.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import ca.ualberta.cs.R;
import ca.ualberta.cs.models.ActiveUserModel;
import ca.ualberta.cs.providers.LocationProvider;

/**
 * Performs the login activity actions
 * @author wyatt
 *
 */
public class LoginActivity extends Activity {

	/**
	 * Handles the login for the login form
	 * 
	 * @author wyatt
	 *
	 */
	private final class LoginListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			// Get the username
			EditText theTextField = (EditText) findViewById(R.id.userNameEditText);

			String theUserName = theTextField.getText().toString();

			if (theUserName.length() <= 0) {
				Toast.makeText(getApplicationContext(),
						"Cannot create an empty Username",
						Toast.LENGTH_LONG).show();
				return;
			}

			// Perform action on click
			ActiveUserModel userController = ActiveUserModel
					.createInstance(getApplicationContext());
			userController.performLogin(theUserName);
			Intent resultIntent = new Intent();
			setResult(Activity.RESULT_OK, resultIntent);
			finish();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		Intent intent = new Intent(this, LocationProvider.class);
		startService(intent);

		// Populate the view
		populateView();
	}

	/**
	 * Populates the view
	 */
	protected void populateView() {
		final Button button = (Button) findViewById(R.id.signInButton);
		button.setOnClickListener(new LoginListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
