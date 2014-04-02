/**
 * 
 */
package ca.ualberta.cs.controllers;

import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.TopicModelList;
import ca.ualberta.cs.providers.ElasticSearchProvider;

/**
 * @author wyatt
 *
 */
public class TopicModelController {
	private TopicModelList theTopicModelList;
	
	public TopicModelController() {
		this.theTopicModelList = TopicModelList.getInstance();
	}
	
	public void newTopic(TopicModel theTopicModel) {
		// Get the provider
		ElasticSearchProvider theProvider = ElasticSearchProvider.getProvider();
		// Add it to the list!
		theProvider.addTopic(theTopicModel, this.theTopicModelList);
	}
	
	public void updateTopic(TopicModel theTopicModel){
		// Get the provider
		ElasticSearchProvider theProvider = ElasticSearchProvider.getProvider();
		// Add it to the list!
		theProvider.updateTopic(theTopicModel, this.theTopicModelList);
	}
	
}
