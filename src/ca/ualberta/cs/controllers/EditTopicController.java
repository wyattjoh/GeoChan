/**
 * 
 */
package ca.ualberta.cs.controllers;

import android.os.Looper;
import android.util.Log;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.TopicModelList;

/**
 * @author wyatt
 *
 */
public class EditTopicController {
	private TopicModelList theTopicModelList;
//	private Activity theEditTopicActivity;
	
	public EditTopicController() {
		this.theTopicModelList = TopicModelList.getInstance();
	}
	
	public void newTopic(String theTitle) {
		TopicModel theTopicModel = new TopicModel();
		
		theTopicModel.setTitle(theTitle);
		
		this.theTopicModelList.addTopicModel(theTopicModel);
		
		if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
			Log.w("EditTopicController", "Running on UI Thread.");
		} else {
			Log.w("EditTopicController", "Not running on UI Thread.");
		}
	}
	
}
