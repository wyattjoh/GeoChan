/**
 * 
 */
package ca.ualberta.cs.controllers;

import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.TopicModelList;
import android.app.Activity;

/**
 * @author wyatt
 *
 */
public class EditTopicController {
	private TopicModelList theTopicModelList;
	private Activity theEditTopicActivity;
	
	public EditTopicController(Activity theEditTopicActivity) {
		this.theEditTopicActivity = theEditTopicActivity;
		this.theTopicModelList = TopicModelList.shared();
	}
	
	public void newTopic(String theTitle) {
		TopicModel theTopicModel = new TopicModel();
		
		theTopicModel.setTitle(theTitle);
		
		this.theTopicModelList.addTopicModel(theTopicModel);
	}
	
}
