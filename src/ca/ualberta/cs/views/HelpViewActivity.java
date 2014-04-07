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
		String styledText = "<p><u><big><b>How to use Geo<font color='blue'>Chan</font></big></b></u></p>" +
							"<p><b>1. Creating a new topic:</b></p>" +
							"<p>To create a new topic, start by pressing the \"+\" " +
							"icon on the top right corner. " +
							"Once the page has loaded, there will be " +
							"two sections. In the \"Title\" section, type " +
							"in the main topic of your post. " +
							"In the \"Comment\" section, write about your " +
							"topic in greater detail. " +
							"Below this, there is a button that " +
							"initally displays 0.0, 0.0." +
							"This is where you can set your location. By clicking the button, a " +
							"map will appear with latitude and longitude " +
							"coordinates above it. " +
							"Click the magnifying glass to zoom in or out, " +
							"then select your exact location on the map and " +
							"press \"Enter\" to confirm. If you did not want to " +
							"set a location, press \"Cancel\". Back on the \"Create Topic\" " +
							"page, you also have the option to add a picture. By selecting " +
							"\"Add Picture\", you will be able to choose a picture from " +
							"any album on your cellular device. If you have selected a picture, " +
							"it will be displayed below. Press \"New Topic\" to create your post. " +
							"If you did not want to create a new post, select \"Cancel\".</p>" +
							"<p><b>2. Viewing a Topic</b></p>" +
							"<p>To view a topic, press anywhere to select a topic.</p>" +
							"<p><b>3. Favorite and voting</b></p>" +
							"<p>While viewing a post, press the \"Star\" icon in the centre " +
							"to favorite a post. If you like the post, upvote it by pressing " +
							"the \"Thumbs Up\" button to the right of the  \"Star\". If you " +
							"don't like a post, downvote it by pressing the \"Thumbs Down\" " +
							"button.</p>" +
							"<p><b>4. View posts that you have favorited or want to read later</b></p>" +
							"<p>Swipe to the left once from the main topic page to " +
							"look at \"Favorited Topics\". If you swipe to the left once more, " +
							"you can look at \"Favorited Comments\". Continuing to swipe to the left " +
							"will show \"ReadLater Topics\" and then \"ReadLater Comments\". " +
							"If you want to return to previous pages, swipe to the right.</p>" +
							"<p><b>5. Sorting Posts</b></p>" +
							"<p>To sort posts with a variety of options, click the vertical \"...\" " +
							"in the top right of the phone or the \"Menu\" button to " +
							"the left of the \"Home\" button.</p>" +
							"<p><b>6. Refresh Topics</b></p>" +
							"<p>To refresh a topic, press the \"Refresh\" icon to the right of the \"+\" " +
							"icon.</p>";
		helpView.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
	}
}
