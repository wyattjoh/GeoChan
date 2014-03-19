/**
 * 
 */
package ca.ualberta.cs.controllers;

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
	
	public void newTopic(TopicModel theTopicModel) {
		this.theTopicModelList.addTopicModel(theTopicModel);
	}
	
}
