package ca.ualberta.cs.views;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;
import ca.ualberta.cs.R;


/**
 * Displays the help's instructions for users
 * @author Stephen
 *
 */
public class HelpViewActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		// Populate the view
		populateView();
	}
	
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        int id = item.getItemId();
	        if (id == android.R.id.home) {
	        	onBackPressed();
	            return true;
	        }
	        return super.onOptionsItemSelected(item);
	    }

	protected void populateView() {
		// Add Help
		TextView helpView = (TextView) findViewById(R.id.helpText);
		String styledText = "<p><u><big><b>How to use this App</big></b></u></p>" +
							"<p>1.THIS IS HELP STUFF\n</p>" +
							"<p>\n2. More Help Stuff\n</p>" +
							"<p>\n3. Even More Help Stuff\n</p>" +
							"<p>\n4. Last of the Help Stuff\n</p>" +
							"<p>\n5. JK this is the last stuff\n</p>" +
							"<p>\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n</p>";
		helpView.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
	}
}
