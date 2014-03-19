/**
 * 
 */
package ca.ualberta.cs.views;

import android.widget.TextView;
import ca.ualberta.cs.R;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.TopicModelList;

/**
 * @author wyatt
 *
 */
public class TopicViewActivity extends PostViewActivity<TopicModel> {

	@Override
	protected void getSelectedModel() {
		// Get the model list
		TopicModelList topicModelList = TopicModelList.getInstance();
		
		this.theModel = topicModelList.getSelectedTopicModel();
	}

	@Override
	void setTitleText() {
		TextView titleView = (TextView) findViewById(R.id.titleTextView);
		titleView.setText(theModel.getTitle());
		
		// Fix action bar
		getActionBar().setTitle(theModel.getTitle()); 
	}

}
