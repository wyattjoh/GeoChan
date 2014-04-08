/**
 * 
 */
package ca.ualberta.cs.controllers;

import ca.ualberta.cs.models.PostModelList;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.TopicModelList;
import ca.ualberta.cs.providers.ElasticSearchProvider;

/**
 * Controls the models of topics
 * 
 * @author wyatt
 * 
 */
public class TopicModelController {
	private TopicModelList theTopicModelList;

	public TopicModelController() {
		this.theTopicModelList = TopicModelList.getInstance();
	}

	/**
	 * Adds a new topic
	 * 
	 * @param theTopicModel
	 */
	public void newTopic(TopicModel theTopicModel) {
		// Get the provider
		ElasticSearchProvider theProvider = ElasticSearchProvider.getProvider();
		// Add it to the list!
		theProvider.addTopic(theTopicModel, this.theTopicModelList);
	}

	/**
	 * Updates a topic
	 * 
	 * @param thePostModelList
	 */
	public void updateTopic(PostModelList<TopicModel> thePostModelList) {
		// Get the provider
		ElasticSearchProvider theProvider = ElasticSearchProvider.getProvider();

		theProvider.updateTopic(
				TopicModelList.getInstance().getLastSelection(),
				thePostModelList);
	}

}
