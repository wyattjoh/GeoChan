/**
 * 
 */
package ca.ualberta.cs.views;

import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import ca.ualberta.cs.R;
import ca.ualberta.cs.models.CommentModel;
import ca.ualberta.cs.models.CommentModelList;

/**
 * @author wyatt
 *
 */
public class CommentViewActivity extends PostViewActivity<CommentModel> {

	@Override
	protected void getSelectedModel() {
		// TODO Auto-generated method stub
		this.theModel = CommentModelList.getInstance().getSelection();
	}

	@Override
	void setTitleText() {
		// Hide the titleView
		TextView titleView = (TextView) findViewById(R.id.titleTextView);
		titleView.setVisibility(View.GONE);
		
		// Fix action bar
		getActionBar().setTitle(theModel.getCommentText()); 
	}

	@Override
	protected OnClickListener getFavoriteOnClickListener() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.cellActiveArea:
			newPost(EditCommentActivity.class);
			return true;
		case R.id.action_settings:
			startSettingsActivity();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
