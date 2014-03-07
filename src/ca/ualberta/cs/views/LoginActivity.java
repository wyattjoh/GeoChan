package ca.ualberta.cs.views;

import ca.ualberta.cs.R;
import ca.ualberta.cs.R.id;
import ca.ualberta.cs.R.layout;
import ca.ualberta.cs.R.menu;
import ca.ualberta.cs.controllers.UserController;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		final Button button = (Button) findViewById(R.id.signInButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	// Get the username
            	EditText theTextField = (EditText)findViewById(R.id.userNameEditText);
            	
            	String theUserName = theTextField.getText().toString();
            	
                // Perform action on click
            	UserController userController = UserController.shared();
            	userController.performLogin(theUserName);
            	Intent resultIntent = new Intent();
            	setResult(Activity.RESULT_OK, resultIntent);
            	finish();
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
