/**
 * 
 */
package ca.ualberta.cs.controllers;

import android.content.Context;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.TopicModelList;
import ca.ualberta.cs.providers.ElasticSearchProvider;

/**
 * @author wyatt
 *
 */
public class TopicModelController {
	private TopicModelList theTopicModelList;
	private Context theContext;
	
	public TopicModelController(Context theContext) {
		this.theTopicModelList = TopicModelList.getInstance();
		this.theContext = theContext;
	}
	
	public void newTopic(TopicModel theTopicModel) {
		// Get the provider
		ElasticSearchProvider theProvider = ElasticSearchProvider.getProviderWithContext(theContext);
		// Add it to the list!
		theProvider.addTopic(theTopicModel, this.theTopicModelList);
	}
	
}
